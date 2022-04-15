import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTCPServer {
    private static ServerSocket sersock;
    private static final int PORT = 1234;

    public static void main(String args[]) {
        System.out.println("Connecting to Port ...");
        System.out.println("Server started...");
        System.out.println("Waiting for a client ...");
        try {
            sersock = new ServerSocket(PORT);// step1

        } catch (IOException e) {
            System.out.println("Unable to connect to port ...");
            System.exit(1);
        }
        do {
            handleClient();
        } while (true);
    }

    private static void handleClient() {
        Socket link = null;
        try {
            link = sersock.accept();
            System.out.println("Client accepted...");
            // takes input from the client socket
            InputStream recivedCiphertext = link.getInputStream();
            InputStreamReader isr = new InputStreamReader(recivedCiphertext);
            BufferedReader br = new BufferedReader(isr);
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            String message = br.readLine();// step4
            System.out.println("ciphertext received : " + message);

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

            S_des obj1 = new S_des(key3);
            obj1.key_generation(); // call to key generation

            S_des obj2 = new S_des(key2);
            obj2.key_generation(); // call to key generation

            S_des obj3 = new S_des(key1);
            obj3.key_generation(); // call to key generation

            int[] recivedCiphertextIntArray = { 0, 0, 0, 0, 0, 0, 0, 0 };
            for (int i = 0; i < message.length(); i++) {
                recivedCiphertextIntArray[i] = (int) (message.charAt(i) - '0');
            }

            int[] ciphertext1 = obj1.decryption(recivedCiphertextIntArray);
            print("Your cipher Text 1 Text is :", ciphertext1, 8);

            int[] decrypted = obj2.encryption(ciphertext1);
            print("Your cipher Text 2 Text is :", decrypted, 8);

            int[] ciphertext2 = obj3.decryption(decrypted);
            print("Your decrypted text is :", ciphertext2, 8);

            br.close();// step5
            output.close();// step5
        } catch (IOException i) {
            System.out.println("massege not received");
            System.exit(1);
        }
        try {
            // close connection
            System.out.println("Closing Connection...");
            link.close();// step5

        } catch (IOException e) {
            System.out.println("Unable to close");
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
