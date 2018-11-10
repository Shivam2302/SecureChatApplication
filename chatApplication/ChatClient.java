package chatApplication;

import java.net.Socket;
//import java.io.*;
//import java.util.*;
//import java.math.*;

class ChatClient
{
  public static void main(String args[]) throws Exception
  {
    // connecting to server ...
    Socket client = new Socket("localhost", 9999);

    // After connection, create one thread for reading and other for writing ...
    ClientReadThread r1 = new ClientReadThread(client);
    r1.start();

    ClientWriteThread w1 = new ClientWriteThread(client);
    w1.start();
  }
}