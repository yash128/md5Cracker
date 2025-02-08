import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//A W Q were present in 8th provider
public class Main {
    private static int[] array;
    public static void main(String[] args) {
//        provider(data,7);
//        provider(data,8);
        System.out.println(DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now()));
        Thread th = new Thread(() -> backup(""));
        Runtime.getRuntime().addShutdownHook(th);
        new Thread(() -> {
            String input = "";
            while (!input.equals("q")) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("program started");
                input = scanner.nextLine();
                backup("");
            }
        }).start();
        ScheduledExecutorService ses  = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {
            backup("");
            System.out.println("saved at " + DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now()));
        },0,1, TimeUnit.HOURS);
        fun();
    }

    private static void fun(){
        System.out.println("all ready");
        String data = "QWERTYUIOPASDFGHJKLZXCVBNMzxcvbnmasdfghjklqwertyuiop1234567890rsdflhvnmpaeiou@";
        try {
            provider(data, 9);
            provider(data, 13);
            provider(data, 10);
            provider(data, 11);
            provider(data, 12);
        } finally {
            backup("");
            System.out.println("  wow !!!!");
        }
    }

    private static void backup(String a){
        File f = new File("C:\\Users\\sanjiv\\Desktop\\backup.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f,true))){
            writer.write(print(array) + "\t" + a);
            writer.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void provider(String data,int wants){
        int count = 0;
        array = new int[wants];
        int[] dest = new int[wants];
        for (int a = 0;a<wants;a++){
            array[a] = a;
            dest[a] = (data.length()-wants)+a;
        }/*
        if(wants==9){
            array = new int[]{0, 1, 3, 12, 18, 26, 44, 60, 60};
        }*/
        for (int a = array.length-1;a>=0;a--){
            while (array[a] != dest[a]){
                count++;
                System.out.println(count + "  " + makeString(data,array));
                array[a]++;
                if (a != array.length-1) {
                    int num = array[a]-1;
                    for (;a < array.length;a++){
                        array[a] = ++num;
                    }
                    a = array.length-1;
                }
            }
        }
        System.out.println(count + "   " + makeString(data,array));
    }


    private static int count;
    private static int numToSwap;
    private static int till;
    private static String md5C;
    public static void main(String pass){
        count = 0;
        till = pass.length();
        numToSwap = 0;
        while (till*(till-1) != count){
            count++;
            if (numToSwap == till-1) numToSwap=0;
            pass = swap(pass,numToSwap);
            numToSwap++;
            md5C = md5(pass);
            System.out.println(count + "=>" + pass);
            if (md5C.equals("0c8907245cdc20b628f763f77d42f632") || md5C.equals("e10adc3949ba59abbe56e057f20f883e") || md5C.equals("1d8b738ff80910ebd9f4491d860c743f")){
                System.out.println(pass);
                backup(pass + "    " + md5C);
            }
        }
    }


    private static char temp;
    private static String swap(String str,int pos){
        temp = str.charAt(pos);
        str = str.substring(0,pos) + str.charAt(pos+1) + str.substring(pos+1);
        str = str.substring(0,pos+1) + temp+str.substring(pos+2);
        return str;
    }

    private static StringBuilder sb;
    private static String print(int[] arr){
        sb = new StringBuilder();
        for (int a : arr){
            sb.append(a).append(" ");
            System.out.print(a+" ");
        }
        System.out.println();
        return sb.toString();
    }

    private static StringBuilder builder;
    private static String makeString(String data,int[] arr){
        builder = new StringBuilder();
        for (int i : arr) {
            builder.append(data.charAt(i));
        }
        return builder.toString();
    }


    private static MessageDigest digest;
    private static byte[] msgDigest;
    private static BigInteger number;
    private static StringBuilder hash;
    private static String md5(String s){
        try {
            digest = MessageDigest.getInstance("MD5");
            msgDigest = digest.digest(s.getBytes());
            number = new BigInteger(1,msgDigest);
            hash = new StringBuilder(number.toString(16));
            while (hash.length()<32){
                hash.insert(0, "0");
            }
            return hash.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
