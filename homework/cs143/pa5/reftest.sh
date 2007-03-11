#!/bin/sh -f
./refcoolc -o /tmp/correct.s $* && cat /tmp/correct.s
