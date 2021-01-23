// 10. Write a program on datagram socket for client/server to display the messages
//    on client side, typed at te server side.

import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException{
        DatagramSocket sock = new DatagramSocket(6678);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true){

                byte[] buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer,buffer.length);
                sock.receive(request);

                System.out.println("Enter message");
                String msg = br.readLine();

                if(msg.compareToIgnoreCase("exit")==0){
                    break;
                }
                buffer = msg.getBytes();
                DatagramPacket reply =  new DatagramPacket(buffer, buffer.length,request.getAddress(),request.getPort());
                sock.send(reply);

            }
        }
        catch (Exception e){}
    }
}
