package chatApplication;

import java.net.Socket;
import java.io.*;
import java.util.*;
import java.nio.channels.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

class ClientReadThread extends Thread
{
  Socket client;
  IvParameterSpec ivsec;
  SecretKey secretKey;

  Secret sec = new Secret();

  ClientReadThread(Socket client)
  {
    this.client = client;
    ivsec = sec.getInitializationVector();
    secretKey = sec.getKey();
  }

  public void run()
  {
    Scanner scan = new Scanner(System.in);

    // This reader thread keeps on checking whether have sent a message or not.
    while(true)
    {
      byte[] temp = new byte[1000]; 
      try
      {      
        InputStream stream = client.getInputStream();
        int count = stream.read(temp);
       
        if(count != 0)
        {
          byte[] data = new byte[count];
          for(int i=0;i<count;i++)
          {
            data[i] = temp[i];
          }

          EncryptAndDecrypt e = new EncryptAndDecrypt();
          byte[] bytePlainText = e.decrypt(data, secretKey, ivsec);
          System.out.println(new String(bytePlainText));
        } 
      }

      catch(Exception e)
      {
        System.out.println("Something went wrong");
      }
    }
  }
}