package chatApplication;

import java.net.*;
import java.io.*;
import java.util.*;

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
      byte[] temp = new byte[1024]; 
      try
      {
        InputStream stream = client.getInputStream();
        int count = stream.read(temp);

        byte[] data = new byte[count];
        for(int i=0;i<count;i++)
        {
          data[i] = temp[i];
        }

        if(count != 0)
        {
          ServerWriteThread w1 = new ServerWriteThread(clients,data);
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