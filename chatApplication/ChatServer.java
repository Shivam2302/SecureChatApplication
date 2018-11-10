package chatApplication;

import java.net.*;
import java.util.*;

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





