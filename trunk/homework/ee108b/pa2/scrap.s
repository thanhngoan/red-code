#print a number
move $t1, $a0           # number
move $a0, $s0           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall
move $a0, $t1           # number

##DEBUG
move $t2, $a0           # number
move $a0, $s1           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall

move $a0, $s2           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall

move $a0, $t1           # number
li $v0, 1
syscall
la $a0, retn            # end of line
li $v0, 4
syscall
move $a0, $t2           # number
