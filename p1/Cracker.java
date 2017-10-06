import java.io.*;
import java.math.*;
import java.security.MessageDigest;

public class Cracker
{
	public static void main(String [] args)
	{
		String shadowFile = "shadow";
		String commonPwds = "common-passwords.txt";
      
      String user, salt, hash, hashOut, salt_hash;

		String sline = null;
		String cline = null;

		String[] shadowSplit, salt_hash_split;

		try
		{
			FileReader shadowData = new FileReader(shadowFile);
			BufferedReader shadowBuffer = new BufferedReader(shadowData);

			while ((sline = shadowBuffer.readLine()) != null)
			{
				shadowSplit = sline.split(":");
				user = shadowSplit[0];
				salt_hash = shadowSplit[1];
            
            salt_hash_split = salt_hash.split("\\$");
            salt = salt_hash_split[2];
            hash = salt_hash_split[3];
            //System.out.println(salt);
            //System.out.println(hash);
         }
		}
      
		catch(Exception e)
		{
			System.out.print(e);
		}
      
      String password = "test";
      String saltier = "salt";
      MD5Shadow hasher = new MD5Shadow();
      String output = hasher.crypt(password, saltier);
      
      System.out.println(output);
	}
}
