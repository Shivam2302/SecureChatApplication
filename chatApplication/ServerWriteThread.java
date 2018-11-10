package chatApplication;

import java.net.*;
import java.io.*;
import java.util.*;


class ServerWriteThread extends Thread
{
  Hashtable<String, Socket> clients;
  byte[] messageInBytes;
  ServerWriteThread(Hashtable<String, Socket> clients, byte[] messageInBytes)
  {
    this.clients = clients;
    this.messageInBytes = messageInBytes;
  }

  public void run()
  {
    try
    {
      PrintStream p = new PrintStream(clients.get("localhost").getOutputStream());
      p.write(messageInBytes, 0, messageInBytes.length);
      p.flush();
    }

    catch(Exception e)
    {
      System.out.println("Something went wrong "+e.toString());
    }
  } 
}