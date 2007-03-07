addi	$t3, $zero, 42
addi	$t1, $zero, 64
sw	$t3, 0($t1)
lw      $v0, 0($t1)
