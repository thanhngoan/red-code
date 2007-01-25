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
COMMENT_START        \(\*
COMMENT_END          \*\)
NEWLINE              \n

%s IN_COMMENT IN_STRING
%%

 /*
  *  Nested comments
  */
<INITIAL,IN_COMMENT>COMMENT_START        {
  ++comment_level;
  BEGIN(IN_COMMENT);
}

<IN_COMMENT>COMMENT_END                  {
  --comment_level;
  if (comment_level == 0)
    BEGIN(INITIAL);
  else if (comment_level < 0)
    {
      comment_level = 0;
      cool_yylval.error_msg = "Unmatched *)";
      return (ERROR);
    }
}

<IN_COMMENT>.   { str_or_comment += yytext;}

 /*
  *  The multiple-character operators.
  */

{DARROW}	      { return (DARROW); }
{ASSIGN}	      { return (ASSIGN); }

 /*
  * Keywords are case-insensitive except for the values true and false,
  * which must begin with a lower-case letter.
  */


class      { return (CLASS); }
else      { return (ELSE); }
fi      { return (FI); }
if      { return (IF); }
in      { return (IN); }
inherits      { return (INHERITS); }
isvoid      { return (ISVOID); }
let      { return (LET); }
loop      { return (LOOP); }
pool      { return (POOL); }
then      { return (THEN); }
while      { return (WHILE); }
case      { return (CASE); }
esac      { return (ESAC); }
new      { return (NEW); }
of      { return (OF); }
not      { return (NOT); }
"+"      { return ('+'); }
"-"      { return ('-'); }
"*"      { return ('*'); }
"="      { return ('='); }
"<"      { return ('<'); }
\.      { return ('.'); }
"~"      { return ('~'); }
","      { return (','); }
";"      { return (';'); }
":"      { return (':'); }
"("      { return ('('); }
")"      { return (')'); }
"@"      { return ('@'); }
"{"      { return ('{'); }
"}"      { return ('}'); }

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
<IN_STRING>\"                               { BEGIN(INITIAL);
  
  cool_yylval.symbol = stringtable.add_string(const_cast<char *>(str_or_comment.c_str()));
  str_or_comment = "";
  return (STR_CONST);
 }

\"                                          { BEGIN(IN_STRING); }

<IN_STRING>\\[.\n]                          {
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

<IN_STRING>[\0]                       {
  cool_yylval.error_msg = "String contains null character";
  return (ERROR);
}
<IN_STRING>.                          { str_or_comment += yytext;}

{NEWLINE} { curr_lineno++; }
{WS} { }
. { cool_yylval.error_msg = "Unexpected character"; return (ERROR); } 

%%
