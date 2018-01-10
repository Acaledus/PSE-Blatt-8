package de.unistuttgart.iaas.icetea.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class which serves methods to generate hashes
 * @author Daniel Capkan, Matrikelnummer: 3325960, st156303@stud.uni-stuttgart.de
 * @author Mario Scheich, Matrikelnummer: 3232655 , st151491@stud.uni-stuttgart.de
 * @author Florian Walcher, Matrikelnummer: 3320185, st156818@stud.uni-stuttgart.de
 * @version 1.0
 */
public class Hash {
	
	/**
	 * Method to generate a Hash based on a String encoded with UTF-8
	 * 
	 * Original-Author: http://m2tec.be/blog/2010/02/03/java-md5-hex-0093
	 * 
	 * @param string
	 *               is the string to generate the hash from
	 * @return returns the hash
	 */
	public static String hash(String string) {
		try {
			//initialize all needed objects
			MessageDigest md = MessageDigest.getInstance("MD5");
			// calculate the hash
			byte[] array = md.digest(string.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			
			//build the output string
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			
			//return it
			return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}