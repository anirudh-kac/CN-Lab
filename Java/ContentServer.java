import java.io.*;
import java.net.*;

public class ContentServer {
    public static void main(String[] args) throws IOException{
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connections");

        Socket sock = sersock.accept();
        System.out.println("Connection Successful");

        //read file name sent by client
        InputStream is = sock.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String fname;
        fname = br.readLine();

        System.out.println("Client requested : " + fname);

        //read contents of file and write to client
        BufferedReader contentRead = new BufferedReader(new FileReader(fname));
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pWriter = new PrintWriter(ostream,true);

        String str;

        while( (str = contentRead.readLine())!= null){
            pWriter.println(str);
        }

        sersock.close();
        contentRead.close();
        br.close();
        pWriter.close();


    }

}
