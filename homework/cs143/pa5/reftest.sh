#!/bin/sh -f
#./refcoolc -o /tmp/correct.s $* && cat /tmp/correct.s
./refcoolc -o /tmp/correct.s $* && cat /tmp/correct.s && ./spim -file /tmp/correct.s
