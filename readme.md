This is a small script that can be used to take a picuture from a UV4L web server remotely every so often.

I've found that on a raspberry pi, this is stable for an interval as low as 400ms for the pi 3 B+.
I haven't found the results from any other pi.

USAGE:

With a compiled jar file you can use it like so:

[item.jar] <ms interval> [-ip <ip>] [-p <port>]

By default, the port is 9090, and the IP is "localhost", as to be ran on the server itself, 
but can be run on any device on the same network