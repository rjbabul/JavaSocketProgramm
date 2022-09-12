package javasocket;
import java.io.*;
 
import java.net.*;
import java.util.Scanner;
 

public class MyClient {
       
    public static void main (String []arg) throws IOException {
        
        // Create Connection with Distributed System (HUB) 
        
        // Local IP Address 127.0.0.1  & port : 4999

        Socket s = new Socket("127.0.0.1" ,4999);

        // Create a stream for data sharing 

        DataOutputStream ss = new DataOutputStream(s.getOutputStream()); 
        DataInputStream s1= new DataInputStream(s.getInputStream());
        
         // Create Multithread

         MyThread1 thread1= new MyThread1( ss, s1 );
         MyThread2  thread2 = new MyThread2(ss , s1);
       
           // Call Multithread
             thread1.start();
             thread2.start();
             ss.flush();

            System.out.println("Communication Complete............");
    }
}


class MyThread1 extends Thread   {
       public MyThread1( DataOutputStream ss, DataInputStream s1) throws IOException {
         run(ss,s1);
    }

    public void run( DataOutputStream ss, DataInputStream s1) throws IOException {
       
           File file = new File("Thread1Massage.txt");
         try {
              Scanner scan = new Scanner(file);
              while(scan.hasNext()){ 
              
	      String massage, str;
              massage = scan.nextLine();

              // Send Request to Distributed System

              ss.writeUTF(massage);
             
              str = (String)s1.readUTF();
                
		}
                
         } catch (FileNotFoundException ex) {
            
         }
        
    }
}

 class MyThread2 extends Thread   {
       public MyThread2( DataOutputStream ss, DataInputStream s1) throws IOException {
         run(ss,s1);
    }

    public void run( DataOutputStream ss, DataInputStream s1) throws IOException {
       
           File file = new File("Thread2Massage.txt");
         try {
              Scanner scan = new Scanner(file);
              while(scan.hasNext()){ 
              
	      String massage, str;
              massage = scan.nextLine();
            
              // Send Request to Distributed System

              ss.writeUTF(massage);
             
              str = (String)s1.readUTF();
                
		}
                
         } catch (FileNotFoundException ex) {
            
         }
        
    }
}


