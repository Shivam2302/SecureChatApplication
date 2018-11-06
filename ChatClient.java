import java.net.Socket;
import java.io.*;
import java.util.*;
import java.nio.channels.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;
import java.math.*;


class ClientReadThread extends Thread
{
  Socket client;
  Scanner scan = new Scanner(System.in);
  ClientReadThread(Socket client)
  {
    this.client = client;
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
          System.out.println(line); 
      }

      catch(Exception e)
      {
        System.out.println("Something went wrong");
      }
      
    }
  }
}

class ClientWriteThread extends Thread
{
  Socket client;
  Scanner scan = new Scanner(System.in);
  ClientWriteThread(Socket client)
  {
    this.client = client;
  }

  public void run()
  {
    while(true)
    {
      try
      {
        String message = scan.nextLine();    
        PrintStream p = new PrintStream(client.getOutputStream());
        p.println(message);
        p.flush();
      }

      catch(Exception e)
      {
        System.out.println("Something went wrong");
      }
      
    } 
  }
}

class ChatClient
{
    public static void main(String args[]) throws Exception
    {
        Socket client = new Socket("localhost", 9999);

        ClientReadThread r1 = new ClientReadThread(client);
        r1.start();

        ClientWriteThread w1 = new ClientWriteThread(client);
        w1.start();

    }
}