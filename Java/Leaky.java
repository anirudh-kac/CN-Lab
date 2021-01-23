import java.util.Scanner;

public class Leaky {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int bucket_size,no_groups,i;
        System.out.println("Enter bucket size");
        bucket_size = sc.nextInt();
        System.out.println("Enter number of groups");
        no_groups = sc.nextInt();
        int no_packets[] = new int[no_groups];
        int input_bw[] = new int[no_groups];

        int output_bw,reqd_bw=0,total_packets =0;

        for(i=0;i<no_groups;i++){
            System.out.println("Enter number of packets for group "  + (i+1));
            no_packets[i] = sc.nextInt();
            System.out.println("Enter bandwidth for group " + (i+1));
            input_bw[i] = sc.nextInt();


            if(total_packets + no_packets[i] < bucket_size){
                total_packets+=no_packets[i];
            } else{
                do{
                    System.out.println("Bucket Overflow!");
                    System.out.println("Enter value less than : " + (bucket_size - total_packets));
                    no_packets[i] = sc.nextInt();
                }while(total_packets + no_packets[i] > bucket_size);
                total_packets+=no_packets[i];
            }

            reqd_bw+= input_bw[i] * no_packets[i];

        }

        System.out.println("The required bandwidth is : "  + reqd_bw);
        System.out.println("Enter output bandwidth : ");
        output_bw = sc.nextInt();

        int rem_bw = reqd_bw;
        int rem_packets = total_packets;

        while(output_bw<rem_bw && rem_packets>0){
            System.out.println("Data sent");
            System.out.println("Remaining packets : " + (--rem_packets));
            System.out.println("Remaining bandwisth : " + (rem_bw-=output_bw));

            if(output_bw>rem_bw && rem_packets>0){
                System.out.println(rem_packets + "no of packet(s) dropped due to insufficient bandwidth");
            }
        }

    }
}
