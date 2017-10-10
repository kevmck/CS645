import java.io.*;
import java.math.*;
import java.security.MessageDigest;

public class Cracker
{
	public static void main(String [] args)
	{
		String shadowFile = "shadow";
		//String commonPwds = "common-passwords.txt";
      String commonPwds = "words_alpha.txt";
      
      String user, salt, hash, salt_hash, output;

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

            try
				{
					FileReader commonData = new FileReader(commonPwds);
					BufferedReader commonBuffer = new BufferedReader(commonData);

					while ((cline = commonBuffer.readLine()) != null)
					{
                  MD5Shadow md5hasher = new MD5Shadow();
                  output = md5hasher.crypt(cline, salt);

						if (hash.equals(output))
						{
							System.out.println(user + ":" + cline);
						}
					}

					commonBuffer.close();
				}
				
				catch(Exception e)
				{
					System.out.print(e);
               e.printStackTrace(System.out);
            }         
         }
         shadowBuffer.close();
		}
      
		catch(Exception e)
		{
			System.out.print(e);
         e.printStackTrace(System.out);
		}     
	}
}
