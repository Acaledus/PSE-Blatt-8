package de.unistuttgart.iaas.icetea.player;

import java.util.ArrayList;

/**
 * Class which stores a playlist
 *
 * @author Fabian Bühler
 * @author Sandro Speth
 * @author Tobias Wältken
 * @version 1.0
 */
public class Playlist {
	
	/** is the name of the playlist */
	private String name;
	/** is a list of all the songs */
	private ArrayList<Song> songs;
	
	/**
	 * constructor for the playlist
	 *
	 * @param name is the name of the playlist
	 */
	public Playlist(String name) {
		this.name = name;
		this.songs = new ArrayList<Song>();
	}

	/**
	 * getter for the playlist name
	 *
	 * @return returns the name of the playlist
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * getter for all the songs in the playlist
	 *
	 * @return returns a list of Songs
	 */
	public ArrayList<Song> getSongs() {
		return this.songs;
	}
	
	/**
	 * adds a song to the playlist
	 *
	 * @param song is the new song
	 */
	public void addSong(Song song) {
		this.songs.add(song);
	}
}
