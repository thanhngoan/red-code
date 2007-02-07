/*
 *  The scanner definition for COOL.
 */

/*
 *  Stuff enclosed in %{ %} in the first section is copied verbatim to the
 *  output, so headers and global definitions are placed here to be visible
 * to the code in the file.  Don't remove anything that was here initially
 */
%{
#include <cool-parse.h>
#include <stringtab.h>
#include <utilities.h>

/* The compiler assumes these identifiers. */
#define yylval cool_yylval
#define yylex  cool_yylex

/* Max size of string constants */
#define MAX_STR_CONST 1025
#define YY_NO_UNPUT   /* keep g++ happy */

extern FILE *fin; /* we read from this file */

extern void cool_yyrestart(FILE * f)
{
  yyrestart(f);
}

#define THE_THING 5

/* define YY_INPUT so we read from the FILE fin:
 * This change makes it possible to use this scanner in
 * the Cool compiler.
 */
#undef YY_INPUT
#define YY_INPUT(buf,result,max_size) \
	if ( (result = fread( (char*)buf, sizeof(char), max_size, fin)) < 0) \
		YY_FATAL_ERROR( "read() in flex scanner failed");

char string_buf[MAX_STR_CONST]; /* to assemble string constants */
char *string_buf_ptr;

extern int curr_lineno;
extern int verbose_flag;

extern YYSTYPE cool_yylval;

/*
 *  Add Your own definitions here
 */
int comment_level = 0; //holds the level of nested comments we are in.  if we are in no comment, it is 0
std::string str_or_comment = "";
bool invalid_string_p = false; //true if the current string has been condemned for invalid characters
%}

/*
 * Define names for regular expressions here.
 */

DARROW                =>
ASSIGN               <-
INTEGER              [0-9]+
OBJECT_IDENTIFIER    [a-z]([a-zA-Z0-9_]*)
TYPE_IDENTIFIER      [A-Z]([a-zA-Z0-9_]*)
WS                   [\n\f\r\t\v\32 ]
DOUBLE_DASH          --
COMMENT_START        ("(*")
COMMENT_END          ("*)")
NEWLINE              \n


%x INCOMMENT INSTRING
%%

 /*
  *  Nested comments
  */

<INITIAL,INCOMMENT>\(\*        {
  ++comment_level;
  BEGIN(INCOMMENT);
}

<INCOMMENT>\*\)                  {
  --comment_level;
  if (comment_level <= 0)
    {
      str_or_comment = ""; // reset the comment text, which is currently not used but might be useful in the future.
      if (comment_level == 0) //it's cool 
        BEGIN(INITIAL);
      else //this should theoretically be impossible to reach
        {
          BEGIN(INITIAL);
          comment_level = 0;
          cool_yylval.error_msg = "Unmatched *)";
          return (ERROR);
        }
    }
 }
<INITIAL>\*\)     {
  cool_yylval.error_msg = "Unmatched *)";
  return (ERROR);
 }

<INCOMMENT><<EOF>>            {
  BEGIN(INITIAL);
  cool_yylval.error_msg = "EOF in comment";
  return (ERROR);
 }

<INCOMMENT>.   { str_or_comment += yytext;}

 /* single-line comments */

{DOUBLE_DASH}.*         { }
 /*
  *  The multiple-character operators.
  */

{DARROW}	      { return (DARROW); }
{ASSIGN}	      { return (ASSIGN); }

 /*
  * Keywords are case-insensitive except for the values true and false,
  * which must begin with a lower-case letter.
  */

[Cc][Ll][Aa][Ss][Ss]      { return (CLASS); }  // class
[Ee][Ll][Ss][Ee]      { return (ELSE); }  // else
[Ff][Ii]      { return (FI); }  // fi
[Ii][Ff]      { return (IF); }  // if
[Ii][Nn]      { return (IN); }  // in
[Ii][Nn][Hh][Ee][Rr][Ii][Tt][Ss]      { return (INHERITS); }  // inherits
[Ii][Ss][Vv][Oo][Ii][Dd]      { return (ISVOID); }  // isvoid
[Ll][Ee][Tt]      { return (LET); }  // let
[Ll][Oo][Oo][Pp]      { return (LOOP); }  // loop
[Pp][Oo][Oo][Ll]      { return (POOL); }  // pool
[Tt][Hh][Ee][Nn]      { return (THEN); }  // then
[Ww][Hh][Ii][Ll][Ee]      { return (WHILE); }  // while
[Cc][Aa][Ss][Ee]      { return (CASE); }  // case
[Ee][Ss][Aa][Cc]      { return (ESAC); }  // esac
[Nn][Ee][Ww]      { return (NEW); }  // new
[Oo][Ff]      { return (OF); }  // of
[Nn][Oo][Tt]      { return (NOT); }  // not

false           {
  cool_yylval.boolean = false;
  return (BOOL_CONST);
}

true           {
  cool_yylval.boolean = true;
  return (BOOL_CONST);
}

"+"      { return ('+'); }
"-"      { return ('-'); }
"="      { return ('='); }
"<"      { return ('<'); }
\.      { return ('.'); }
"~"      { return ('~'); }
","      { return (','); }
";"      { return (';'); }
":"      { return (':'); }
")"      { return (')'); }
"@"      { return ('@'); }
"{"      { return ('{'); }
"}"      { return ('}'); }
"*"      { return ('*'); }
"("      { return ('('); }

{OBJECT_IDENTIFIER}   {
  //  cool_yylval.symbol = idtable.add_string("hello");
  cool_yylval.symbol = idtable.add_string(yytext, yyleng);
  return (OBJECTID);
}
{TYPE_IDENTIFIER}   {
  cool_yylval.symbol = idtable.add_string(yytext, yyleng);
  return (TYPEID);
}

{INTEGER}             { 
  int parsed_number = strtol(yytext, &yytext + yyleng, 10);
  cool_yylval.symbol = inttable.add_int(parsed_number);                                        
  return (INT_CONST);
}

 /*
  *  String constants (C syntax)
  *  Escape sequence \c is accepted for all characters c. Except for 
  *  \n \t \b \f, the result is c.
  *  There are a few error conditions.
  */
<INSTRING>\"                               {
  
  BEGIN(INITIAL); //on end quote, we are out of the string state
  if (str_or_comment.size() > MAX_STR_CONST)
    {
      cool_yylval.error_msg = "String constant too long";
      str_or_comment = "";
      return (ERROR);
    }
  else if (!invalid_string_p)
    {
      // add the string to the string table and report it to the 
      cool_yylval.symbol = stringtable.add_string(const_cast<char *>(str_or_comment.c_str()));
      str_or_comment = "";
      return (STR_CONST);
    }
 }

\"                                          {
  invalid_string_p = false;
  BEGIN(INSTRING);
}

<INSTRING>(\\.|\\\n)                          {
  char unescaped = 0;
  switch (yytext[1]) {
    //  case '\0': unput('\0') break; // pass the null character on to be handled as an error
  case 'n': unescaped = '\n'; break;
  case 'b': unescaped = '\b'; break;
  case 'f': unescaped = '\f'; break;
  case 't': unescaped = '\t'; break;
  case '\n': curr_lineno++;
  default: unescaped = yytext[1]; break;
  }
  str_or_comment += unescaped;
}

<INSTRING>[\0]                       {
  invalid_string_p = true;
  cool_yylval.error_msg = "String contains null character";
  return (ERROR);
}

<INSTRING><<EOF>>            {
  BEGIN(INITIAL);
  invalid_string_p = true;
  cool_yylval.error_msg = "EOF in string constant";
  return (ERROR);
 }

<INSTRING>.            {
  str_or_comment += yytext;
 }

<INSTRING>\n            {
  /* If a string contains an unescaped newline, report that error as ‘‘Unterminated string constant’’
     and resume lexing at the beginning of the next line—we assume the programmer simply forgot the
     close-quote.
  */
  curr_lineno++; //always always increment the line count
  cool_yylval.error_msg = "Unterminated string constant";
  BEGIN(INITIAL);
  return (ERROR);
 }

<INITIAL,INCOMMENT>{NEWLINE} { curr_lineno++; }

{WS} { }

. { cool_yylval.error_msg = yytext; return (ERROR); } 

%%

