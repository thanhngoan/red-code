##############################################################
# simulator.s -- skeleton file for a simulated MIPS machine		
#								
#    Programming Assignment 2						
#    EE108B			
#
##############################################################

		.data
registers:	.space 128		# 32 words for registers
static:		.space 2048		# 512 words.  This stores
					# the programs you run on your
					# virtual machine and
					# also includes stack area


str1:		.asciiz "The return value: "
str2:		.asciiz "The number of instructions executed: "
str3:		.asciiz "R-type: "
str4:		.asciiz "I-type: "
str5:		.asciiz "J-type: "
retn:		.asciiz "\n"
	
		.text
        	.globl main

main:		addi $sp, $sp, -32		
		sw $ra, 20($sp)
		sw $fp, 16($sp)
		addiu $fp, $sp, 28

		# this code reads in the input file--a list of integers
		# (one per line) representing a MIPS assembly language
		# program, hand-assembled.  It stops when it sees a 0, i.e., 
		# sll $0, $0, 0 or NOP)  The code is stored at the beginning 
		# of static segment allocated above, one integer per word 
		# (one instruction per word)
		
		la $t0, static		# $t0 = pointer to beginning of
					#    static space, where your
					#    code will be stored.
	
loop1:		li $v0, 5		# code for read_int
		syscall			# $v0 gets integer
		beq $v0, $0, EO_prog
	
		sw $v0, 0($t0)		# place instruction in code space
		addi $t0, $t0, 4	# increment to next code space
		j loop1

EO_prog:	
		sw $v0, 0($t0)		# place the NOP in the code space 
					# as well to signal the end of program

		la $a0, registers
		la $a1, static
		addi $a2, $a1, 2044	# stack pointer points to highest
					#    memory in the static memory area
		la $a3, static		# $a3 can be used as the pointer
					#    to your instructions, so it
					#    is initialized to point to the
					#    first one.
	
		jal sim_mips		# Call the MIPS simulator

		move $t0, $v0		

		la $a0, str1		# "The return value: "
		li $v0, 4
		syscall
		move $a0, $t0
		li $v0, 1
		syscall
		la $a0, retn
		li $v0, 4
		syscall

		la $a0, str2		# "The number of instructions ... "
		li $v0, 4
		syscall
		move $a0, $v1
		li $v0, 1
		syscall
		la $a0, retn
		li $v0, 4
		syscall

		lw $ra, 20($sp)
		lw $fp, 16($sp)
		addi $sp, $sp, 32
		jr $ra			# exit out of main

sim_mips:	# Arguments passed in:
		#	$a0 = pointer to space for your registers (access
		#		0($a0) up to 124($a0)
		#	$a1 = pointer to lowest address in your static 
		#		memory area (2048 bytes available)
		#	$a2 = pointer to the top of the stack (also the
		#		highest memory in the static memory area)
		#	$a3 = pointer to the first instruction in the program 
		# 		(actually contains same value as $a1, since
		#		code is loaded into lowest memory addresses).
		#               Recall that you do not need to load the
		#               instructions in! The shell takes care of this
		#               for you.
		#
		# Register allocation:
		#	You should probably assign certain SPIM registers
		#	to act as your simulated machine's PC, etc.
		#	For clarity's sake, note the assignments here.
		#
		# Virtual machine
		#	If you need more storage area for your
		#	*machine*, use assembler directives to
		#	allocate space.

#initialize the machine by setting up the registers appropriately
sw $zero, 0($a0)     # initialize zero register
addiu $t0, $zero, 42

sw $t0, 4($a0)         # assembly temporary holds the answer to life the unverse and everything
addiu $t0, $zero, 42
sw $t0, 112($a0)       # global pointer -> 42
addiu $t0, $zero, 42
sw $t0, 116($a0)       # stack pointer -> 42
addiu $t0, $zero, 42
sw $t0, 120($a0)       # frame pointer -> 42
addiu $t0, $zero, 42
sw $t0, 124($a0)       # return address -> 42

#store r, i, j counts in $s5-7
addiu $s5, $zero, 0 #initialize s5-7
addiu $s6, $zero, 0
addiu $s7, $zero, 0

execpc:
lw $t0, 0($a1)            #load next instruction
beq $t0, $zero, endexec   #all zeros is ENDOF instruction

#opcodes: addi: 8, andi: 12, slti: 10,
# j: 32, jr: 0, jal: 48, jalr: 9
# jump mask: 67108863
#R instructions:
#    opcode mask: 4227858432, rs mask: 65011712, rt mask: 2031616,
#    rd mask:     63488,   shamt mask: 1984,  funct mask: 63
#I instructions:
#   opcode mask: 4227858432, rs mask: 65011712, rt mask: 2031616,
#   immed mask: 65535
#J instructions:
# opcode mask: 4227858432, address mask: 67108863
#process the opcode/funct field to determine what instruction it is
# I instructions: rs mask: 65011712 rt mask: 2031616 immediate mask: 65535

decodeAny:
andi $s0, $t0, 4227858432 #put opcode in s0
srl  $s0, $s2, 26         # translate opcode

#figure out if it's a jump instruction
addiu $t1, $zero, 48       #jal
beq   $s0, $t1, decodeJ
addiu $t1, $zero, 32       #j
beq   $s0, $t1, decodeJ

#beq   $s0, $zero, decodeRI #any R instruction
#default to R/I type instructions
j decodeRI

decodeJ:
andi $s1, $t0, 67108863   #put jump address into s1
srl  $s1, $s1, 21         #***s1 now holds jump address***

addiu $t1, $zero, 48       #goto jal
beq   $s0, $t1, performJAL
addiu $t1, $zero, 32       #goto j
beq   $s0, $t1, performJ

performJAL:
addiu $t1, $a1, 4          # $t1 has PC + 4
sw $t1, 124($a0)           # store PC + 4 in $ra

performJ:
move $a1, $s1              # adjust PC to given jump address
addiu $s7 $s7, 1           #increment J instruction count
j execpc                   # execute that instruction

decodeRI:
andi $s1, $t0, 65011712   #put rs in s1
srl  $s1, $s1, 21         # translate opcode
sll  $s1, $s1, 2          #get offset of virtual register in memory
addu $s1, $a0, $s1        #s1 now holds actual address of rs data
lw   $s1, 0($s1)          #***s1 now holds actual rs data***

andi $s2, $t0, 2031616    #put rt in s2
srl  $s2, $s2, 16         # translate opcode
sll  $s2, $s2, 2          #get offset of virtual register in memory
addu $s2, $a0, $s2        #***s2 now holds actual address of rs data***
                          #now sw $x, 0(s2) will store data in $x into rs reg
beq $s0, $zero, decodeR
j decodeI

decodeR:
andi $s3, $t0, 65011712   #put rd in s1
srl  $s3, $s3, 11         # translate opcode
sll  $s3, $s3, 2          #get offset of virtual register in memory
addu $s3, $a0, $s3        #s3 now holds actual address of rs data
lw   $s3, 0($s3)          #***s3 now holds actual rd data***

andi $s4, $t0, 65011712   #put shamt in s4
srl  $s4, $s4, 6          #translate opcode

andi $t2, $t0, 63         #put funct code in t2

performADD:
add $t1, $s1, $s3         # add rs and rd into t1
j afterRCompute

performSUB:
sub $t1, $s1, $s3         # add rs and rd into t1
j afterRCompute

performAND:
and $t1, $s1, $s3         # add rs and rd into t1
j afterRCompute

# TODO
performSLL:
sll $t1, $s1, $t1         # add rs and rd into t1
j afterRCompute

performSLT:
slt $t1, $s1, $s3         # add rs and rd into t1
j afterRCompute

performXOR:
xor $t1, $s1, $s3         # add rs and rd into t1
j afterRCompute

decodeI:
addiu $s6 $s6, 1          #increment I instruction count

andi $s3, $t0, 65535      #put immediate in s3
                          #***s3 now holds the UNSIGNED immediate value

addi $t1, $zero, 8
beq  $t1, $s0, performAddI
# addi instruction
performAddI:
sll  $s3, $s3, 16         #extends the unnoticed sign to 32 bits
sra  $s3, $s3, 16         

#add the value in rs to the value of the immediate into t1
add $t1, $s1, $s3
j afterICompute

performANDI:
sll  $s3, $s3, 16         #extends the unnoticed sign to 32 bits
sra  $s3, $s3, 16

#add the value in rs to the value of the immediate into t1
and $t1, $s1, $s3
j afterICompute

performSLTI:
sll  $s3, $s3, 16         #extends the unnoticed sign to 32 bits
sra  $s3, $s3, 16

#store RS < immediate in s3
slt $t1, $s1, $s3
j afterICompute

performLUI:
sll  $s3, $s3, 16         #extends the unnoticed sign to 32 bits
sra  $s3, $s3, 16

#store RS < immediate in t1
sll $t1, $s3, 16
j afterICompute

#TODO
performLW:
sll  $s3, $s3, 16         #extends the unnoticed sign to 32 bits
sra  $s3, $s3, 16

#store RS < immediate in t1
add $t1, $s1, $s3
add $t1, $a3, $t1
lw $t1, 0($t1)
j afterICompute

#TODO
performSW:
sll  $s3, $s3, 16         #extends the unnoticed sign to 32 bits
sra  $s3, $s3, 16

#store RS < immediate in t1
add $t1, $s1, $s3         #immediate + rs
add $t1, $a3, $t1         #actual memory address of memory
sw $t1, 0($s2)
j afterNonjump

# instructions should jump to afterRCompute when they have finished
# their computations and want to store a value
# should pass the value to store through $t1
afterICompute:
afterRCompute:  
#store the result in rt
sw    $t1, 0($s2)         #t2 now holds actual rs data
j afterNonjump

afterNonjump:
addiu $a1, $a1, 4         #increment pc
j execpc

endexec:
lw $a3, 8($a0)             #load virtual return value into a3
#move $a3, $t0

#####  ADD YOUR CODE HERE !!!!!!!!!!!!

		# Here are some guidelines about how you might want to 
		# approach this problem:
		#
		# 1. Fetch the instruction from memory. 
		#    To facilitate this, you will need to implement a
		#    "virtual" PC. The PC points to the address of the next
		#    instruction. 
		# 
		# 2. Decode the instruction
		#    a. Determine the opcode (and function field ... if
		#       applicable)
		#    b. Read the rs and rt registers.
		#
		# 3. Now you know the kind of instruction you're
		#    executing. Use your opcode (and function field)
		#    to branch to a code block that will do what the 
		#    opcode (and function field) dictates.
		#
		# Reminders:
		# ===============
		# 1. Don't forget to increment your virtual PC after each
		#    instruction fetch.
		#
		# 2. Remember that jumps change your virtual PC and that
		#    branches *may* change your virtual PC.
		#
		# 3. Before you return to the shell, make sure you load 
                #    the real $v0 register with the value stored in you
		#    virtual $v0 register.
		#    

  
#store instruction counts in a0-a2, and return value in a3

addu $a0, $zero, $s5
addu $a1, $zero, $s6
addu $a2, $zero, $s7
addu $a3, $zero, $a3

printcounts:    #a0, a1, and a2 are R. I. J. counts respectively

move $t0, $a0 #store arguments in temporaries
move $t1, $a1
move $t2, $a2
move $s6, $a3
addu $s7, $t0, $t1      #t4 has the total instruction count
addu $s7, $s7, $t2

la $a0, str3		# r type:
li $v0, 4
syscall
move $a0, $t0           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall

la $a0, str4		# r type:
li $v0, 4
syscall
move $a0, $t1           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall

la $a0, str5		# r type:
li $v0, 4
syscall
move $a0, $t2           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall

move $v0, $s6
move $v1, $s7

jr $ra			# exit out of main
