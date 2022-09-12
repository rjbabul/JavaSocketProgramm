package javasocket;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class MyServer {
    public static void main(String []babul) throws IOException{

            // Connected with Distributed System (HUB)

            ServerSocket s = new ServerSocket(5000);
            Socket ss= s.accept();
        
         System.out.println("Connected...");
         int i=0;
       while(i<10){
        try{    
      
        // Method for get request

        DataInputStream input = new DataInputStream(ss.getInputStream());
        String str;

        // read request from Distributed System

        str = (String)input.readUTF();
       
        
        DataOutputStream dd= new DataOutputStream(ss.getOutputStream());

        // Create a Feedback

        String sss ;
        sss = "Thanks for connecting....";

         // Send Feedback

        dd.writeUTF(sss); 
        
          } catch (FileNotFoundException ex) {
             System.out.println("Connection Over...."); 
             break;
         }
        i++;
       }
        System.out.println("Complete Communication.....!");
        System.out.println("");
        System.out.println("DisConnected.....!");
        System.out.println("");
        System.out.println("");
    }
}

//C:\Users\rjbab\Documents\NetBeansProjects\JavaSocket\src\javasocket