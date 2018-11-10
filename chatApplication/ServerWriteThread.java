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