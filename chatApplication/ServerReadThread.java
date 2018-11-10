package chatApplication;

import java.net.*;
import java.io.*;
import java.util.*;
import java.math.*;

class ServerReadThread extends Thread
{
  Socket client;
  Hashtable<String, Socket> clients;
  
  ServerReadThread(Socket client, Hashtable<String, Socket> clients)
  {
    this.client = client;
    this.clients = clients;
  }

  public void run()
  {
    Scanner scan = new Scanner(System.in);
    while(true)
    {
      try
      {
        BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        if((line = bf.readLine()) != null)
        {
          ServerWriteThread w1 = new ServerWriteThread(clients,line);
          w1.start();
        } 
      }

      catch(Exception e)
      {
        System.out.println("Something went wrong" +e.toString());
      } 
    }
  }
}