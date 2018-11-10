package chatApplication;

import java.net.Socket;
import java.io.*;
import java.util.*;
import java.nio.channels.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

class ClientWriteThread extends Thread
{
  Socket client;
  IvParameterSpec ivsec;
  SecretKey secretKey;

  Secret sec = new Secret();

  ClientWriteThread(Socket client)
  {
    this.client = client;
    ivsec = sec.getInitializationVector();
    secretKey = sec.getKey();
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
        EncryptAndDecrypt e = new EncryptAndDecrypt();
        byte[] byteCipherText = e.encrypt(message, secretKey, ivsec);

        PrintStream p = new PrintStream(client.getOutputStream());
        p.write(byteCipherText, 0, byteCipherText.length);
        p.flush();
      }

      catch(Exception e)
      {
        System.out.println("Something went wrong");
      }
      
    } 
  }
}