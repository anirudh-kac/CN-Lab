//7. Write a program for error detecting code using CRC CCITT (16 bits)
import java.io.*;

public class CRC {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int message_bits , gen_bits , total_bits , i ;
        int message[] , gen[] , app_message[] , rem[] , transmitted_msg[];

        System.out.println("Enter number of bits in message");
        message_bits = Integer.parseInt(br.readLine());
        message = new int[message_bits];
        System.out.println("Enter the message bits");
        for(i = 0 ; i<message_bits ; i++){
            message[i] = Integer.parseInt(br.readLine());
        }
        
        System.out.println("Enter number of generator bits ");
        gen_bits = Integer.parseInt(br.readLine());
        gen = new int[gen_bits];
        System.out.println("Enter the generator bits ");
        for(i = 0 ; i<gen_bits ; i++){
            gen[i] = Integer.parseInt(br.readLine());
        }

        total_bits = message_bits + gen_bits - 1;

        app_message = new int[total_bits];
        rem = new int[total_bits];
        transmitted_msg = new int[total_bits];

        for(i =0 ; i<message_bits ; i++){
            app_message[i] = message[i];
        }

        System.out.println("The appended message is : ");

        for(i =0 ;i <total_bits ; i++){
            System.out.print(app_message[i] + " ");
        }
        
        System.out.println();

        for( i = 0 ;i<total_bits ; i++ ){
            rem[i] = app_message[i];
        }

        rem = divide(gen,rem);

        for( i =0 ; i<total_bits ; i++){
            transmitted_msg[i] = app_message[i] ^ rem[i];
        }

        System.out.println("The transmitted message is : ");

        for(i =0 ; i<total_bits ; i++){
            System.out.print(transmitted_msg[i] + " ");
        }

        System.out.println();

        System.out.println("Enter the recieved message");

        for(i =0 ; i< total_bits ; i++){
            transmitted_msg[i] = Integer.parseInt(br.readLine());
        }

        rem = divide(gen,rem);

        for( i = 0 ; i<total_bits ; i++){
            if(rem[i] != 0){
                System.out.println("There is an error");
                break;
            }

            if( i == rem.length -1){
                System.out.println("There is no error");
            }
        }
    }

    static int[] divide(int gen[] , int rem[]){
        int i , cur = 0;

        while(true){
            for( i = 0 ; i<gen.length ; i++){
                rem[cur+ i] = (rem[cur+i] ^ gen[i]);
            }

            while((rem[cur] == 0) && cur < rem.length -1){
                cur++;
            }

            if((rem.length - cur) < gen.length){
                break;
            }
        }

        return rem;
    }
}
