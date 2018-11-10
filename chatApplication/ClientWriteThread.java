package chatApplication;

import java.net.Socket;
import java.io.*;
import java.util.*;
import java.math.*;

class ClientWriteThread extends Thread
{
  Socket client;
  ClientWriteThread(Socket client)
  {
    this.client = client;
  }

  public void run()
  {
    Scanner scan = new Scanner(System.in);

    // keep on waiting for input from user to send ...
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