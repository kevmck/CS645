/*------------------------
CS 645 - Project 1, Part 2

Group members:
Delia Batista
Boniface Kiamue
Kevin McKenzie
------------------------*/

import java.io.*;

public class Cracker
{
	public static void main(String [] args)
	{
		//Strings defining file names (assuming they are in the same directory as this .java file)
      String shadowFile = "shadow";
		//String commonPwds = "common-passwords.txt";
      
      //The line below represents the custom word file created to crack the
      //missing password for Problem 1, Part 3. It can remain commented.
      String commonPwds = "10_million_password_list_top_1000000_reduced.txt";
      
      String user, salt, hash, salt_hash, output;

		String sline = null;
		String cline = null;
      
      //String array for storing split strings from shadow file and shash subfield for processing.
		String[] shadowSplit, salt_hash_split;
      
      //Try block implemented to catch any errors while reading "shadow".
		try
		{
         //FileReader and BufferReader for processing "shadow".
			FileReader shadowData = new FileReader(shadowFile);
			BufferedReader shadowBuffer = new BufferedReader(shadowData);
         
         //While loop that iterates over the lines in the "shadow" file.
			while ((sline = shadowBuffer.readLine()) != null)
			{
				shadowSplit = sline.split(":");
				user = shadowSplit[0];
				salt_hash = shadowSplit[1];
            salt_hash_split = salt_hash.split("\\$");
            salt = salt_hash_split[2];
            hash = salt_hash_split[3];
            
            //Nested Try block implemented to catch any errors while reading "common-passwords.txt".
            try
				{
               //FileReader and BufferReader instantiated for processing "common-passwords.txt".
					FileReader commonData = new FileReader(commonPwds);
					BufferedReader commonBuffer = new BufferedReader(commonData);
               
               //While loop that iterates over the lines in the "common-passwords.txt" file;
					//uses the current values from shadowSplit to do perform hashing and string comparisons.
					while ((cline = commonBuffer.readLine()) != null)
					{
                  MD5Shadow md5hasher = new MD5Shadow();
                  output = md5hasher.crypt(cline, salt);
                  
                  //Logic that compares result of MD5 digest (hashOut) to value from
						//"shadow" (hash); if there is a match, output is formatted and printed to console.
						if (hash.equals(output))
						{
							System.out.println(user + ":" + cline);
                     break;
						}
					}
               
               //Close BufferReader.
					commonBuffer.close();
				}
				
            //Catch for nested Try.
				catch(Exception e)
				{
					System.out.print(e);
               e.printStackTrace(System.out);
            }         
         }
         
         //Close BufferReader.
         shadowBuffer.close();
		}
      
      //Catch for outer Try.
		catch(Exception e)
		{
			System.out.print(e);
         e.printStackTrace(System.out);
		}     
	}
}
