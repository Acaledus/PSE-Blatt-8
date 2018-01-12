package de.unistuttgart.iaas.icetea.user;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Daniel Capkan, Matrikelnummer: 3325960, st156303@stud.uni-stuttgart.de
 * @author Mario Scheich, Matrikelnummer: 3232655, st151491@stud.uni-stuttgart.de
 * @author Florian Walcher, Matrikelnummer: 3320185, st156818@stud.uni-stuttgart.de
 */
public class UserManager {

	String username;
	String passwordHash;	
	boolean loggedIn;
	HashMap<String, String> users = new HashMap<String, String>();
	public HashSet<String> admins = new HashSet<String>();

	public UserManager() {
		addUser("admin", "password");
		promoteUser("admin");
	}
	
	/**
	 * getter for the currently logged in user
	 * @return returns the name of user as String
	 */
	public String getCurrentUser() {
		return username;
	}
	
	/**
	 * function to add a user
	 * @param name - is the name of the new user
	 * @param pass - is the password of the new user
	 */
	public void addUser(String name, String pass) {
		users.put(name, Hash.hash(pass));
	}
	
	/**
	 * makes a user an admin user
	 * @param name - is the name of the user to promote
	 */
	public void promoteUser(String name) {
		admins.add(name);
	}
	
	/**
	 * check if the current user has admin rights
	 */
	public boolean isAdmin() {
		if(admins.contains(username)) 
			return true;
		return false;
	}
	
	/**
	 * @param name the username to look up
	 * @return true if the user exists
	 */
	public boolean hasUser(String name) {
		if(users.containsKey(name)) 
			return true;
		return false;
	}
	
	/**
	 * Löscht einen User.
	 * @param name - der Name des Users, der gelöscht wird
	 */
	public void deleteUser(String name) {
		if(users.containsKey(name)) {
			users.remove(name, users.get(name));
			System.out.println(name + " wurde geloescht.");
		} else {
			System.out.println("Dieser Benutzer existiert nicht.");
		}
	}
	/**
	 * login a existing user
	 * @param name - is the name of the user to login
	 * @param pass - is the password of the user to login
	 */
	public boolean login(String name, String pass) {
		if(loggedIn == true) {
			System.out.println("Sie sind bereits angemeldet.");
			return false;
		}
		if(users.containsKey(name)) {
			username = name;
			if(users.get(name).equals(Hash.hash(pass))) {
				loggedIn = true;
				System.out.println("Anmeldevorgang erfolgreich");
			} else {
				System.out.println("Ungültiges Passwort");
				loggedIn = false;
			}
		}
		return loggedIn;
	}

	/**
	 * log the current user out
	 */
	public void logout() {
		username = null;
		passwordHash = null;
		loggedIn = false;
	}
}
