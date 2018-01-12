package de.unistuttgart.iaas.icetea.player;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Class which stores a Song
 *
 * @author Fabian Bühler
 * @author Sandro Speth
 * @author Tobias Wältken
 * @version 1.0
 */
public class Song {
	
	/** is the name of the song */
	private String name;
	/** is the path to the file */
	private String path;
	
	/**
	 * constructor of a Song
	 *
	 * @param name is the name of the new song
	 * @param path is the path to the new file
	 */
	public Song(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	/**
	 * getter for the URL to the File
	 *
	 * @return returns the path as URL
	 */
	public URL getURL() {
		try {
			File file = new File(path);
			URI uri = file.toURI();
			return uri.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * getter for the name
	 *
	 * @return returns the name as String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getter for the path
	 *
	 * @return returns the path as String
	 */
	public String getPath() {
		return this.path;
	}
}
