# Implement an Ethernet LAN using n nodes and set multiple traffic nodes and plot congestion
# window for different source / destination.

set ns [new Simulator]

set nf [open pa3.nam w]
$ns namtrace-all $nf
set tf [open pa3.tr w]
$ns trace-all $tf

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam pa3.nam &
    exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$n0 label "src1"
$n0 color "magenta"

$n5 label "dest1"
$n5 color "blue"

$n2 label "src2"
$n2 color "magenta"

$n3 label "dest2"
$n3 color "blue"

$ns make-lan "$n0 $n1 $n2 $n3 $n4" 100Mb 100ms LL Queue/DropTail Mac/802_3
$ns duplex-link $n4 $n5 1Mb 1ms DropTail

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ftp0 set packetSize_ 5000
$ftp0 set interval_ 0.5

set tcp2 [new Agent/TCP]
$ns attach-agent $n2 $tcp2

set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
$ftp2 set packetSize_ 5000
$ftp2 set interval_ 0.5

set sink1 [new Agent/TCPSink]
$ns attach-agent $n5 $sink1

set sink2 [new Agent/TCPSink]
$ns attach-agent $n3 $sink2

set file1 [open file1.tr w]
$tcp0 attach $file1

set file2 [open file2.tr w]
$tcp2 attach $file2

$ns connect $tcp0 $sink1
$ns connect $tcp2 $sink2


$tcp0 trace cwnd_
$tcp2 trace cwnd_

$ns at 0.1 "$ftp0 start"
$ns at 0.2 "$ftp2 start"
$ns at  7 "$ftp0 stop"
$ns at 8 "$ftp2 stop"
$ns at 10 "$ftp0 start"
$ns at 12 "$ftp2 start"
$ns at 15 "$ftp0 stop"
$ns at 16 "$ftp2 stop"

$ns at 17 "finish"

$ns run



