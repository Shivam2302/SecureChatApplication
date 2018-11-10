package chatApplication;

import java.util.Scanner;
import java.io.*;
import java.nio.channels.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

class EncryptAndDecrypt
{

    public static byte[] encrypt(String plainText, SecretKey key, IvParameterSpec ivsec)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivsec);
            byte[] bytePlainText  = plainText.getBytes("UTF-8");
            byte[] byteCipherText = cipher.doFinal(bytePlainText);
            return byteCipherText;
        }
        catch(Exception e)
        {
          System.out.println("Something is wrong in encryption : "+e);
          return null;
        }
        
    }

    public static byte[] decrypt(byte[] byteCipherText, SecretKey key, IvParameterSpec ivsec)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivsec);
            byte[] bytePlainText = cipher.doFinal(byteCipherText);
            return bytePlainText;
        }
        catch(Exception e)
        {
          System.out.println("Something is wrong in decryption : "+e);
          return null;
        }
    }

}
