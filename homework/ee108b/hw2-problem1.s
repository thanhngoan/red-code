#1.
#for (i=0; i<=100; i++)
#   a[i] = b[i] + c;

#$a0 holds address of a
#$a1 holds address of b
#$t0 holds variable i

#initialization
addui $t0, $zero, 0       # initialize i
lw $s1, 4($a0)            # load array lengths of a and b into s1 and s2
lw $s2, 4($a1)          

fortest:
#test part of the loop
slti $t1, $t0, 101        # i < 101 stored in $t1
beq $t1, $zero, forend    #jump to end if !(i < 101)

#body part of the loop
sll $t1, 2($t0)           #store i<<2 in $t1 (byte offset into arrays a and b)

#error handling for reading b[i]
slt $t2, $t0, $s2         # if !(i < b.length) goto OutOfBounds
beq $t2, $zero, OutOfBounds

#reading b[i] + c into $t3
add $t2, $t1, $a1         #store location of b[i] in $t2
lw $t3, 8($t2)            # load b[i] into $t3
addu $t3, $t3, $s0        # store b[i] + c in $t3

#error handling for accessing a[i]
slt $t2, $t0, $s1         # if !(i < a.length) goto OutOfBounds
beq $t2, $zero, OutOfBounds

#storing b[i] + c 
add $t4, $t1, $a0         #store location of a[i] in $t4
sw $t3, 8($t4)            #store b[i] + c in a[i]

#restarting loop
addi $t0, $t0, 1          #increment and jump to the start of the loop
j fortest

forend:
