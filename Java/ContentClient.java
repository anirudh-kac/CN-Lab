// Using TCP/IP sockets, write a client-server program to make the
//    client send the file name and to make the server send back the
//    contents of the requested file if present.


import java.io.*;
import java.net.*;


public class ContentClient {
    public static void main(String[] args) throws IOException{
        Socket sock = new Socket("127.0.0.1",4000);
        
        //read filename from user

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fname; 
        System.out.println("Enter file name :");
        fname = br.readLine();

        //send filename to server
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pWrite = new PrintWriter(ostream,true);
        pWrite.println(fname);

        //read and print file contents sent by server

        InputStream is = sock.getInputStream();
        BufferedReader contentRead = new BufferedReader(new InputStreamReader(is));

        String str;

        while((str = contentRead.readLine())!=null){
            System.out.println(str);
        }

        sock.close();
        br.close();
        contentRead.close();
        pWrite.close();

    }
}
