user: reddaly
username: reddaly

hw4 - threads

Extras:
I equipped the downloader with extra progress bars and byte tracking ability.

A cooler extra would have been implementing the SHA1 collision technique
described in [1].

Notes:
Javadoc is included for a lot of the assignment.

For Part A, I assumed the transaction count should be incremented for both the
withdrawled account and deposited account, not just the withdrawled.

Notes about Testing:
It is very difficult to test concurrency code.  I did not try to do
anything fancy.  I did, however, create some unit tests for code that uses threads.
I also created a ruby script that stress tests the cracker code.  It is also
sort of difficult to unit test GUI code, so I just went with what looks pretty.

References:
http://www.infosec.sdu.edu.cn/paper/Finding%20Collisions%20in%20the%20Full%20SHA-1.pdf
