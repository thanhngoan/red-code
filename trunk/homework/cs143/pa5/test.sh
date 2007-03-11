#!/bin/sh -f
./mycoolc -o /tmp/thing.s $* && cat /tmp/thing.s && ./spim -file /tmp/thing.s
#./mycoolc -o /tmp/thing.s $* && ./spim -file /tmp/thing.s
