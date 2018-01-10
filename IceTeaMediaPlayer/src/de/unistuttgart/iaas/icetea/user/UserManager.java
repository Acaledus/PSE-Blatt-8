package de.unistuttgart.iaas.icetea.user;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Daniel Capkan, Matrikelnummer: 3325960, st156303@stud.uni-stuttgart.de
 * @author Mario Scheich, Matrikelnummer: 3232655 , st151491@stud.uni-stuttgart.de
 * @author Florian Walcher, Matrikelnummer: 3320185, st156818@stud.uni-stuttgart.de
 */
public class UserManager {

	public UserManager() {
		//TODO fill in your initialization code here
		
		// create standard user
		addUser("admin", "letmein");
		promoteUser("admin");
	}
	
	/**
	 * getter for the currently logged in user
	 *
	 * @return returns the name of user as String
	 */
	public String getCurrentUser() {
		//TODO
		return "";
	}
	
	/**
	 * function to add a user
	 * 
	 * @param name
	 *            is the name of the new user
	 * @param pass
	 *            is the password of the new user
	 */
	public void addUser(String name, String pass) {
		//TODO
	}
	
	/**
	 * makes a user an admin user
	 *
	 * @param name
	 *            is the name of the user to promote
	 */
	public void promoteUser(String name) {
		//TODO
	}
	
	/**
	 * check if the current user has admin rights
	 */
	public boolean isAdmin() {
		//TODO
		return false;
	}
	
	/**
	 * @param name the username to look up
	 *
	 * @return true if the user exists
	 */
	public boolean hasUser(String name) {
		//TODO
		return false;
	}
	
	/**
	 * login a existing user
	 *
	 * @param name
	 *            is the name of the user to login
	 * @param pass
	 *            is the password of the user to login
	 */
	public boolean login(String name, String pass) {
		//TODO
		return false;
	}

	/**
	 * log the current user out
	 */
	public void logout() {
		//TODO
	}
}
