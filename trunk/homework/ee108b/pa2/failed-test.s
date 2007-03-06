addi  $t0, $zero, 1       #$t0 gets 1
addi  $t1, $t0,   -12     #$t1 gets -11
addi  $s0, $t1,   32767   #$s0 gets 32756
addi  $s1, $s0,  -32768   #$s1 gets -12
addi  $v0, $s1,   0       #$v1 gets -12
nop
