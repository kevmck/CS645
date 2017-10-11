/*------------------------
CS 645 - Project 1, Part 2

Group members:
Delia Batista
Boniface Kiamue
Kevin McKenzie
------------------------*/

import java.io.*;
import java.math.*;
import java.security.MessageDigest;

public class SimpleCracker
{
	public static void main(String [] args)
	{
		//Strings defining file names (assuming they are in the same directory as this .java file)
		String shadowFile = "shadow-simple";
		String commonPwds = "common-passwords.txt";
      
      String user, salt, hash, hashOut, testString;

		String sline = null;
		String cline = null;
		
		//String array for storing split strings from shadow file for processing.
		String[] shadowSplit;
		
		//Try block implemented to catch any errors while reading "shadow-simple".
		try
		{
			//FileReader and BufferReader for processing "shadow-simple". 
			FileReader shadowData = new FileReader(shadowFile);
			BufferedReader shadowBuffer = new BufferedReader(shadowData);
			
			//While loop that iterates over the lines in the "shadow-simple" file.
			while ((sline = shadowBuffer.readLine()) != null)
			{
				shadowSplit = sline.split(":");
				user = shadowSplit[0];
				salt = shadowSplit[1];
				hash = shadowSplit[2];
				
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
						testString = salt + cline;

						MessageDigest md = MessageDigest.getInstance("MD5");
						byte[] bytes = md.digest(testString.getBytes());
						BigInteger bi = new BigInteger(1, bytes);
						hashOut =  String.format("%0" + (bytes.length << 1) + "X", bi);
						
						//Logic that compares result of MD5 digest (hashOut) to value from
						//"shadow-simple" (hash); if there is a match, output is formatted and printed to console.
						if (hash.equals(hashOut))
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
				}
			}

			//Close BufferReader.
			shadowBuffer.close();
		}
		
		//Catch for outer Try.
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}
//