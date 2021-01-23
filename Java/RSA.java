import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Random;


public class RSA {
    private BigInteger p;  
    private BigInteger q;  
    private BigInteger n;  
    private BigInteger phi;  
    private BigInteger e;  
    private BigInteger d;  
    private Random r;
    private int bit_length = 1024;

    public RSA(){
        r = new Random();
        p = BigInteger.probablePrime(bit_length, r);
        q = BigInteger.probablePrime(bit_length, r);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bit_length/2, r);

        while(e.gcd(phi).compareTo(BigInteger.ONE)>0 && e.compareTo(phi) < 0){
            e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);

    }

    public RSA(BigInteger n , BigInteger e , BigInteger d){
        this.n = n;
        this.e = e;
        this.d = d;
    }

    public static String bytesToString (byte[] encrypted){
        String test = "";
        for(byte b : encrypted){
            test+= Byte.toString(b);
        }

        return test;
    }

    public byte[] encrypt(byte[] message){
        return (new BigInteger(message)).modPow(e,n).toByteArray();
    }

    public byte[] decrypt(byte[] message){
        return (new BigInteger(message)).modPow(d,n).toByteArray();
    }

    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        RSA rsa = new RSA();
        String test ;
        System.out.println("Enter the message to encrypt");
        test = br.readLine();

        System.out.println("Encrypting string : " + test);
        System.out.println("String in bytes : " + bytesToString(test.getBytes()));
        byte [] encrypted = rsa.encrypt(test.getBytes());
        System.out.println("Encrypted bytes : " + encrypted);

        byte [] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted string in bytes : " + bytesToString(decrypted));

        System.out.println("Decrypted String : " + new String(decrypted));


    }
}
