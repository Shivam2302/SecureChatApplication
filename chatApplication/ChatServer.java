package chatApplication;

import java.net.*;
import java.io.*;
import java.util.*;
import java.math.*;

class ServerWriteThread extends Thread
{
  Hashtable<String, Socket> clients;
  String message;
  ServerWriteThread(Hashtable<String, Socket> clients, String message)
  {
    this.clients = clients;
    this.message = message;
  }

  public void run()
  {
    try
    {
      String content[] = message.split(" ", 2);  
      if(clients.containsKey("localhost"))
      {
        PrintStream p = new PrintStream(clients.get(content[0]).getOutputStream());
        p.println(content[1]);
        p.flush();
      }
      
      else
      {
        System.out.println("No such User");
      }
    }

    catch(Exception e)
    {
      System.out.println("Something went wrong "+e.toString());
    }
  } 
}

class ChatServer
{

  public static void main(String args[]) throws Exception
  {   
    ServerSocket server = new ServerSocket(9999);
    //ArrayList<Socket> clients = new ArrayList<Socket>();

    Hashtable<String, Socket> clients = new Hashtable<String, Socket>();  

    // create a new socket each time a client connect to server and make a read thread for that client.
    while(true)
    {
      Socket client = server.accept();
      String hostName = client.getInetAddress().getHostName();
      System.out.println("Hostname is "+hostName);
      clients.put(hostName, client);

      ServerReadThread r1 = new ServerReadThread(client,clients);
      r1.start();
    }
  }
}




