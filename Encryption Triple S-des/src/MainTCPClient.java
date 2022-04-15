import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainTCPClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    // private static final String host;
    public static void main(String args[]) {
        try {
            host = InetAddress.getLocalHost();
            accessServer();
        } catch (IOException e) {
            System.out.println("Host not Found");
            System.exit(1);
        }
    }

    private static void accessServer() {
        System.out.println("Connected...");
        Socket link = null;
        try {
            link = new Socket(host, PORT);
            PrintWriter output = new PrintWriter(link.getOutputStream());
            String message;

            Scanner input = new Scanner(System.in);
            // write your code here
            System.out.println("Enter the Key 1 :");
            int[] key1 = new int[10];
            for (int i = 0; i < 10; ++i) {
                key1[i] = input.nextInt();
            }

            System.out.println("Enter the Key 2 :");
            int[] key2 = new int[10];
            for (int i = 0; i < 10; ++i) {
                key2[i] = input.nextInt();
            }

            System.out.println("Enter the Key 3 :");
            int[] key3 = new int[10];
            for (int i = 0; i < 10; ++i) {
                key3[i] = input.nextInt();
            }

            S_des obj1 = new S_des(key1);
            obj1.key_generation(); // call to key generation

            S_des obj2 = new S_des(key2);
            obj2.key_generation(); // call to key generation

            S_des obj3 = new S_des(key3);
            obj3.key_generation(); // call to key generation
            System.out.println("Enter the Plain Text :");
            int[] plaintext = new int[8];
            for (int i = 0; i < 8; ++i) {
                plaintext[i] = input.nextInt();
            }

            System.out.println("Your plain Text is :");
            for (int i = 0; i < 8; i++) // printing the
                System.out.print(plaintext[i] + " ");

            int[] ciphertext1 = obj1.encryption(plaintext);
            print("Your cipher Text 1 Text is :", ciphertext1, 8);

            int[] decrypted = obj2.decryption(ciphertext1);
            print("Your cipher Text 2 Text is :", decrypted, 8);

            int[] ciphertext2 = obj3.encryption(decrypted);
            print("Your cipher Text 3 Text is :", ciphertext2, 8);
            message = "";
            for (int i = 0; i < ciphertext2.length; i++) {
                message += ciphertext2[i];
            }
            output.println(message); // send ciphertext to server(another pc)
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println("No response form server");
        }
        try {
            System.out.println("Close Connection...");

            link.close();// step4
        } catch (IOException e) {
            System.out.println("Unable to connect");
            System.exit(1);

        }
    }

    public static void print(String s, int[] arr, int n) {
        System.out.println(s);
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
