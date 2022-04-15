import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);

        // write your code here
        System.out.println("Enter the Key 1 :");
        int [] key1 = new int[10];
        for(int i = 0 ;i < 10 ; ++i){
            key1[i] = input.nextInt();
        }

        System.out.println("Enter the Key 2 :");
        int[] key2 = new int[10];
        for(int i = 0 ;i < 10 ; ++i){
            key2[i] = input.nextInt();
        }

        System.out.println("Enter the Key 3 :");
        int[] key3 = new int[10];
        for(int i = 0 ;i < 10 ; ++i){
            key3[i] = input.nextInt();
        }

        S_des obj1 = new S_des(key3);
        obj1.key_generation(); // call to key generation

        S_des obj2 = new S_des(key2);
        obj2.key_generation(); // call to key generation
        // function

        S_des obj3 = new S_des(key1);
        obj3.key_generation(); // call to key generation


        // function
        // int []plaintext= {1,0,1,0,0,1,0,1};

        System.out.println("Enter the Plain Text :");
        int[] plaintext = new int[8];
        for(int i = 0 ;i < 8 ; ++i){
            plaintext[i] = input.nextInt();
        }

        System.out.println("Your plain Text is :");
        for (int i = 0; i < 8; i++) // printing the
            // plaintext
            System.out.print(plaintext[i] + " ");

        int[] ciphertext1 = obj1.decryption(plaintext);
        print("Your cipher Text 1 Text is :", ciphertext1, 8 );

        int[] decrypted = obj2.encryption(ciphertext1);
        print("Your cipher Text 2 Text is :", decrypted,8 );

        int[] ciphertext2 = obj3.decryption(decrypted);
        print("Your cipher Text 3 Text is :", ciphertext2,8 );

    }

    public static void print(String s,int []arr,int n){
        System.out.println(s);
        for(int i = 0; i < n; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
