import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.channels.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;
import java.math.*;

class ServerReadThread extends Thread
{
  Socket client;
  ArrayList<Socket> clients;
  Scanner scan = new Scanner(System.in);
  ServerReadThread(Socket client, ArrayList<Socket> clients)
  {
    this.client = client;
    this.clients = clients;
  }

  public void run()
  {
    while(true)
    {
      try
      {
        BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        if((line = bf.readLine()) != null)
        {
          System.out.println(line);
          ServerWriteThread w1 = new ServerWriteThread(clients,line);
          w1.start();
        } 
      }

      catch(Exception e)
      {
        System.out.println("Something went wrong");
      }
      
    }
  }
}

class ServerWriteThread extends Thread
{
  ArrayList<Socket> clients;
  String message;
  ServerWriteThread(ArrayList<Socket> clients, String message)
  {
    this.clients = clients;
    this.message = message;
  }

  public void run()
  {
    try
    {
      for(int i=0;i<clients.size();i++)
      {   
        PrintStream p = new PrintStream(clients.get(i).getOutputStream());
        p.println(message);
        p.flush();
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
    ArrayList<Socket> clients = new ArrayList<Socket>();

    while(true)
    {
      Socket client = server.accept();
      clients.add(client);

      ServerReadThread r1 = new ServerReadThread(clients.get(clients.size()-1),clients);
      r1.start();

      // ServerWriteThread w1 = new ServerWriteThread(clients);
      // w1.start();
    }
    
  }
}