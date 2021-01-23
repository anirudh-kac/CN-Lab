import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket sock  = new DatagramSocket();
        while(true){
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 6678;
            String msg = "Hello";
            byte buffer[] = new byte[1000];
            buffer = msg.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length,address,port);

            sock.send(request);

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            sock.receive(reply);
            System.out.println("Server sent : " + new String(reply.getData()));

        }
    }
}
