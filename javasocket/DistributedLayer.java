package javasocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.*;


public class DistributedLayer {
    public static void main( String [] babul) throws IOException{
       
       // Connect between Hub and Client 
       
        ServerSocket cs = new ServerSocket(4999);
        Socket Clienthub= cs.accept();
         
         // Get Data from Client 
         
         String massage,massages2h; 
         DataInputStream client2hub = new DataInputStream(Clienthub.getInputStream());
          
         // Connect between Distributed Layer and Server
         
         Socket hub2server = new Socket("127.0.0.1" ,5000);
         
         // Send Data to Server
         
         DataOutputStream hubtoserver = new DataOutputStream(hub2server.getOutputStream());
         
         // get Data from server 
         DataInputStream server2hub = new DataInputStream(hub2server.getInputStream());
         
         // send Dato to client 
         
         DataOutputStream hub2client =  new DataOutputStream(Clienthub.getOutputStream());
         
         int i;
         i=0;
         
         // Store Server feddback into Server Massage.txt file 
         
         File file = new File("ServerMassage.txt");
         FileWriter fw = new FileWriter(file,true);   	
	 BufferedWriter ServerFeedBack = new BufferedWriter(fw);
         while( i<10  ){
              try {
             massage= (String)client2hub.readUTF();
             // Process request 
             
             int n ;
             n=massage.length();
             String client , request;
             client = massage.substring(0,10);
             request= massage.substring(11,n-1);
             
            // System.out.println(massage);
             ServerFeedBack.write("Client:   "+client);
             ServerFeedBack.newLine();
             ServerFeedBack.newLine();
             ServerFeedBack.write("Client Massage:   "+request);
             ServerFeedBack.newLine();
            
              
             hubtoserver.writeUTF(request); 
             massages2h = (String)server2hub.readUTF();
             
           //  System.out.println(massages2h);
             
             hub2client.writeUTF(massages2h);
              
             ServerFeedBack.write("Server FeedBack:   "+"Hellow "+client+"   "+massages2h);
             ServerFeedBack.newLine();
             ServerFeedBack.newLine();
            
             ServerFeedBack.newLine();
         } catch (FileNotFoundException ex) {
              
         }
              i++;
             
         } 
         ServerFeedBack.close();
      
         // Massage for checking progress 

         System.out.println("Complete Communication.....!");
         System.out.println("");
         System.out.println("DisConnected.....!");
         System.out.println("");
         System.out.println("Please checkig feedback into ServerMassage.txt file "); 
         System.out.println("\n");
      
    }
}
