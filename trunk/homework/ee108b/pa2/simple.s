addi $t0, $zero, 1
addi $t1, $zero, 64
addi $t2, $t1, 60
add  $v0, $t1, $t0
sub  $v0, $v0, $t2
xor  $v0, $v0, $t2
sll  $v0, $v0, $t2
slt  $v0, $v0, $t2
and  $v0, $v0, $t2
nop
