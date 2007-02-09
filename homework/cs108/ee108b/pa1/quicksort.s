#==============================================================================
# FILE:         quicksort.s (PA #1)
#
# Description:  Skeleton for assembly quicksort routine. 
# 		To complete this assignment, add the following functionality:
#
#		1. Call quicksort. (See quicksort.c)
#		   Pass 2 arguments:
#
#		   ARG 1: Pointer to the first element of the array
#		   (referred to as "nums" in the C code)
#
#		   ARG 2: Number of elements in the array
#                 
#				  Remember to use the correct CALLING CONVENTIONS !!!
#                 Pass all arguments in the conventional way!
#
# 		2. Quicksort routine.
#		   The routine is recursive by definition, so quicksort MUST 
#		   call itself.
#		   Again, make sure that you use the correct calling 
#                  conventions!
#
#		3. Print results.
#                  Print out the results in the prescribed format. We will be 
#                  using a script to test your programs ... so it is 
#                  essential that you follow this format: 
#                  List the sorted elements on a SINGLE line. 
#                  Place ONE space between each printed integer.
#		   Place a single return character at the end of the list.
#
#==============================================================================


	.file	1 "quicksort.c"
	.set	nobopt
gcc2_compiled.:
__gnu_compiled_c:
	.data
	.align	2
	.text
	.align	2
	.globl	print_str
	.rdata
	.align	2
$LC0:		.data
HOW_MANY:	.asciiz "How many elements to be sorted?"
ENTER:		.asciiz "Enter next element:"
ANS:		.asciiz "The sorted list is:"
SPACE:		.asciiz " "				# Space
EOL:		.asciiz "\n"			# Return character

	.text
	.align	2
	.globl	main
	.align	2
	.globl	QuickSort
	.align	2

	.text
	.text
	.ent	main
	

#==========================================================================
main:
#==========================================================================

		#----------------------------------------------------------
		# Register Definitions
		#----------------------------------------------------------
		# $s0 - pointer to the first element of the array
		# $s1 - number of elements in the array
		# $s2 - number of bytes in the array
		#----------------------------------------------------------
		
		#---- Store the old values into stack ---------------------
		.frame	$sp,24,$ra		# vars= 0, regs= 1/0, args= 16, extra= 0
		.mask	0x80000000,-8
		.fmask	0x00000000,0
		subu	$sp,$sp,24
		sw	$ra,16($sp)

		#---- Prompt user for array size --------------------------
		li	$v0, 4				# print_string
		la	$a0, HOW_MANY		# text to be displayed
		syscall
		li	$v0, 4
		la	$a0, EOL
		syscall					# print "\n"
		li	$v0, 5				# read_int
		syscall	
		move	$s1, $v0		# save number of elements

		#---- Create dynamic array --------------------------------
		li	$v0, 9
		sll	$s2, $s1, 2			# number of bytes needed
		move	$a0, $s2		# set up the argument for sbrk
		syscall
		move	$s0, $v0		# the addr of allocated memory

		#---- Prompt user for array elements ----------------------
		addu	$t2, $s0, $s2		# addr of the last element
		move	$t0, $s0
		j	END1

LOOP1:		li	$v0, 4				# print_string
		la	$a0, ENTER				# text to be displayed
		syscall
		li	$v0, 4
		la	$a0, EOL
		syscall						# print "\n"
		li	$v0, 5					# read_int
		syscall
		sw	$v0, 0($t0)		
		addiu	$t0, $t0, 4
END1:		bne	$t2, $t0, LOOP1 

		#---- Call QuickSort ---------------------------------------
		#
		#  ADD YOUR CODE HERE
		#  Make sure that you pass parameters according to the 
		#  established convention !!!!
		#
		# $s0 - pointer to the first element of the array
		# $s1 - number of elements in the array

addiu    $a0, $s0, 0
addiu    $a1, $s1, 0
jal QuickSort


#---- Print sorted array -----------------------------------
li	$v0, 4					# print_string
la	$a0, ANS				# text: "The sorted list is:"
syscall
li	$v0, 4					# print_string
la	$a0, EOL				# text
syscall

		#
		# ADD YOUR CODE HERE
		# Print out your sorted array in the predefined format
		# It is OK to print one extra space at the end.
		#

addu	$t2, $s0, $s2  # t2 = addr of the last element
move	$t0, $s0       # t0 = current address
j	END4
LOOP4:

li	$v0, 1	     # print value
lw	$a0, 0($t0)
syscall

li	$v0, 4	     # print space
la	$a0, SPACE
syscall

addiu	$t0, $t0, 4  #increment loop ptr
END4:		bne	$t2, $t0, LOOP4

		#---- Restore the old values from the stack ----------------
EXITMAIN:	li	$v0, 4				# print_string
	        la      $a0, EOL		# print a space
            syscall          

$L4:
	lw	$ra,16($sp)
	addu	$sp,$sp,24
	
	#---- Exit -------------------------------------------------
	j	$ra
	.end	main
	.text
	.ent	QuickSort


#--------------------------------------------------------------------------
QuickSort:
#--------------------------------------------------------------------------
# ARGUMENT STORAGE:
# $a0 holds int * array
# $a1 holds int n
# $t0 holds boundary
#---- Store the old values into stack ---------------------
  addiu   $sp,$sp,-32
sw      $fp, 28($sp)
addiu   $fp,$sp,-4
sw      $ra, -4($fp)
sw      $s0, -8($fp)
sw      $s1, -12($fp)
sw      $s2, -16($fp)
sw      $s3, -20($fp)

# store args in s0 and s1
move $s0, $a0
move $s1, $a1

#---- function body
# initial if n < 2
addiu     $t0, $zero, 2
slt       $t0, $s1, $t0
bne       $t0, $zero, QuickSortReturn

# variable manipulation
move    $a0, $s0 #move stored values into argument registers and partition
move    $a1, $s1
jal Partition

move    $s2, $v0 #store boundary in s2
move    $a0, $s0 #store array and boundary in a0 and a1
move    $a1, $s2
jal QuickSort

sll     $a0, $s2, 2 #a0 = array address - 4 - 4*boundary
addiu   $a0, $a0, 4
addu   $a0, $s0, $a0

addiu $a1, $s1, -1
subu  $a1, $a1, $s2 #a1 = n - 1 - boundary

jal QuickSort

QuickSortReturn:
#---- Restore stack values --------------------------------

  lw      $s0, -8($fp)
lw      $s1, -12($fp)
lw      $s2, -16($fp)
lw      $s3, -20($fp)

lw      $ra, -4($fp) #restore $ra
lw      $fp, 28($sp) #restore $fp
addiu $sp, $sp, 32 #restore $sp

jr $ra #return

Partition:
#---- Store the old values into stack ---------------------
  addiu   $sp,$sp,-32
sw      $fp, 28($sp)
addiu   $fp,$sp,-4
sw      $ra, -4($fp)
sw      $s0, -8($fp)  #lh
sw      $s1, -12($fp) #rh
sw      $s2, -16($fp) #pivot
sw      $s3, -20($fp) #temp

lw      $s2, 0($a0)       #pivot = array[0]
addiu   $s0, $zero, 1  #lh = 1
addiu   $s1, $a1, -1   #rh = n - 1

PartLoop:
j PartSubLoopTest1
  PartSubLoopBody1:
  addiu $s1, $s1, -1 #rh--

PartSubLoopTest1:
  slt $t6, $s0, $s1 # lh < rh is in t6

sll $t7, $s1, 2    # rh*4 for int offset
addu $t7, $t7, $a0 
lw $t7, 0($t7) # array[rh] is in $t7

addiu $t7, $t7, 1
slt $t7, $s2, $t7 # t7 = array[rh] >= pivot  SAME AS t7 =  pivot < array[rh] + 1

and $t6, $t6, $t7 # lh < rh && array[rh] >= pivot
bne $t6, $zero, PartSubLoopBody1 # branch on pass

j PartSubLoopTest2

PartSubLoopBody2:
    addiu $s0, $s0, 1 #lh++

PartSubLoopTest2:
  slt $t6, $s0, $s1 # lh < rh is in t6

sll $t7, $s0, 2    # lh*4 for int offset
addu $t7, $t7, $a0 # array[lh] is in $t7
lw $t7, 0($t7)
slt $t7, $t7, $s2

and $t6, $t6, $t7 # lh < rh && array[rh] >= pivot
bne $t6, $zero, PartSubLoopBody2 # branch on pass

#----- after 2 while loops
beq $s0, $s1, AfterPartLoop # break on lh == rh

sll $t0, $s0, 2     # lh*4 for int offset
addu  $t0, $t0, $a0 # address of array[lh] is in t1
sll $t1, $s1, 2     # lh*4 for int offset
addu  $t1, $t1, $a0 # address of array[rh] is in t1

# perform swap
lw $t2, 0($t0) # temp = t2 = array[lh]
lw $t3, 0($t1) #        t3 = array[rh]
sw $t2, 0($t1)
sw $t3, 0($t0)

j PartLoop

  AfterPartLoop:
  sll $t0, $s0, 2     # lh*4 for int offset
addu  $t0, $t0, $a0 # address of array[lh] is in t1

lw $t1, 0($t0) # t1 =  array[lh], t0= &array[lh]

  move $v0, $zero
bge $t1, $s2, PartitionReturn # return 0 if array[lh] >= pivot

sw $t1, 0($a0) 
sw $s2, 0($t0) # array[lh] = pivot

move $v0, $s0 #return lh

  PartitionReturn:
#---- restore old values
  lw      $s0, -8($fp)
lw      $s1, -12($fp)
lw      $s2, -16($fp)
lw      $s3, -20($fp)

lw      $ra, -4($fp) #restore $ra
lw      $fp, 28($sp) #restore $fp
addiu $sp, $sp, 32 #restore $sp

jr $ra
