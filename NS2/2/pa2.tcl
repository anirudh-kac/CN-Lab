# Implement transmission of ping messages/trace route over a network topology consisting of 6
# nodes and find the number of packets dropped due to congestion.

set ns [new Simulator]

set nf [open pa2.nam w]
$ns namtrace-all $nf

set tf [open pa2.tr w]
$ns trace-all $tf

proc finish {} {
    global ns nf tf
    $ns flush-trace 
    close $nf
    close $tf
    exec nam pa2.nam &
    exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$n4 shape box

#set links

$ns duplex-link $n0 $n4 1005Mb 10ms DropTail
$ns duplex-link $n1 $n4 200Mb 10ms DropTail
$ns duplex-link $n2 $n4 50Mb 10ms DropTail
$ns duplex-link $n3 $n4 100Mb 10ms DropTail
$ns duplex-link $n5 $n4 20Mb 10ms DropTail

#attach Ping agents over nodes

set p0 [new Agent/Ping]
$p0 set packetSize_ 5000
$p0 set interval_ 0.5
$ns attach-agent $n0 $p0
 
set p1 [new Agent/Ping]
$ns attach-agent $n1 $p1

set p2 [new Agent/Ping]
$ns attach-agent $n2 $p2

set p3 [new Agent/Ping]
$p3 set packetSize_ 5000
$p3 set interval_ 0.5
$ns attach-agent $n3 $p3

set p4 [new Agent/Ping]
$ns attach-agent $n4 $p4

set p5 [new Agent/Ping]
$ns attach-agent $n5 $p5

#set queue limit

$ns queue-limit $n0 $n4 2
$ns queue-limit $n3 $n4 3

Agent/Ping instproc recv {from rtt} {
    $self instvar node_
    puts "Node [$node_ id] recieved answer from node $from with rtt $rtt msec"
}

$ns connect $p0 $p4
$ns connect $p3 $p5

$ns at 0.1 "$p0 send"
$ns at 0.2 "$p0 send"
$ns at 0.3 "$p0 send"
$ns at 0.4 "$p0 send"
$ns at 0.5 "$p0 send"
$ns at 0.6 "$p0 send"
$ns at 0.7 "$p0 send"
$ns at 0.8 "$p0 send"
$ns at 0.9 "$p0 send"
$ns at 1.0 "$p0 send"
$ns at 1.1 "$p0 send"
$ns at 1.2 "$p0 send"
$ns at 1.3 "$p0 send"
$ns at 1.4 "$p0 send"
$ns at 1.5 "$p0 send"
$ns at 1.6 "$p0 send"
$ns at 1.7 "$p0 send"
$ns at 1.8 "$p0 send"
$ns at 1.9 "$p0 send"
$ns at 2.0 "$p0 send"
$ns at 2.1 "$p0 send"
$ns at 2.2 "$p0 send"
$ns at 2.3 "$p0 send"
$ns at 2.4 "$p0 send"
$ns at 2.5 "$p0 send"
$ns at 2.6 "$p0 send"
$ns at 2.7 "$p0 send"
$ns at 2.8 "$p0 send"
$ns at 2.9 "$p0 send"

$ns at 0.1 "$p3 send"
$ns at 0.2 "$p3 send"
$ns at 0.3 "$p3 send"
$ns at 0.4 "$p3 send"
$ns at 0.5 "$p3 send"
$ns at 0.6 "$p3 send"
$ns at 0.7 "$p3 send"
$ns at 0.8 "$p3 send"
$ns at 0.9 "$p3 send"
$ns at 1.0 "$p3 send"
$ns at 1.1 "$p3 send"
$ns at 1.2 "$p3 send"
$ns at 1.3 "$p3 send"
$ns at 1.4 "$p3 send"
$ns at 1.5 "$p3 send"
$ns at 1.6 "$p3 send"
$ns at 1.7 "$p3 send"
$ns at 1.8 "$p3 send"
$ns at 1.9 "$p3 send"
$ns at 2.0 "$p3 send"
$ns at 2.1 "$p3 send"
$ns at 2.2 "$p3 send"
$ns at 2.3 "$p3 send"
$ns at 2.4 "$p3 send"
$ns at 2.5 "$p3 send"
$ns at 2.6 "$p3 send"
$ns at 2.7 "$p3 send"
$ns at 2.8 "$p3 send"
$ns at 2.9 "$p3 send"

$ns at 3.0 "finish"
$ns run





