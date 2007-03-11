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
str_const25:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const1
	.byte	0	
	.align	2
	.word	-1
str_const24:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const10
	.ascii	"Unrelated"
	.byte	0	
	.align	2
	.word	-1
str_const23:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const3
	.ascii	"DLesser3"
	.byte	0	
	.align	2
	.word	-1
str_const22:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"DLesser"
	.byte	0	
	.align	2
	.word	-1
str_const21:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const0
	.ascii	"D"
	.byte	0	
	.align	2
	.word	-1
str_const20:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"Giraffe"
	.byte	0	
	.align	2
	.word	-1
str_const19:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Main"
	.byte	0	
	.align	2
	.word	-1
str_const18:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const8
	.ascii	"Animal"
	.byte	0	
	.align	2
	.word	-1
str_const17:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const8
	.ascii	"String"
	.byte	0	
	.align	2
	.word	-1
str_const16:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Bool"
	.byte	0	
	.align	2
	.word	-1
str_const15:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const6
	.ascii	"Int"
	.byte	0	
	.align	2
	.word	-1
str_const14:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const7
	.ascii	"IO"
	.byte	0	
	.align	2
	.word	-1
str_const13:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const8
	.ascii	"Object"
	.byte	0	
	.align	2
	.word	-1
str_const12:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const5
	.ascii	"_prim_slot"
	.byte	0	
	.align	2
	.word	-1
str_const11:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const10
	.ascii	"SELF_TYPE"
	.byte	0	
	.align	2
	.word	-1
str_const10:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const10
	.ascii	"_no_class"
	.byte	0	
	.align	2
	.word	-1
str_const9:
	.word	4
	.word	8
	.word	String_dispTab
	.word	int_const11
	.ascii	"<basic class>"
	.byte	0	
	.align	2
	.word	-1
str_const8:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const6
	.ascii	"dog"
	.byte	0	
	.align	2
	.word	-1
str_const7:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const5
	.ascii	"irrelevant"
	.byte	0	
	.align	2
	.word	-1
str_const6:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"not int"
	.byte	0	
	.align	2
	.word	-1
str_const5:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Love"
	.byte	0	
	.align	2
	.word	-1
str_const4:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Lisp"
	.byte	0	
	.align	2
	.word	-1
str_const3:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const6
	.ascii	"KLC"
	.byte	0	
	.align	2
	.word	-1
str_const2:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const10
	.ascii	"real deal"
	.byte	0	
	.align	2
	.word	-1
str_const1:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"halo"
	.byte	0	
	.align	2
	.word	-1
str_const0:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const5
	.ascii	"example.cl"
	.byte	0	
	.align	2
	.word	-1
int_const11:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	13
	.word	-1
int_const10:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	9
	.word	-1
int_const9:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	7
	.word	-1
int_const8:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	6
	.word	-1
int_const7:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	2
	.word	-1
int_const6:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	3
	.word	-1
int_const5:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	10
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
	.word	8
	.word	-1
int_const2:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	5
	.word	-1
int_const1:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	0
	.word	-1
int_const0:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	1
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
	.word	str_const13
	#Lookup for IO
	.word	str_const14
	#Lookup for Int
	.word	str_const15
	#Lookup for Bool
	.word	str_const16
	#Lookup for String
	.word	str_const17
	#Lookup for Animal
	.word	str_const18
	#Lookup for Main
	.word	str_const19
	#Lookup for Giraffe
	.word	str_const20
	#Lookup for D
	.word	str_const21
	#Lookup for DLesser
	.word	str_const22
	#Lookup for DLesser3
	.word	str_const23
	#Lookup for Unrelated
	.word	str_const24
#Name Lookup table for classes (index is class identifier)
	 .globl InheritanceTable
InheritanceTable:
	#Lookup for Object
	.word	0

	#Lookup for IO
	.word	0

	#Lookup for Int
	.word	0

	#Lookup for Bool
	.word	0

	#Lookup for String
	.word	0

	#Lookup for Animal
	.word	0

	#Lookup for Main
	.word	0

	#Lookup for Giraffe
	.word	0

	#Lookup for D
	.word	0

	#Lookup for DLesser
	.word	8

	#Lookup for DLesser3
	.word	8

	#Lookup for Unrelated
	.word	0

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
	.word	int_const1 # basic type default for Int
	.word	0 # primary slot -> 0 (for each bool, int, and string)
#Prototype object for class Animal
	.word	-1
Animal_protObj:
	.word	5
	.word	4
	.word	Animal_dispTab
	.word	str_const25 # basic type default for String
#Prototype object for class Main
	.word	-1
Main_protObj:
	.word	6
	.word	5
	.word	Main_dispTab
	.word	0 #non-basic intialization to null
	.word	0 # basic type initialized to VOID
#Prototype object for class Giraffe
	.word	-1
Giraffe_protObj:
	.word	7
	.word	6
	.word	Giraffe_dispTab
	.word	0 # basic type initialized to VOID
	.word	str_const25 # basic type default for String
	.word	str_const25 # basic type default for String
#Prototype object for class D
	.word	-1
D_protObj:
	.word	8
	.word	24
	.word	D_dispTab
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	int_const1 # basic type default for Int
	.word	str_const25 # basic type default for String
	.word	0 # basic type initialized to VOID
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	int_const1 # basic type default for Int
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	int_const1 # basic type default for Int
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
#Prototype object for class DLesser
	.word	-1
DLesser_protObj:
	.word	9
	.word	24
	.word	DLesser_dispTab
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	int_const1 # basic type default for Int
	.word	str_const25 # basic type default for String
	.word	0 # basic type initialized to VOID
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	int_const1 # basic type default for Int
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	int_const1 # basic type default for Int
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
#Prototype object for class DLesser3
	.word	-1
DLesser3_protObj:
	.word	10
	.word	24
	.word	DLesser3_dispTab
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	int_const1 # basic type default for Int
	.word	str_const25 # basic type default for String
	.word	0 # basic type initialized to VOID
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	bool_const0 # basic type default for Bool
	.word	int_const1 # basic type default for Int
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	int_const1 # basic type default for Int
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
	.word	0 # basic type initialized to VOID
#Prototype object for class Unrelated
	.word	-1
Unrelated_protObj:
	.word	11
	.word	4
	.word	Unrelated_dispTab
	.word	0 #non-basic intialization to null
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
#Dispatch table for Animal
Animal_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
#Dispatch table for Main
Main_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	Main.main
#Dispatch table for Giraffe
Giraffe_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	Giraffe.stuff
	.word	Giraffe.things
	.word	Giraffe.selfish
	.word	Giraffe.describe
	.word	Giraffe.talk
#Dispatch table for D
D_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	D.stupid
	.word	D.create_d
	.word	D.arith
	.word	D.addtwonumbers
	.word	D.dispatch3
	.word	D.selfish
#Dispatch table for DLesser
DLesser_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	D.stupid
	.word	D.create_d
	.word	D.arith
	.word	D.addtwonumbers
	.word	DLesser.dispatch3
	.word	D.selfish
	.word	DLesser.thing
#Dispatch table for DLesser3
DLesser3_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	D.stupid
	.word	D.create_d
	.word	D.arith
	.word	D.addtwonumbers
	.word	DLesser3.dispatch3
	.word	D.selfish
	.word	DLesser3.thing
#Dispatch table for Unrelated
Unrelated_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
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

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
IO_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
Int_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 #no init for attribute -> VOID_val
	 sw $zero 12($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
Bool_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 #no init for attribute -> VOID_val
	 sw $zero 12($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
String_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 #no init for attribute -> VOID_val
	 sw $zero 12($s0)
	 #no init for attribute -> VOID_str_field
	 sw $zero 16($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
Animal_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 la $a0, str_const1 # string const: load from appropriate global!
	 sw $a0 12($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
Main_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 la $a0, Giraffe_protObj     # store pointer to prototype object in a0 
	 jal Object.copy    # Create new object 
	 jal Giraffe_init    # Create new object 
	 sw $a0 12($s0)
	 la $a0, IO_protObj     # store pointer to prototype object in a0 
	 jal Object.copy    # Create new object 
	 jal IO_init    # Create new object 
	 sw $a0 16($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
Giraffe_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 la $a0, IO_protObj     # store pointer to prototype object in a0 
	 jal Object.copy    # Create new object 
	 jal IO_init    # Create new object 
	 sw $a0 12($s0)
	 la $a0, str_const2 # string const: load from appropriate global!
	 sw $a0 16($s0)
	 sw $fp, 0($sp) # store FP at the tip top of the Activation Record 
	 sw $s0, -4($sp) # store $ra so in AR at fp - 4 
	 addi $sp, $sp, -12 
	#Attribute binding for realdescriptor 
	 lw $a0, 16($s0)
	 sw $a0,  4($sp) # store evaluated parameter on stack 
	 la $a0, IO_protObj     # store pointer to prototype object in a0 
	 jal Object.copy    # Create new object 
	 jal IO_init    # Create new object 
	 move $s0, $a0 
	 jal IO.out_string # dispatch the method 
	 lw $s0,    4($sp) # load back ra 
	 lw $fp,    8($sp) # load back fp 
	 addi $sp, $sp, 8 
	#Attribute binding for realdescriptor 
	 lw $a0, 16($s0)
	 sw $a0 20($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
D_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 la $a0, bool_const1 # const: load from appropriate global!
	 sw $a0 12($s0)
	 #no init for attribute -> VOIDf
	 sw $zero 16($s0)
	 la $a0, int_const5 # int const: load from appropriate global!
	 sw $a0 20($s0)
	 la $a0, str_const3 # string const: load from appropriate global!
	 sw $a0 24($s0)
	#Attribute binding for s 
	 lw $a0, 24($s0)
	 sw $a0 28($s0)
	#Attribute binding for t 
	 lw $a0, 12($s0)
	 beq $a0, $zero, IsFalse0
	 la $a0, bool_const0
	 j AfterBoolSet1
	 IsFalse0:

	 la $a0, bool_const1
	 AfterBoolSet1:

	 sw $a0 32($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 addi $t2, $t2, 1  # subtract 1 from RHS because a < b <=> a <= b + 1 over integers 
	 slt $t0, $t1, $t2   # result of division in $t0
	 beq $t0, $zero, IsFalse2
	 la $a0, bool_const1
	 j AfterBoolSet3
	 IsFalse2:

	 la $a0, bool_const0
	 AfterBoolSet3:

	 addi $sp, $sp, 4 
	 sw $a0 36($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 slt $t0, $t1, $t2   # result of division in $t0
	 beq $t0, $zero, IsFalse4
	 la $a0, bool_const1
	 j AfterBoolSet5
	 IsFalse4:

	 la $a0, bool_const0
	 AfterBoolSet5:

	 addi $sp, $sp, 4 
	 sw $a0 40($s0)
	 addi $sp, $sp, -4 
	 la $a0, str_const4 # string const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, str_const5 # string const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 44($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const2 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 48($s0)
	 addi $sp, $sp, -4 
	 la $a0, bool_const0 # const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, bool_const0 # const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 52($s0)
	 addi $sp, $sp, -4 
	#Attribute binding for o 
	 lw $a0, 28($s0)
	 sw $a0, 4($sp)     # store first result 
	#Attribute binding for o 
	 lw $a0, 28($s0)
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 bne $t1, $t2, IsFalse6
	 la $a0, bool_const1
	 j AfterBoolSet7
	 IsFalse6:

	 la $a0, bool_const0
	 AfterBoolSet7:

	 addi $sp, $sp, 4 
	 sw $a0 56($s0)
#NEGATE
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t2, 12($a0)    # decode argument to negation
	 addi $t1, $zero, 0 # load back first result after method
	 sub $t0, $t1, $t2  # result of addition in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 sw $a0 60($s0)
	 addi $sp, $sp, -4 
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	 beq $a0, $zero, _case_abort2 # case on void
	 lw $t0, 0($a0) # load class tag into $t0
	 la $t1, InheritanceTable # load class tag into $t0
	 startcasetesting9:
	 beq $t0, $zero, _case_abort # when we get to a null parent we have tested all branches
	 beq $t0, 2,Int10 # when we get to a null parent we have tested all branches
	 beq $t0, 4,String11 # when we get to a null parent we have tested all branches
	 add $t0, $t0, $t1 # load class tag into $t0
	 lw  $t0, 0($t0) # load class tag into $t0
	 j startcasetesting9
	 Int10:
	 la $a0, int_const8 # int const: load from appropriate global!
	 j endcase8
	 String11:
	 la $a0, str_const6 # string const: load from appropriate global!
	 j endcase8
	 endcase8:
	 addi $sp, $sp, 4 
	 sw $a0 64($s0)
	 la $a0, int_const2 # int const: load from appropriate global!
	 addi $sp, $sp, -4 
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	 la $a0, str_const7 # string const: load from appropriate global!
	 addi $sp, $sp, 4 
	 sw $a0 68($s0)
	 la $a0, Int_protObj
	 jal Object.copy 
	 addi $sp, $sp, -4 
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	#Local binding for x 
	 lw $a0, -16($fp)
	 addi $sp, $sp, 4 
	 sw $a0 72($s0)
	 la $a0, bool_const1 # const: load from appropriate global!
	 lw $t0, 12($a0)
	 beq $t0, $zero, IsFalse12
	 la $a0, int_const2 # int const: load from appropriate global!
	 j AfterBoolSet13
	 IsFalse12:

	 la $a0, int_const9 # int const: load from appropriate global!
	 AfterBoolSet13:

	 sw $a0 76($s0)
	 Test14:

	 la $a0, bool_const1 # const: load from appropriate global!
	 lw $t0, 12($a0)
	 beq $t0, $zero, AfterBoolSet15
	 la $a0, int_const4 # int const: load from appropriate global!
	 j Test14
	 AfterBoolSet15:

	 sw $a0 80($s0)
 # ERROR -- some undefined expression class dispatch
	 sw $a0 84($s0)
	 sw $fp, 0($sp) # store FP at the tip top of the Activation Record 
	 sw $s0, -4($sp) # store $ra so in AR at fp - 4 
	 addi $sp, $sp, -8 
	#Attribute binding for s 
	 lw $a0, 24($s0)
	 move $s0, $a0 
	 jal Object.type_name # dispatch the method 
	 lw $s0,    4($sp) # load back ra 
	 lw $fp,    8($sp) # load back fp 
	 addi $sp, $sp, 8 
	 sw $a0 88($s0)
	 la $a0, str_const8 # string const: load from appropriate global!
	 sw $a0 92($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
DLesser_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 la $a0, bool_const1 # const: load from appropriate global!
	 sw $a0 12($s0)
	 #no init for attribute -> VOIDf
	 sw $zero 16($s0)
	 la $a0, int_const5 # int const: load from appropriate global!
	 sw $a0 20($s0)
	 la $a0, str_const3 # string const: load from appropriate global!
	 sw $a0 24($s0)
	#Attribute binding for s 
	 lw $a0, 24($s0)
	 sw $a0 28($s0)
	#Attribute binding for t 
	 lw $a0, 12($s0)
	 beq $a0, $zero, IsFalse16
	 la $a0, bool_const0
	 j AfterBoolSet17
	 IsFalse16:

	 la $a0, bool_const1
	 AfterBoolSet17:

	 sw $a0 32($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 addi $t2, $t2, 1  # subtract 1 from RHS because a < b <=> a <= b + 1 over integers 
	 slt $t0, $t1, $t2   # result of division in $t0
	 beq $t0, $zero, IsFalse18
	 la $a0, bool_const1
	 j AfterBoolSet19
	 IsFalse18:

	 la $a0, bool_const0
	 AfterBoolSet19:

	 addi $sp, $sp, 4 
	 sw $a0 36($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 slt $t0, $t1, $t2   # result of division in $t0
	 beq $t0, $zero, IsFalse20
	 la $a0, bool_const1
	 j AfterBoolSet21
	 IsFalse20:

	 la $a0, bool_const0
	 AfterBoolSet21:

	 addi $sp, $sp, 4 
	 sw $a0 40($s0)
	 addi $sp, $sp, -4 
	 la $a0, str_const4 # string const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, str_const5 # string const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 44($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const2 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 48($s0)
	 addi $sp, $sp, -4 
	 la $a0, bool_const0 # const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, bool_const0 # const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 52($s0)
	 addi $sp, $sp, -4 
	#Attribute binding for o 
	 lw $a0, 28($s0)
	 sw $a0, 4($sp)     # store first result 
	#Attribute binding for o 
	 lw $a0, 28($s0)
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 bne $t1, $t2, IsFalse22
	 la $a0, bool_const1
	 j AfterBoolSet23
	 IsFalse22:

	 la $a0, bool_const0
	 AfterBoolSet23:

	 addi $sp, $sp, 4 
	 sw $a0 56($s0)
#NEGATE
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t2, 12($a0)    # decode argument to negation
	 addi $t1, $zero, 0 # load back first result after method
	 sub $t0, $t1, $t2  # result of addition in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 sw $a0 60($s0)
	 addi $sp, $sp, -4 
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	 beq $a0, $zero, _case_abort2 # case on void
	 lw $t0, 0($a0) # load class tag into $t0
	 la $t1, InheritanceTable # load class tag into $t0
	 startcasetesting25:
	 beq $t0, $zero, _case_abort # when we get to a null parent we have tested all branches
	 beq $t0, 2,Int26 # when we get to a null parent we have tested all branches
	 beq $t0, 4,String27 # when we get to a null parent we have tested all branches
	 add $t0, $t0, $t1 # load class tag into $t0
	 lw  $t0, 0($t0) # load class tag into $t0
	 j startcasetesting25
	 Int26:
	 la $a0, int_const8 # int const: load from appropriate global!
	 j endcase24
	 String27:
	 la $a0, str_const6 # string const: load from appropriate global!
	 j endcase24
	 endcase24:
	 addi $sp, $sp, 4 
	 sw $a0 64($s0)
	 la $a0, int_const2 # int const: load from appropriate global!
	 addi $sp, $sp, -4 
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	 la $a0, str_const7 # string const: load from appropriate global!
	 addi $sp, $sp, 4 
	 sw $a0 68($s0)
	 la $a0, Int_protObj
	 jal Object.copy 
	 addi $sp, $sp, -4 
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	#Local binding for x 
	 lw $a0, -16($fp)
	 addi $sp, $sp, 4 
	 sw $a0 72($s0)
	 la $a0, bool_const1 # const: load from appropriate global!
	 lw $t0, 12($a0)
	 beq $t0, $zero, IsFalse28
	 la $a0, int_const2 # int const: load from appropriate global!
	 j AfterBoolSet29
	 IsFalse28:

	 la $a0, int_const9 # int const: load from appropriate global!
	 AfterBoolSet29:

	 sw $a0 76($s0)
	 Test30:

	 la $a0, bool_const1 # const: load from appropriate global!
	 lw $t0, 12($a0)
	 beq $t0, $zero, AfterBoolSet31
	 la $a0, int_const4 # int const: load from appropriate global!
	 j Test30
	 AfterBoolSet31:

	 sw $a0 80($s0)
 # ERROR -- some undefined expression class dispatch
	 sw $a0 84($s0)
	 sw $fp, 0($sp) # store FP at the tip top of the Activation Record 
	 sw $s0, -4($sp) # store $ra so in AR at fp - 4 
	 addi $sp, $sp, -8 
	#Attribute binding for s 
	 lw $a0, 24($s0)
	 move $s0, $a0 
	 jal Object.type_name # dispatch the method 
	 lw $s0,    4($sp) # load back ra 
	 lw $fp,    8($sp) # load back fp 
	 addi $sp, $sp, 8 
	 sw $a0 88($s0)
	 la $a0, str_const8 # string const: load from appropriate global!
	 sw $a0 92($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
DLesser3_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
	 la $a0, bool_const1 # const: load from appropriate global!
	 sw $a0 12($s0)
	 #no init for attribute -> VOIDf
	 sw $zero 16($s0)
	 la $a0, int_const5 # int const: load from appropriate global!
	 sw $a0 20($s0)
	 la $a0, str_const3 # string const: load from appropriate global!
	 sw $a0 24($s0)
	#Attribute binding for s 
	 lw $a0, 24($s0)
	 sw $a0 28($s0)
	#Attribute binding for t 
	 lw $a0, 12($s0)
	 beq $a0, $zero, IsFalse32
	 la $a0, bool_const0
	 j AfterBoolSet33
	 IsFalse32:

	 la $a0, bool_const1
	 AfterBoolSet33:

	 sw $a0 32($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 addi $t2, $t2, 1  # subtract 1 from RHS because a < b <=> a <= b + 1 over integers 
	 slt $t0, $t1, $t2   # result of division in $t0
	 beq $t0, $zero, IsFalse34
	 la $a0, bool_const1
	 j AfterBoolSet35
	 IsFalse34:

	 la $a0, bool_const0
	 AfterBoolSet35:

	 addi $sp, $sp, 4 
	 sw $a0 36($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 slt $t0, $t1, $t2   # result of division in $t0
	 beq $t0, $zero, IsFalse36
	 la $a0, bool_const1
	 j AfterBoolSet37
	 IsFalse36:

	 la $a0, bool_const0
	 AfterBoolSet37:

	 addi $sp, $sp, 4 
	 sw $a0 40($s0)
	 addi $sp, $sp, -4 
	 la $a0, str_const4 # string const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, str_const5 # string const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 44($s0)
	 addi $sp, $sp, -4 
	 la $a0, int_const2 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const2 # int const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 48($s0)
	 addi $sp, $sp, -4 
	 la $a0, bool_const0 # const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, bool_const0 # const: load from appropriate global!
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 la $a0, bool_const1     # true 
	 la $a1, bool_const0     # false 
	 jal equality_test    # test primitive types against each other 
	 addi $sp, $sp, 4 
	 sw $a0 52($s0)
	 addi $sp, $sp, -4 
	#Attribute binding for o 
	 lw $a0, 28($s0)
	 sw $a0, 4($sp)     # store first result 
	#Attribute binding for o 
	 lw $a0, 28($s0)
	 lw   $t1, 4($sp)     # load back first result after method
	 move $t2, $a0        # load back first result after method
	 bne $t1, $t2, IsFalse38
	 la $a0, bool_const1
	 j AfterBoolSet39
	 IsFalse38:

	 la $a0, bool_const0
	 AfterBoolSet39:

	 addi $sp, $sp, 4 
	 sw $a0 56($s0)
#NEGATE
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t2, 12($a0)    # decode argument to negation
	 addi $t1, $zero, 0 # load back first result after method
	 sub $t0, $t1, $t2  # result of addition in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 sw $a0 60($s0)
	 addi $sp, $sp, -4 
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	 beq $a0, $zero, _case_abort2 # case on void
	 lw $t0, 0($a0) # load class tag into $t0
	 la $t1, InheritanceTable # load class tag into $t0
	 startcasetesting41:
	 beq $t0, $zero, _case_abort # when we get to a null parent we have tested all branches
	 beq $t0, 2,Int42 # when we get to a null parent we have tested all branches
	 beq $t0, 4,String43 # when we get to a null parent we have tested all branches
	 add $t0, $t0, $t1 # load class tag into $t0
	 lw  $t0, 0($t0) # load class tag into $t0
	 j startcasetesting41
	 Int42:
	 la $a0, int_const8 # int const: load from appropriate global!
	 j endcase40
	 String43:
	 la $a0, str_const6 # string const: load from appropriate global!
	 j endcase40
	 endcase40:
	 addi $sp, $sp, 4 
	 sw $a0 64($s0)
	 la $a0, int_const2 # int const: load from appropriate global!
	 addi $sp, $sp, -4 
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	 la $a0, str_const7 # string const: load from appropriate global!
	 addi $sp, $sp, 4 
	 sw $a0 68($s0)
	 la $a0, Int_protObj
	 jal Object.copy 
	 addi $sp, $sp, -4 
	 sw $a0,  -16($fp) # store evaluated parameter on stack 
	#Local binding for x 
	 lw $a0, -16($fp)
	 addi $sp, $sp, 4 
	 sw $a0 72($s0)
	 la $a0, bool_const1 # const: load from appropriate global!
	 lw $t0, 12($a0)
	 beq $t0, $zero, IsFalse44
	 la $a0, int_const2 # int const: load from appropriate global!
	 j AfterBoolSet45
	 IsFalse44:

	 la $a0, int_const9 # int const: load from appropriate global!
	 AfterBoolSet45:

	 sw $a0 76($s0)
	 Test46:

	 la $a0, bool_const1 # const: load from appropriate global!
	 lw $t0, 12($a0)
	 beq $t0, $zero, AfterBoolSet47
	 la $a0, int_const4 # int const: load from appropriate global!
	 j Test46
	 AfterBoolSet47:

	 sw $a0 80($s0)
 # ERROR -- some undefined expression class dispatch
	 sw $a0 84($s0)
	 sw $fp, 0($sp) # store FP at the tip top of the Activation Record 
	 sw $s0, -4($sp) # store $ra so in AR at fp - 4 
	 addi $sp, $sp, -8 
	#Attribute binding for s 
	 lw $a0, 24($s0)
	 move $s0, $a0 
	 jal Object.type_name # dispatch the method 
	 lw $s0,    4($sp) # load back ra 
	 lw $fp,    8($sp) # load back fp 
	 addi $sp, $sp, 8 
	 sw $a0 88($s0)
	 la $a0, str_const8 # string const: load from appropriate global!
	 sw $a0 92($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
Unrelated_init:

	 sw $fp,  0($sp) # store frame pointer in top-most portion of stack
	 move $fp, $sp
	 addi $sp, $sp, -12 
	 sw $ra,  -4($fp) # store ra 
	 sw $s0,  -8($fp) # store ra 
 # ERROR -- some undefined expression class dispatch
	 sw $a0 12($s0)
	 lw $ra,  -4($fp) # load back ra 
	 lw $s0,  -8($fp) # load back ra 
	 lw $fp,   0($fp) # store frame pointer in top-most portion of stack
	 addi $sp, $sp, 12 
	 jr $ra
# METHOD DEFINITIONS FOR Animal 

# METHOD DEFINITIONS FOR Main 

	.globl	Main.main
Main.main:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	#Attribute binding for io 
	 lw $a0, 16($s0)
	 beq $a0, $zero, IsFalse50
	 la $a0, bool_const0
	 j AfterBoolSet51
	 IsFalse50:

	 la $a0, bool_const1
	 AfterBoolSet51:

	 lw $t0, 12($a0)
	 beq $t0, $zero, IsFalse48
	 la $a0, bool_const1 # const: load from appropriate global!
	 j AfterBoolSet49
	 IsFalse48:

	 la $a0, bool_const0 # const: load from appropriate global!
	 AfterBoolSet49:

	 addi $sp, $sp, -4 
	 la $a0, int_const2 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const3 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 div $t0, $t1, $t2   # result of division in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 addi $sp, $sp, 4 
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
# METHOD DEFINITIONS FOR Giraffe 

	.globl	Giraffe.stuff
Giraffe.stuff:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 la $a0, int_const4 # int const: load from appropriate global!
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	Giraffe.things
Giraffe.things:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 la $a0, int_const4 # int const: load from appropriate global!
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	Giraffe.selfish
Giraffe.selfish:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 move $a0, $s0 #self object
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	Giraffe.describe
Giraffe.describe:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 sw $fp, 0($sp) # store FP at the tip top of the Activation Record 
	 sw $s0, -4($sp) # store $ra so in AR at fp - 4 
	 addi $sp, $sp, -8 
	 move $a0, $s0 #self object
	 move $s0, $a0 
	 jal Giraffe.talk # dispatch the method 
	 lw $s0,    4($sp) # load back ra 
	 lw $fp,    8($sp) # load back fp 
	 addi $sp, $sp, 8 
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	Giraffe.talk
Giraffe.talk:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	#Attribute binding for fakedescriptor 
	 lw $a0, 20($s0)
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
# METHOD DEFINITIONS FOR D 

	.globl	D.stupid
D.stupid:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	#Attribute binding for t 
	 lw $a0, 12($s0)
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	D.create_d
D.create_d:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 la $a0, D_protObj     # store pointer to prototype object in a0 
	 jal Object.copy    # Create new object 
	 jal D_init    # Create new object 
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	D.arith
D.arith:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 addi $sp, $sp, -4 
	 la $a0, int_const0 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const7 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 add $t0, $t1, $t2  # result of addition in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 addi $sp, $sp, 4 
	 addi $sp, $sp, -4 
	 la $a0, int_const6 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const4 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 sub $t0, $t1, $t2  # result of addition in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 addi $sp, $sp, 4 
	 addi $sp, $sp, -4 
	 la $a0, int_const2 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const8 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 mult $t1, $t2   # result of division in $t0
	 mflo $t0        # result of multiplication in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 addi $sp, $sp, 4 
	 addi $sp, $sp, -4 
	 la $a0, int_const9 # int const: load from appropriate global!
	 sw $a0, 4($sp)     # store first result 
	 la $a0, int_const3 # int const: load from appropriate global!
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 div $t0, $t1, $t2   # result of division in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 addi $sp, $sp, 4 
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
	.globl	D.addtwonumbers
D.addtwonumbers:
	 addi $fp, $sp, 20
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 addi $sp, $sp, -4 
	#Attribute binding for i 
	 lw $a0, 20($s0)
	 sw $a0, 4($sp)     # store first result 
	#ERROR NO BINDING for j 
	 jal Object.copy    # Create a new integer that's a copy of the second argument 
	 lw $t1, 4($sp)     # load back first result after method
	 lw $t1, 12($t1)    # decode integers
	 lw $t2, 12($a0)    
	 add $t0, $t1, $t2  # result of addition in $t0
	 sw  $t0, 12($a0)   # store result in new integer 
	 addi $sp, $sp, 4 
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 12 
	 jr $ra
	.globl	D.dispatch3
D.dispatch3:
	 addi $fp, $sp, 20
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
 # ERROR -- some undefined expression class dispatch
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 12 
	 jr $ra
	.globl	D.selfish
D.selfish:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 move $a0, $s0 #self object
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
# METHOD DEFINITIONS FOR DLesser 

	.globl	DLesser.dispatch3
DLesser.dispatch3:
	 addi $fp, $sp, 20
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 la $a0, int_const2 # int const: load from appropriate global!
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 12 
	 jr $ra
	.globl	DLesser.thing
DLesser.thing:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
 # ERROR -- some undefined expression class dispatch
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
# METHOD DEFINITIONS FOR DLesser3 

	.globl	DLesser3.dispatch3
DLesser3.dispatch3:
	 addi $fp, $sp, 20
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
	 la $a0, int_const2 # int const: load from appropriate global!
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 12 
	 jr $ra
	.globl	DLesser3.thing
DLesser3.thing:
	 addi $fp, $sp, 12
	 addi $sp, $sp, -4 
	 sw $ra,  4($sp) # store ra on stack
	 move $s0, $a0 
 # ERROR -- some undefined expression class dispatch
	 lw $ra,  4($sp) # load back ra 
	 addi $sp, $sp, 4 
	 jr $ra
# METHOD DEFINITIONS FOR Unrelated 


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
#ANN(COOLCLASS, "Animal","Object", "String")
#ANN(COOLCLASS, "Main","Object", "Giraffe", "IO")
  #ANN(COOLMETHOD, "Main", "main", "Int")
#ANN(COOLCLASS, "Giraffe","Object", "IO", "String", "String")
  #ANN(COOLMETHOD, "Giraffe", "stuff", "Int")
  #ANN(COOLMETHOD, "Giraffe", "things", "Int")
  #ANN(COOLMETHOD, "Giraffe", "selfish", "Giraffe")
  #ANN(COOLMETHOD, "Giraffe", "describe", "String")
  #ANN(COOLMETHOD, "Giraffe", "talk", "String")
#ANN(COOLCLASS, "D","Object", "Bool", "Bool", "Int", "String", "Object", "Bool", "Bool", "Bool", "Bool", "Bool", "Bool", "Bool", "Int", "Object", "Object", "Object", "Int", "Object", "Object", "Object", "Object")
  #ANN(COOLMETHOD, "D", "stupid", "Bool")
  #ANN(COOLMETHOD, "D", "create_d", "D")
  #ANN(COOLMETHOD, "D", "arith", "Int")
  #ANN(COOLMETHOD, "D", "addtwonumbers", "Int", "Int", "Int")
  #ANN(COOLMETHOD, "D", "dispatch3", "Int", "Int", "Int")
  #ANN(COOLMETHOD, "D", "selfish", "SELF_TYPE")
#ANN(COOLCLASS, "DLesser","D")
  #ANN(COOLMETHOD, "DLesser", "dispatch3", "Int", "Int", "Int")
  #ANN(COOLMETHOD, "DLesser", "thing", "DLesser")
#ANN(COOLCLASS, "DLesser3","D")
  #ANN(COOLMETHOD, "DLesser3", "dispatch3", "Int", "Int", "Int")
  #ANN(COOLMETHOD, "DLesser3", "thing", "DLesser3")
#ANN(COOLCLASS, "Unrelated","Object", "D")
