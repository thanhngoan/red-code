/* A Bison parser, made by GNU Bison 1.875.  */

/* Skeleton parser for Yacc-like parsing with Bison,
   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002 Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place - Suite 330,
   Boston, MA 02111-1307, USA.  */

/* As a special exception, when this file is copied by Bison into a
   Bison output file, you may use that output file without restriction.
   This special exception was added by the Free Software Foundation
   in version 1.24 of Bison.  */

/* Written by Richard Stallman by simplifying the original so called
   ``semantic'' parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Using locations.  */
#define YYLSP_NEEDED 0

/* If NAME_PREFIX is specified substitute the variables and functions
   names.  */
#define yyparse cool_yyparse
#define yylex   cool_yylex
#define yyerror cool_yyerror
#define yylval  cool_yylval
#define yychar  cool_yychar
#define yydebug cool_yydebug
#define yynerrs cool_yynerrs


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     CLASS = 258,
     ELSE = 259,
     FI = 260,
     IF = 261,
     IN = 262,
     INHERITS = 263,
     LET = 264,
     LOOP = 265,
     POOL = 266,
     THEN = 267,
     WHILE = 268,
     CASE = 269,
     ESAC = 270,
     OF = 271,
     DARROW = 272,
     NEW = 273,
     ISVOID = 274,
     STR_CONST = 275,
     INT_CONST = 276,
     BOOL_CONST = 277,
     TYPEID = 278,
     OBJECTID = 279,
     ASSIGN = 280,
     NOT = 281,
     LE = 282,
     ERROR = 283,
     LETPREC = 285
   };
#endif
#define CLASS 258
#define ELSE 259
#define FI 260
#define IF 261
#define IN 262
#define INHERITS 263
#define LET 264
#define LOOP 265
#define POOL 266
#define THEN 267
#define WHILE 268
#define CASE 269
#define ESAC 270
#define OF 271
#define DARROW 272
#define NEW 273
#define ISVOID 274
#define STR_CONST 275
#define INT_CONST 276
#define BOOL_CONST 277
#define TYPEID 278
#define OBJECTID 279
#define ASSIGN 280
#define NOT 281
#define LE 282
#define ERROR 283
#define LETPREC 285




/* Copy the first part of user declarations.  */
#line 6 "cool.y"

#include <iostream>
#include "cool-tree.h"
#include "stringtab.h"
#include "utilities.h"

#include <list>

#ifdef yylineno
#undef yylineno
#endif //yylineno

extern char *curr_filename;

void yyerror(char *s);        /*  defined below; called for each parse error */
extern int yylex();           /*  the entry point to the lexer  */


Expression makeDefaultExpression(Symbol ofType);

 struct Letvar {
   Letvar (Symbol i, Symbol t, Expression e) {id=i; type=t; expr=e; }
   Symbol id;
   Symbol type;
   Expression expr;
 };

template <class T>  List<T> * list_nth(List<T> *l, int index);


typedef List<Letvar> * Letvars;

/************************************************************************/
/*                DONT CHANGE ANYTHING IN THIS SECTION                  */


Program ast_root;	      /* the result of the parse  */
Classes parse_results;        /* for use in semantic analysis */
int omerrs = 0;               /* number of errors in lexing and parsing */


/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

#if ! defined (YYSTYPE) && ! defined (YYSTYPE_IS_DECLARED)
#line 48 "cool.y"
typedef union YYSTYPE {
  Boolean boolean;
  Symbol symbol;
  Program program;
  Class_ class_;
  Classes classes;
  Feature feature;
  Features features;
  Formal formal;
  Formals formals;
  Case case_;
  Cases cases;
  Expression expression;
  Expressions expressions;
  Letvar * letvar;
  Letvars letvars;
  char *error_msg;
} YYSTYPE;
/* Line 191 of yacc.c.  */
#line 202 "cool.tab.c"
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 214 of yacc.c.  */
#line 214 "cool.tab.c"

#if ! defined (yyoverflow) || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# if YYSTACK_USE_ALLOCA
#  define YYSTACK_ALLOC alloca
# else
#  ifndef YYSTACK_USE_ALLOCA
#   if defined (alloca) || defined (_ALLOCA_H)
#    define YYSTACK_ALLOC alloca
#   else
#    ifdef __GNUC__
#     define YYSTACK_ALLOC __builtin_alloca
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning. */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
# else
#  if defined (__STDC__) || defined (__cplusplus)
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   define YYSIZE_T size_t
#  endif
#  define YYSTACK_ALLOC malloc
#  define YYSTACK_FREE free
# endif
#endif /* ! defined (yyoverflow) || YYERROR_VERBOSE */


#if (! defined (yyoverflow) \
     && (! defined (__cplusplus) \
	 || (YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  short yyss;
  YYSTYPE yyvs;
  };

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (short) + sizeof (YYSTYPE))				\
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  register YYSIZE_T yyi;		\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (0)
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack)					\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack, Stack, yysize);				\
	Stack = &yyptr->Stack;						\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (0)

#endif

#if defined (__STDC__) || defined (__cplusplus)
   typedef signed char yysigned_char;
#else
   typedef short yysigned_char;
#endif

/* YYFINAL -- State number of the termination state. */
#define YYFINAL  7
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   303

/* YYNTOKENS -- Number of terminals. */
#define YYNTOKENS  46
/* YYNNTS -- Number of nonterminals. */
#define YYNNTS  16
/* YYNRULES -- Number of rules. */
#define YYNRULES  60
/* YYNRULES -- Number of states. */
#define YYNSTATES  150

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   285

#define YYTRANSLATE(YYX) 						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const unsigned char yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
      42,    43,    34,    32,    45,    33,    38,    35,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    44,    41,
      30,    31,     2,     2,    37,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    39,     2,    40,    36,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,     2,    29
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const unsigned char yyprhs[] =
{
       0,     0,     3,     5,     7,    10,    17,    26,    33,    40,
      47,    49,    50,    52,    55,    66,    71,    78,    80,    84,
      86,    90,    91,    95,   104,   111,   116,   124,   130,   134,
     139,   145,   148,   151,   155,   159,   163,   167,   170,   174,
     178,   182,   185,   189,   191,   193,   195,   197,   199,   202,
     209,   212,   216,   218,   222,   223,   229,   233,   235,   237,
     241
};

/* YYRHS -- A `-1'-separated list of the rules' RHS. */
static const yysigned_char yyrhs[] =
{
      47,     0,    -1,    48,    -1,    49,    -1,    48,    49,    -1,
       3,    23,    39,    50,    40,    41,    -1,     3,    23,     8,
      23,    39,    50,    40,    41,    -1,     3,    23,    39,     1,
      40,    41,    -1,     3,     1,    39,    50,    40,    41,    -1,
       3,     1,    39,     1,    40,    41,    -1,    51,    -1,    -1,
      52,    -1,    51,    52,    -1,    24,    42,    54,    43,    44,
      23,    39,    55,    40,    41,    -1,    24,    44,    23,    41,
      -1,    24,    44,    23,    25,    55,    41,    -1,     1,    -1,
      24,    44,    23,    -1,    53,    -1,    54,    45,    53,    -1,
      -1,    24,    25,    55,    -1,    55,    37,    23,    38,    24,
      42,    59,    43,    -1,    55,    38,    24,    42,    59,    43,
      -1,    24,    42,    59,    43,    -1,     6,    55,    12,    55,
       4,    55,     5,    -1,    13,    55,    10,    55,    11,    -1,
      39,    58,    40,    -1,     9,    61,     7,    55,    -1,    14,
      55,    16,    56,    15,    -1,    18,    23,    -1,    19,    55,
      -1,    55,    32,    55,    -1,    55,    33,    55,    -1,    55,
      34,    55,    -1,    55,    35,    55,    -1,    36,    55,    -1,
      55,    30,    55,    -1,    55,    27,    55,    -1,    55,    31,
      55,    -1,    26,    55,    -1,    42,    55,    43,    -1,    24,
      -1,    21,    -1,    20,    -1,    22,    -1,    57,    -1,    56,
      57,    -1,    24,    44,    23,    17,    55,    41,    -1,    55,
      41,    -1,    58,    55,    41,    -1,    55,    -1,    59,    45,
      55,    -1,    -1,    24,    44,    23,    25,    55,    -1,    24,
      44,    23,    -1,     1,    -1,    60,    -1,    60,    45,    61,
      -1,    60,     1,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const unsigned short yyrline[] =
{
       0,   130,   130,   134,   145,   163,   166,   168,   169,   170,
     176,   178,   183,   190,   204,   207,   209,   210,   214,   218,
     219,   220,   224,   226,   229,   232,   236,   238,   241,   248,
     293,   297,   298,   301,   302,   303,   304,   307,   310,   311,
     312,   313,   316,   319,   320,   321,   322,   325,   326,   329,
     333,   334,   338,   339,   340,   344,   345,   346,   349,   356,
     365
};
#endif

#if YYDEBUG || YYERROR_VERBOSE
/* YYTNME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals. */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "CLASS", "ELSE", "FI", "IF", "IN", 
  "INHERITS", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", 
  "OF", "DARROW", "NEW", "ISVOID", "STR_CONST", "INT_CONST", "BOOL_CONST", 
  "TYPEID", "OBJECTID", "ASSIGN", "NOT", "LE", "ERROR", "LETPREC", "'<'", 
  "'='", "'+'", "'-'", "'*'", "'/'", "'~'", "'@'", "'.'", "'{'", "'}'", 
  "';'", "'('", "')'", "':'", "','", "$accept", "program", "class_list", 
  "class", "feature_list", "nonempty_feature_list", "feature", "formal", 
  "method_formals", "expr", "case_branches", "case_branch", 
  "oneormore_expr", "comma_delimited_exprs", "letvar", "letvars", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const unsigned short yytoknum[] =
{
       0,   256,   284,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   285,
      60,    61,    43,    45,    42,    47,   126,    64,    46,   123,
     125,    59,    40,    41,    58,    44
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const unsigned char yyr1[] =
{
       0,    46,    47,    48,    48,    49,    49,    49,    49,    49,
      50,    50,    51,    51,    52,    52,    52,    52,    53,    54,
      54,    54,    55,    55,    55,    55,    55,    55,    55,    55,
      55,    55,    55,    55,    55,    55,    55,    55,    55,    55,
      55,    55,    55,    55,    55,    55,    55,    56,    56,    57,
      58,    58,    59,    59,    59,    60,    60,    60,    61,    61,
      61
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const unsigned char yyr2[] =
{
       0,     2,     1,     1,     2,     6,     8,     6,     6,     6,
       1,     0,     1,     2,    10,     4,     6,     1,     3,     1,
       3,     0,     3,     8,     6,     4,     7,     5,     3,     4,
       5,     2,     2,     3,     3,     3,     3,     2,     3,     3,
       3,     2,     3,     1,     1,     1,     1,     1,     2,     6,
       2,     3,     1,     3,     0,     5,     3,     1,     1,     3,
       2
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const unsigned char yydefact[] =
{
       0,     0,     0,     2,     3,     0,     0,     1,     4,     0,
       0,     0,    17,     0,     0,     0,    12,     0,    17,     0,
       0,    21,     0,     0,    17,    13,     0,     0,     0,     9,
       0,    19,     0,     0,     8,     0,     7,     5,     0,     0,
       0,     0,    15,     0,    18,     0,    20,     0,     0,     0,
       0,     0,     0,    45,    44,    46,    43,     0,     0,     0,
       0,     0,     6,     0,     0,    57,     0,     0,     0,     0,
       0,    31,    32,     0,    54,    41,    37,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    16,
       0,     0,     0,    60,     0,     0,     0,     0,    22,    52,
       0,    50,    28,     0,    42,    39,    38,    40,    33,    34,
      35,    36,     0,     0,     0,     0,    56,    59,    29,     0,
       0,     0,    47,    25,     0,    51,     0,    54,     0,     0,
       0,    27,     0,    30,    48,    53,     0,     0,    14,     0,
      55,     0,    54,    24,    26,     0,     0,     0,    23,    49
};

/* YYDEFGOTO[NTERM-NUM]. */
static const yysigned_char yydefgoto[] =
{
      -1,     2,     3,     4,    14,    15,    16,    31,    32,    99,
     121,   122,    78,   100,    67,    68
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -114
static const short yypact[] =
{
      25,     0,    56,    25,  -114,   -12,    -6,  -114,  -114,     6,
      43,    11,    28,    32,    45,    12,  -114,    47,    52,    55,
      26,    75,    77,    62,  -114,  -114,    23,    70,    72,  -114,
      73,  -114,    35,   -10,  -114,    76,  -114,  -114,    95,    78,
      75,    88,  -114,    79,  -114,    96,  -114,    88,     2,    88,
      88,    98,    88,  -114,  -114,  -114,   -20,    88,    88,    88,
      88,   196,  -114,    84,   153,  -114,    81,     3,   151,   119,
     173,  -114,   -17,    88,    88,   256,   -17,   208,    51,   182,
      88,    88,    88,    88,    88,    88,    88,   103,   135,  -114,
      88,    88,   137,  -114,     2,    88,    88,   138,   256,   256,
      36,  -114,  -114,   220,  -114,   265,   265,   265,    24,    24,
     -17,   -17,   123,   121,   244,   101,   139,  -114,   256,   144,
     122,    10,  -114,  -114,    88,  -114,   143,    88,   127,    88,
      88,  -114,   146,  -114,  -114,   256,   128,    39,  -114,   110,
     256,   155,    88,  -114,  -114,    88,    53,   232,  -114,  -114
};

/* YYPGOTO[NTERM-NUM].  */
static const short yypgoto[] =
{
    -114,  -114,  -114,   170,    27,  -114,   177,   154,  -114,   -41,
    -114,    74,  -114,  -113,  -114,    99
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -59
static const short yytable[] =
{
      61,     5,    10,    65,    93,    73,    64,    12,    69,    70,
     -58,    72,    18,    24,   137,    41,    75,    76,    77,    79,
      87,    88,    74,     6,    24,   133,    66,     9,     1,   146,
      13,    42,    98,    11,   120,    13,    13,   103,    19,   105,
     106,   107,   108,   109,   110,   111,   -11,    13,    94,   114,
     115,   -11,   -10,    35,   118,   119,     7,    47,    85,    86,
      48,    87,    88,   -11,    49,    50,    17,    29,    20,    51,
      52,    53,    54,    55,    21,    56,    22,    57,    39,   123,
      40,   124,   143,   135,   124,    23,    26,    58,   139,   140,
      59,   102,    27,    60,    47,    28,   148,    48,   124,    30,
      33,    49,    50,    34,   147,   129,    51,    52,    53,    54,
      55,    36,    56,    37,    57,   144,    43,    38,    44,    63,
      62,    71,    45,    90,    58,    92,   112,    59,    80,    96,
      60,    81,    82,    83,    84,    85,    86,    80,    87,    88,
      81,    82,    83,    84,    85,    86,    80,    87,    88,    81,
      82,    83,    84,    85,    86,   131,    87,    88,    95,   113,
     116,   126,   120,   127,   130,    91,   132,   136,   138,   141,
     142,    80,   145,     8,    81,    82,    83,    84,    85,    86,
      80,    87,    88,    81,    82,    83,    84,    85,    86,    97,
      87,    88,    25,   117,    46,   134,     0,     0,     0,     0,
      80,     0,     0,    81,    82,    83,    84,    85,    86,    80,
      87,    88,    81,    82,    83,    84,    85,    86,     0,    87,
      88,     0,     0,    80,     0,   104,    81,    82,    83,    84,
      85,    86,     0,    87,    88,    80,     0,    89,    81,    82,
      83,    84,    85,    86,     0,    87,    88,    80,     0,   101,
      81,    82,    83,    84,    85,    86,     0,    87,    88,    80,
       0,   125,    81,    82,    83,    84,    85,    86,     0,    87,
      88,    80,     0,   149,    81,    82,    83,    84,    85,    86,
       0,    87,    88,    80,   128,     0,    81,    82,    83,    84,
      85,    86,   -59,    87,    88,   -59,   -59,    83,    84,    85,
      86,     0,    87,    88
};

static const short yycheck[] =
{
      41,     1,     8,     1,     1,    25,    47,     1,    49,    50,
       7,    52,     1,     1,   127,    25,    57,    58,    59,    60,
      37,    38,    42,    23,     1,    15,    24,    39,     3,   142,
      24,    41,    73,    39,    24,    24,    24,    78,    11,    80,
      81,    82,    83,    84,    85,    86,    40,    24,    45,    90,
      91,    40,    40,    26,    95,    96,     0,     6,    34,    35,
       9,    37,    38,    40,    13,    14,    23,    41,    40,    18,
      19,    20,    21,    22,    42,    24,    44,    26,    43,    43,
      45,    45,    43,   124,    45,    40,    39,    36,   129,   130,
      39,    40,    40,    42,     6,    40,    43,     9,    45,    24,
      23,    13,    14,    41,   145,     4,    18,    19,    20,    21,
      22,    41,    24,    41,    26,     5,    40,    44,    23,    23,
      41,    23,    44,    39,    36,    44,    23,    39,    27,    10,
      42,    30,    31,    32,    33,    34,    35,    27,    37,    38,
      30,    31,    32,    33,    34,    35,    27,    37,    38,    30,
      31,    32,    33,    34,    35,    11,    37,    38,     7,    24,
      23,    38,    24,    42,    25,    12,    44,    24,    41,    23,
      42,    27,    17,     3,    30,    31,    32,    33,    34,    35,
      27,    37,    38,    30,    31,    32,    33,    34,    35,    16,
      37,    38,    15,    94,    40,   121,    -1,    -1,    -1,    -1,
      27,    -1,    -1,    30,    31,    32,    33,    34,    35,    27,
      37,    38,    30,    31,    32,    33,    34,    35,    -1,    37,
      38,    -1,    -1,    27,    -1,    43,    30,    31,    32,    33,
      34,    35,    -1,    37,    38,    27,    -1,    41,    30,    31,
      32,    33,    34,    35,    -1,    37,    38,    27,    -1,    41,
      30,    31,    32,    33,    34,    35,    -1,    37,    38,    27,
      -1,    41,    30,    31,    32,    33,    34,    35,    -1,    37,
      38,    27,    -1,    41,    30,    31,    32,    33,    34,    35,
      -1,    37,    38,    27,    40,    -1,    30,    31,    32,    33,
      34,    35,    27,    37,    38,    30,    31,    32,    33,    34,
      35,    -1,    37,    38
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const unsigned char yystos[] =
{
       0,     3,    47,    48,    49,     1,    23,     0,    49,    39,
       8,    39,     1,    24,    50,    51,    52,    23,     1,    50,
      40,    42,    44,    40,     1,    52,    39,    40,    40,    41,
      24,    53,    54,    23,    41,    50,    41,    41,    44,    43,
      45,    25,    41,    40,    23,    44,    53,     6,     9,    13,
      14,    18,    19,    20,    21,    22,    24,    26,    36,    39,
      42,    55,    41,    23,    55,     1,    24,    60,    61,    55,
      55,    23,    55,    25,    42,    55,    55,    55,    58,    55,
      27,    30,    31,    32,    33,    34,    35,    37,    38,    41,
      39,    12,    44,     1,    45,     7,    10,    16,    55,    55,
      59,    41,    40,    55,    43,    55,    55,    55,    55,    55,
      55,    55,    23,    24,    55,    55,    23,    61,    55,    55,
      24,    56,    57,    43,    45,    41,    38,    42,    40,     4,
      25,    11,    44,    15,    57,    55,    24,    59,    41,    55,
      55,    23,    42,    43,     5,    17,    59,    55,    43,    41
};

#if ! defined (YYSIZE_T) && defined (__SIZE_TYPE__)
# define YYSIZE_T __SIZE_TYPE__
#endif
#if ! defined (YYSIZE_T) && defined (size_t)
# define YYSIZE_T size_t
#endif
#if ! defined (YYSIZE_T)
# if defined (__STDC__) || defined (__cplusplus)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# endif
#endif
#if ! defined (YYSIZE_T)
# define YYSIZE_T unsigned int
#endif

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrlab1

/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK;						\
      goto yybackup;						\
    }								\
  else								\
    { 								\
      yyerror ("syntax error: cannot back up");\
      YYERROR;							\
    }								\
while (0)

#define YYTERROR	1
#define YYERRCODE	256

/* YYLLOC_DEFAULT -- Compute the default location (before the actions
   are run).  */

#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)         \
  Current.first_line   = Rhs[1].first_line;      \
  Current.first_column = Rhs[1].first_column;    \
  Current.last_line    = Rhs[N].last_line;       \
  Current.last_column  = Rhs[N].last_column;
#endif

/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (0)

# define YYDSYMPRINT(Args)			\
do {						\
  if (yydebug)					\
    yysymprint Args;				\
} while (0)

# define YYDSYMPRINTF(Title, Token, Value, Location)		\
do {								\
  if (yydebug)							\
    {								\
      YYFPRINTF (stderr, "%s ", Title);				\
      yysymprint (stderr, 					\
                  Token, Value);	\
      YYFPRINTF (stderr, "\n");					\
    }								\
} while (0)

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (cinluded).                                                   |
`------------------------------------------------------------------*/

#if defined (__STDC__) || defined (__cplusplus)
static void
yy_stack_print (short *bottom, short *top)
#else
static void
yy_stack_print (bottom, top)
    short *bottom;
    short *top;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (/* Nothing. */; bottom <= top; ++bottom)
    YYFPRINTF (stderr, " %d", *bottom);
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if defined (__STDC__) || defined (__cplusplus)
static void
yy_reduce_print (int yyrule)
#else
static void
yy_reduce_print (yyrule)
    int yyrule;
#endif
{
  int yyi;
  unsigned int yylineno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %u), ",
             yyrule - 1, yylineno);
  /* Print the symbols being reduced, and their result.  */
  for (yyi = yyprhs[yyrule]; 0 <= yyrhs[yyi]; yyi++)
    YYFPRINTF (stderr, "%s ", yytname [yyrhs[yyi]]);
  YYFPRINTF (stderr, "-> %s\n", yytname [yyr1[yyrule]]);
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (Rule);		\
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YYDSYMPRINT(Args)
# define YYDSYMPRINTF(Title, Token, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   SIZE_MAX < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#if YYMAXDEPTH == 0
# undef YYMAXDEPTH
#endif

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined (__GLIBC__) && defined (_STRING_H)
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
#   if defined (__STDC__) || defined (__cplusplus)
yystrlen (const char *yystr)
#   else
yystrlen (yystr)
     const char *yystr;
#   endif
{
  register const char *yys = yystr;

  while (*yys++ != '\0')
    continue;

  return yys - yystr - 1;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined (__GLIBC__) && defined (_STRING_H) && defined (_GNU_SOURCE)
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
#   if defined (__STDC__) || defined (__cplusplus)
yystpcpy (char *yydest, const char *yysrc)
#   else
yystpcpy (yydest, yysrc)
     char *yydest;
     const char *yysrc;
#   endif
{
  register char *yyd = yydest;
  register const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

#endif /* !YYERROR_VERBOSE */



#if YYDEBUG
/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if defined (__STDC__) || defined (__cplusplus)
static void
yysymprint (FILE *yyoutput, int yytype, YYSTYPE *yyvaluep)
#else
static void
yysymprint (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  /* Pacify ``unused variable'' warnings.  */
  (void) yyvaluep;

  if (yytype < YYNTOKENS)
    {
      YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
# ifdef YYPRINT
      YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
    }
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  switch (yytype)
    {
      default:
        break;
    }
  YYFPRINTF (yyoutput, ")");
}

#endif /* ! YYDEBUG */
/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

#if defined (__STDC__) || defined (__cplusplus)
static void
yydestruct (int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yytype, yyvaluep)
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  /* Pacify ``unused variable'' warnings.  */
  (void) yyvaluep;

  switch (yytype)
    {

      default:
        break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */

#ifdef YYPARSE_PARAM
# if defined (__STDC__) || defined (__cplusplus)
int yyparse (void *YYPARSE_PARAM);
# else
int yyparse ();
# endif
#else /* ! YYPARSE_PARAM */
#if defined (__STDC__) || defined (__cplusplus)
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */



/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
# if defined (__STDC__) || defined (__cplusplus)
int yyparse (void *YYPARSE_PARAM)
# else
int yyparse (YYPARSE_PARAM)
  void *YYPARSE_PARAM;
# endif
#else /* ! YYPARSE_PARAM */
#if defined (__STDC__) || defined (__cplusplus)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
  
  register int yystate;
  register int yyn;
  int yyresult;
  /* Number of tokens to shift before error messages enabled.  */
  int yyerrstatus;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;

  /* Three stacks and their tools:
     `yyss': related to states,
     `yyvs': related to semantic values,
     `yyls': related to locations.

     Refer to the stacks thru separate pointers, to allow yyoverflow
     to reallocate them elsewhere.  */

  /* The state stack.  */
  short	yyssa[YYINITDEPTH];
  short *yyss = yyssa;
  register short *yyssp;

  /* The semantic value stack.  */
  YYSTYPE yyvsa[YYINITDEPTH];
  YYSTYPE *yyvs = yyvsa;
  register YYSTYPE *yyvsp;



#define YYPOPSTACK   (yyvsp--, yyssp--)

  YYSIZE_T yystacksize = YYINITDEPTH;

  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;


  /* When reducing, the number of symbols on the RHS of the reduced
     rule.  */
  int yylen;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed. so pushing a state here evens the stacks.
     */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack. Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	short *yyss1 = yyss;


	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow ("parser stack overflow",
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),

		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyoverflowlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyoverflowlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	short *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyoverflowlab;
	YYSTACK_RELOCATE (yyss);
	YYSTACK_RELOCATE (yyvs);

#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;


      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

/* Do appropriate processing given the current state.  */
/* Read a lookahead token if we need one and don't already have one.  */
/* yyresume: */

  /* First try to decide what to do without reference to lookahead token.  */

  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YYDSYMPRINTF ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Shift the lookahead token.  */
  YYDPRINTF ((stderr, "Shifting token %s, ", yytname[yytoken]));

  /* Discard the token being shifted unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  *++yyvsp = yylval;


  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  yystate = yyn;
  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 130 "cool.y"
    { ast_root = program(yyvsp[0].classes); }
    break;

  case 3:
#line 135 "cool.y"
    {
  // $1 will be NULL when it encountered a parse error
  if (yyvsp[0].class_)
    {
      yyval.classes = single_Classes(yyvsp[0].class_);
      parse_results = yyval.classes;
    }
  else
      yyval.classes = NULL;
}
    break;

  case 4:
#line 146 "cool.y"
    {
  if (yyvsp[-1].classes && yyvsp[0].class_)
    {
      yyval.classes = append_Classes(yyvsp[-1].classes,single_Classes(yyvsp[0].class_)); 
      parse_results = yyval.classes;
    }
  else if (yyvsp[-1].classes)
    yyval.classes = yyvsp[-1].classes;
  else if (yyvsp[0].class_)
    yyval.classes = single_Classes(yyvsp[0].class_);
  else
      yyval.classes = nil_Classes();
}
    break;

  case 5:
#line 164 "cool.y"
    { yyval.class_ = class_(yyvsp[-4].symbol,idtable.add_string("Object"),yyvsp[-2].features,
              stringtable.add_string(curr_filename)); }
    break;

  case 6:
#line 167 "cool.y"
    { yyval.class_ = class_(yyvsp[-6].symbol,yyvsp[-4].symbol,yyvsp[-2].features,stringtable.add_string(curr_filename)); }
    break;

  case 7:
#line 168 "cool.y"
    { yyclearin; yyval.class_ = NULL; }
    break;

  case 8:
#line 169 "cool.y"
    { yyclearin; yyval.class_ = NULL;}
    break;

  case 9:
#line 170 "cool.y"
    { yyclearin; yyval.class_ = NULL;}
    break;

  case 10:
#line 176 "cool.y"
    { yyval.features = yyvsp[0].features != NULL ? yyvsp[0].features : nil_Features(); }
    break;

  case 11:
#line 178 "cool.y"
    {  yyval.features = nil_Features(); }
    break;

  case 12:
#line 183 "cool.y"
    {
  if (yyvsp[0].feature)
    yyval.features = single_Features(yyvsp[0].feature);
  else
    yyval.features = NULL;
}
    break;

  case 13:
#line 190 "cool.y"
    {
  if (yyvsp[-1].features && yyvsp[0].feature)
    yyval.features = append_Features(yyvsp[-1].features, single_Features(yyvsp[0].feature));
  else if (yyvsp[-1].features)
    yyval.features = yyvsp[-1].features;
  else if (yyvsp[0].feature)
    yyval.features = single_Features(yyvsp[0].feature);
  else
    yyval.features = NULL;
}
    break;

  case 14:
#line 205 "cool.y"
    { yyval.feature = method(yyvsp[-9].symbol, yyvsp[-7].formals, yyvsp[-4].symbol, yyvsp[-2].expression); }
    break;

  case 15:
#line 207 "cool.y"
    { yyval.feature = attr(yyvsp[-3].symbol, yyvsp[-1].symbol, makeDefaultExpression(yyvsp[-1].symbol)); }
    break;

  case 16:
#line 209 "cool.y"
    { yyval.feature = attr(yyvsp[-5].symbol, yyvsp[-3].symbol, yyvsp[-1].expression); }
    break;

  case 17:
#line 210 "cool.y"
    { yyclearin; yyval.feature = NULL; }
    break;

  case 18:
#line 214 "cool.y"
    { yyval.formal = formal(yyvsp[-2].symbol, yyvsp[0].symbol); }
    break;

  case 19:
#line 218 "cool.y"
    { yyval.formals = single_Formals(yyvsp[0].formal); }
    break;

  case 20:
#line 219 "cool.y"
    { yyval.formals = append_Formals(yyvsp[-2].formals, single_Formals(yyvsp[0].formal));}
    break;

  case 21:
#line 220 "cool.y"
    { yyval.formals = nil_Formals(); }
    break;

  case 22:
#line 224 "cool.y"
    { yyval.expression = assign(yyvsp[-2].symbol, yyvsp[0].expression); }
    break;

  case 23:
#line 227 "cool.y"
    { yyval.expression = static_dispatch(yyvsp[-7].expression, yyvsp[-5].symbol, yyvsp[-3].symbol, yyvsp[-1].expressions); }
    break;

  case 24:
#line 230 "cool.y"
    { yyval.expression = dispatch(yyvsp[-5].expression, yyvsp[-3].symbol, yyvsp[-1].expressions); }
    break;

  case 25:
#line 233 "cool.y"
    { yyval.expression =  dispatch(object(idtable.add_string("self")), yyvsp[-3].symbol, yyvsp[-1].expressions);}
    break;

  case 26:
#line 236 "cool.y"
    { yyval.expression =  cond(yyvsp[-5].expression, yyvsp[-3].expression, yyvsp[-1].expression);}
    break;

  case 27:
#line 238 "cool.y"
    { yyval.expression =  loop(yyvsp[-3].expression, yyvsp[-1].expression);}
    break;

  case 28:
#line 241 "cool.y"
    { yyval.expression =  block(yyvsp[-1].expressions);}
    break;

  case 29:
#line 249 "cool.y"
    {
  // $2 is NULL if an error was encountered while parsing the args
  if (yyvsp[-2].letvars)
    {
      List<Letvar> * vars = yyvsp[-2].letvars;
      int num_vars = list_length(vars);
      
      // store the next-inner expression here
      // decrement this as we go through the list until it, using it
      // as the next let body, until it is the outter-most expression
      Expression inner_expr = NULL;
      
      // go through each let variable and create a new
      // binding (let expression)
      for (int i = num_vars-1; i >= 0; i--)
        {
          List<Letvar> * item = list_nth(vars, i);
          
          Expression body = inner_expr == NULL ? yyvsp[0].expression : inner_expr;
          
          inner_expr = let(item->hd()->id,
                           item->hd()->type,
                           item->hd()->expr,
                           body);
        }
      yyval.expression = inner_expr;
    }
  else
    {
      YYERROR; //signal error if there are no valid lets
      cerr << "signaling an error" << endl;
    }
}
    break;

  case 30:
#line 294 "cool.y"
    { yyval.expression = typcase(yyvsp[-3].expression, yyvsp[-1].cases); }
    break;

  case 31:
#line 297 "cool.y"
    { yyval.expression = new_(yyvsp[0].symbol); }
    break;

  case 32:
#line 298 "cool.y"
    { yyval.expression = isvoid(yyvsp[0].expression); }
    break;

  case 33:
#line 301 "cool.y"
    { yyval.expression = plus(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 34:
#line 302 "cool.y"
    { yyval.expression = sub(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 35:
#line 303 "cool.y"
    { yyval.expression = mul(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 36:
#line 304 "cool.y"
    { yyval.expression = divide(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 37:
#line 307 "cool.y"
    { yyval.expression = neg(yyvsp[0].expression); }
    break;

  case 38:
#line 310 "cool.y"
    { yyval.expression = lt(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 39:
#line 311 "cool.y"
    { yyval.expression = leq(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 40:
#line 312 "cool.y"
    { yyval.expression = eq(yyvsp[-2].expression, yyvsp[0].expression); }
    break;

  case 41:
#line 313 "cool.y"
    { yyval.expression = comp(yyvsp[0].expression); }
    break;

  case 42:
#line 316 "cool.y"
    { yyval.expression = yyvsp[-1].expression; }
    break;

  case 43:
#line 319 "cool.y"
    { yyval.expression = object(yyvsp[0].symbol); }
    break;

  case 44:
#line 320 "cool.y"
    { yyval.expression = int_const(yyvsp[0].symbol);  }
    break;

  case 45:
#line 321 "cool.y"
    { yyval.expression = string_const(yyvsp[0].symbol); }
    break;

  case 46:
#line 322 "cool.y"
    { yyval.expression = bool_const(yyvsp[0].boolean); }
    break;

  case 47:
#line 325 "cool.y"
    { yyval.cases = single_Cases(yyvsp[0].case_); }
    break;

  case 48:
#line 326 "cool.y"
    { yyval.cases = append_Cases(yyvsp[-1].cases, single_Cases(yyvsp[0].case_));}
    break;

  case 49:
#line 330 "cool.y"
    { yyval.case_ = branch(yyvsp[-5].symbol, yyvsp[-3].symbol, yyvsp[-1].expression); }
    break;

  case 50:
#line 333 "cool.y"
    { yyval.expressions = single_Expressions(yyvsp[-1].expression); }
    break;

  case 51:
#line 334 "cool.y"
    { yyval.expressions = append_Expressions(yyvsp[-2].expressions, single_Expressions(yyvsp[-1].expression)); }
    break;

  case 52:
#line 338 "cool.y"
    { yyval.expressions = single_Expressions(yyvsp[0].expression); }
    break;

  case 53:
#line 339 "cool.y"
    { yyval.expressions = append_Expressions(yyvsp[-2].expressions, single_Expressions(yyvsp[0].expression)); }
    break;

  case 54:
#line 340 "cool.y"
    {yyval.expressions = nil_Expressions(); }
    break;

  case 55:
#line 344 "cool.y"
    { yyval.letvar = new Letvar(yyvsp[-4].symbol, yyvsp[-2].symbol, yyvsp[0].expression); }
    break;

  case 56:
#line 345 "cool.y"
    {yyval.letvar = new Letvar(yyvsp[-2].symbol, yyvsp[0].symbol, no_expr()); }
    break;

  case 57:
#line 346 "cool.y"
    { yyclearin; yyerrok; yyval.letvar = NULL; }
    break;

  case 58:
#line 349 "cool.y"
    {
  // if $1 is null there was an error in parsing
  if (yyvsp[0].letvar)
    yyval.letvars = new List<Letvar>(yyvsp[0].letvar);
  else
      yyval.letvars = NULL;
}
    break;

  case 59:
#line 357 "cool.y"
    {
  if (yyvsp[-2].letvar)
    yyval.letvars = new List<Letvar>(yyvsp[-2].letvar, yyvsp[0].letvars);
  else if (yyvsp[0].letvars)
    yyval.letvars = yyvsp[0].letvars;
  else
    yyval.letvars = NULL;
}
    break;

  case 60:
#line 365 "cool.y"
    { yyclearin; yyerrok; yyval.letvars = new List<Letvar>(yyvsp[-1].letvar, NULL); }
    break;


    }

/* Line 991 of yacc.c.  */
#line 1616 "cool.tab.c"

  yyvsp -= yylen;
  yyssp -= yylen;


  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;


  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if YYERROR_VERBOSE
      yyn = yypact[yystate];

      if (YYPACT_NINF < yyn && yyn < YYLAST)
	{
	  YYSIZE_T yysize = 0;
	  int yytype = YYTRANSLATE (yychar);
	  char *yymsg;
	  int yyx, yycount;

	  yycount = 0;
	  /* Start YYX at -YYN if negative to avoid negative indexes in
	     YYCHECK.  */
	  for (yyx = yyn < 0 ? -yyn : 0;
	       yyx < (int) (sizeof (yytname) / sizeof (char *)); yyx++)
	    if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	      yysize += yystrlen (yytname[yyx]) + 15, yycount++;
	  yysize += yystrlen ("syntax error, unexpected ") + 1;
	  yysize += yystrlen (yytname[yytype]);
	  yymsg = (char *) YYSTACK_ALLOC (yysize);
	  if (yymsg != 0)
	    {
	      char *yyp = yystpcpy (yymsg, "syntax error, unexpected ");
	      yyp = yystpcpy (yyp, yytname[yytype]);

	      if (yycount < 5)
		{
		  yycount = 0;
		  for (yyx = yyn < 0 ? -yyn : 0;
		       yyx < (int) (sizeof (yytname) / sizeof (char *));
		       yyx++)
		    if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
		      {
			const char *yyq = ! yycount ? ", expecting " : " or ";
			yyp = yystpcpy (yyp, yyq);
			yyp = yystpcpy (yyp, yytname[yyx]);
			yycount++;
		      }
		}
	      yyerror (yymsg);
	      YYSTACK_FREE (yymsg);
	    }
	  else
	    yyerror ("syntax error; also virtual memory exhausted");
	}
      else
#endif /* YYERROR_VERBOSE */
	yyerror ("syntax error");
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      /* Return failure if at end of input.  */
      if (yychar == YYEOF)
        {
	  /* Pop the error token.  */
          YYPOPSTACK;
	  /* Pop the rest of the stack.  */
	  while (yyss < yyssp)
	    {
	      YYDSYMPRINTF ("Error: popping", yystos[*yyssp], yyvsp, yylsp);
	      yydestruct (yystos[*yyssp], yyvsp);
	      YYPOPSTACK;
	    }
	  YYABORT;
        }

      YYDSYMPRINTF ("Error: discarding", yytoken, &yylval, &yylloc);
      yydestruct (yytoken, &yylval);
      yychar = YYEMPTY;

    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab2;


/*----------------------------------------------------.
| yyerrlab1 -- error raised explicitly by an action.  |
`----------------------------------------------------*/
yyerrlab1:


  goto yyerrlab2;


/*---------------------------------------------------------------.
| yyerrlab2 -- pop states until the error token can be shifted.  |
`---------------------------------------------------------------*/
yyerrlab2:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;

      YYDSYMPRINTF ("Error: popping", yystos[*yyssp], yyvsp, yylsp);
      yydestruct (yystos[yystate], yyvsp);
      yyvsp--;
      yystate = *--yyssp;

      YY_STACK_PRINT (yyss, yyssp);
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  YYDPRINTF ((stderr, "Shifting error token, "));

  *++yyvsp = yylval;


  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#ifndef yyoverflow
/*----------------------------------------------.
| yyoverflowlab -- parser overflow comes here.  |
`----------------------------------------------*/
yyoverflowlab:
  yyerror ("parser stack overflow");
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
  return yyresult;
}


#line 130 "cool.y"


/* makes a new expression with the correct default value for the given type. */
Expression makeDefaultExpression(Symbol ofType)
{
  if (strcmp(ofType->get_string(), "Bool") == 0)
    return bool_const(false);

  else if (strcmp(ofType->get_string(), "Int") == 0)
    return int_const(inttable.add_string("0"));

  else if (strcmp(ofType->get_string(), "String") == 0)
    return string_const(stringtable.add_string(""));

  else
    return no_expr();
}

/* This function is called automatically when Bison detects a parse error. */
void yyerror(char *s)
{
  extern int curr_lineno;

  cerr << "\"" << curr_filename << "\", line " << curr_lineno << ": " \
    << s << " at or near ";
  print_cool_token(yychar);
  cerr << endl;
  omerrs++;
  omerrs--;

  if(omerrs>50) {fprintf(stdout, "More than 50 errors\n"); exit(1);}
}


//gets the nth item from the list
template <class T>
  List<T> * list_nth(List<T> *l, int index)
{
  int i = 0;
  for (; l != NULL; l = l->tl())
    if (i++ == index)
      return l;
  return NULL;
}

