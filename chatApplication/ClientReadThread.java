package chatApplication;

import java.net.Socket;
import java.io.*;
import java.util.*;
import java.math.*;

class ClientReadThread extends Thread
{
  Socket client;
  ClientReadThread(Socket client)
  {
    this.client = client;
  }

  public void run()
  {
    Scanner scan = new Scanner(System.in);

    // This reader thread keeps on checking whether have sent a message or not.
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