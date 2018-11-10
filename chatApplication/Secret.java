package chatApplication;

import java.nio.channels.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

class Secret
{
  private byte iv[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  private byte[] key = { 3, 9, 6, 12, 15, 21, 18, 24, 30, 27, 33, 39, 36, 42, 48, 45};

  public IvParameterSpec getInitializationVector()
  {
  	return new IvParameterSpec(iv);
  }

  public SecretKey getKey()
  {
  	return new SecretKeySpec(key, "AES");
  }
}