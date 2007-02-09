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
		
		# ADD YOUR CODE HERE!
		# Remeber to use the correct calling conventions!
		#