addi $v0, $zero, 2
sll  $v0, $v0, 1
addi $t0, $zero, 4
bne $v0, $t0, thing
addi $v0, $v0, 2349
#bne $v0, $t0, thing
thing:
  sll $v0, $v0, 2
nop
