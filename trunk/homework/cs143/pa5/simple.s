# start of generated code
	.data
	.align	2
	.globl	class_nameTab
	.globl	Main_protObj
	.globl	Int_protObj
	.globl	String_protObj
	.globl	bool_const0
	.globl	bool_const1
	.globl	_int_tag
	.globl	_bool_tag
	.globl	_string_tag
_int_tag:
	.word	2
_bool_tag:
	.word	3
_string_tag:
	.word	4
	.globl	_MemMgr_INITIALIZER
_MemMgr_INITIALIZER:
	.word	_NoGC_Init
	.globl	_MemMgr_COLLECTOR
_MemMgr_COLLECTOR:
	.word	_NoGC_Collect
	.globl	_MemMgr_TEST
_MemMgr_TEST:
	.word	0
	.word	-1
str_const11:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const3
	.byte	0	
	.align	2
	.word	-1
str_const10:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Main"
	.byte	0	
	.align	2
	.word	-1
str_const9:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const2
	.ascii	"String"
	.byte	0	
	.align	2
	.word	-1
str_const8:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Bool"
	.byte	0	
	.align	2
	.word	-1
str_const7:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const5
	.ascii	"Int"
	.byte	0	
	.align	2
	.word	-1
str_const6:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const6
	.ascii	"IO"
	.byte	0	
	.align	2
	.word	-1
str_const5:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const2
	.ascii	"Object"
	.byte	0	
	.align	2
	.word	-1
str_const4:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const7
	.ascii	"_prim_slot"
	.byte	0	
	.align	2
	.word	-1
str_const3:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const8
	.ascii	"SELF_TYPE"
	.byte	0	
	.align	2
	.word	-1
str_const2:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const8
	.ascii	"_no_class"
	.byte	0	
	.align	2
	.word	-1
str_const1:
	.word	4
	.word	8
	.word	String_dispTab
	.word	int_const9
	.ascii	"<basic class>"
	.byte	0	
	.align	2
	.word	-1
str_const0:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const8
	.ascii	"simple.cl"
	.byte	0	
	.align	2
	.word	-1
int_const9:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	13
	.word	-1
int_const8:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	9
	.word	-1
int_const7:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	10
	.word	-1
int_const6:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	2
	.word	-1
int_const5:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	3
	.word	-1
int_const4:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	4
	.word	-1
int_const3:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	0
	.word	-1
int_const2:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	6
	.word	-1
int_const1:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	1
	.word	-1
int_const0:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	5
	.word	-1
bool_const0:
	.word	3
	.word	4
	.word	Bool_dispTab
	.word	0
	.word	-1
bool_const1:
	.word	3
	.word	4
	.word	Bool_dispTab
	.word	1
#Name Lookup table for classes (index is class identifier)
class_nameTab:
	#Lookup for Object
	.word	str_const5
	#Lookup for IO
	.word	str_const6
	#Lookup for Int
	.word	str_const7
	#Lookup for Bool
	.word	str_const8
	#Lookup for String
	.word	str_const9
	#Lookup for Main
	.word	str_const10
#PROTOTYPE OBJECTS
#Prototype object for class Object
	.word	-1
Object_protObj:
	.word	0
	.word	3
	.word	Object_dispTab
#Prototype object for class IO
	.word	-1
IO_protObj:
	.word	1
	.word	3
	.word	IO_dispTab
#Prototype object for class Int
	.word	-1
Int_protObj:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	0 # primary slot -> 0 (for each bool, int, and string)
#Prototype object for class Bool
	.word	-1
Bool_protObj:
	.word	3
	.word	4
	.word	Bool_dispTab
	.word	0 # primary slot -> 0 (for each bool, int, and string)
#Prototype object for class String
	.word	-1
String_protObj:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const3 # basic type default for Int
	.word	0 # primary slot -> 0 (for each bool, int, and string)
#Prototype object for class Main
	.word	-1
Main_protObj:
	.word	5
	.word	5
	.word	Main_dispTab
	.word	int_const3 # basic type default for Int
	.word	bool_const0 # basic type default for Bool
#DISPATCH TABLES OBJECTS
#Dispatch table for Object
Object_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
#Dispatch table for IO
IO_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	IO.out_string
	.word	IO.out_int
	.word	IO.in_string
	.word	IO.in_int
#Dispatch table for Int
Int_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
#Dispatch table for Bool
Bool_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
#Dispatch table for String
String_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	String.length
	.word	String.concat
	.word	String.substr
#Dispatch table for Main
Main_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	Main.main
	.globl	heap_start
heap_start:
	.word	0
	.text
	.globl	Main_init
	.globl	Int_init
	.globl	String_init
	.globl	Bool_init
	.globl	Main.main
Object_init:

jr $ra # TODO INITIALIZATION
IO_init:

jr $ra # TODO INITIALIZATION
Int_init:

jr $ra # TODO INITIALIZATION
Bool_init:

jr $ra # TODO INITIALIZATION
String_init:

jr $ra # TODO INITIALIZATION
Main_init:

jr $ra # TODO INITIALIZATION
# METHOD DEFINITIONS FOR Main 

	.globl	Main.main
Main.main:
	 addi $sp, $sp, -4 # push stack to store $ra
	 sw $ra, 4($sp)    # store ra 
	 la $a0, int_const2 # int const: load from appropriate global!
	 lw $ra, 4($sp)    # load back ra after method
	 addi $sp, $sp, 4  # pop stack back up on return
	la $t0, str_const1
	move   $s3, $sp
	addi   $sp, $sp, -12
	sw     $ra, 8($sp)
	sw     $a0, 4($sp)
	move   $s2, $ra
	jal    IO.out_int
	lw     $ra, 8($sp)
	move   $ra, $s2
	addi   $sp, $sp, 8
	 jr $ra 

# end of generated code
#ANN(COOLCLASS, "Object", "_no_class")
#ANN(COOLMETHOD, "Object", "abort", "Object")
#ANN(COOLMETHOD, "Object", "type_name", "String")
#ANN(COOLMETHOD, "Object", "copy", "SELF_TYPE")
#ANN(COOLCLASS, "String", "Object", "Int", "_prim_slot")
#ANN(COOLMETHOD, "String", "length", "Int")
#ANN(COOLMETHOD, "String", "concat", "String", "String")
#ANN(COOLMETHOD, "String", "substr", "Int", "Int", "String")
#ANN(COOLCLASS, "Bool", "Object", "_prim_slot")
#ANN(COOLCLASS, "Int", "Object", "_prim_slot")
#ANN(COOLCLASS, "IO", "Object")
#ANN(COOLMETHOD, "IO", "out_string", "String", "SELF_TYPE")
#ANN(COOLMETHOD, "IO", "out_int", "Int", "SELF_TYPE")
#ANN(COOLMETHOD, "IO", "in_string", "String")
#ANN(COOLMETHOD, "IO", "in_int", "Int")
#ANN(COOLCLASS, "Main","Object", "Int", "Bool")
  #ANN(COOLMETHOD, "Main", "main", "Int")
