package de.unistuttgart.iaas.icetea.player;

import java.io.File;
import java.net.URL;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 * Class which connects to the BasicPlayer and is used to control it
 *
 * @author Fabian Bühler
 * @author Sandro Speth
 * @author Tobias Wältken
 * @version 1.0
 */
public class PlayerCore {
	
	/** is the actual player */
	private BasicPlayer player;
	
	/**
	 * Standard Constructor
	 *
	 * @param listener is a listener for the player
	 */
	public PlayerCore(BasicPlayerListener... listeners) {
		this.player = new BasicPlayer();

		for (BasicPlayerListener listener : listeners) {
			this.player.addBasicPlayerListener(listener);
		}
	}
	
	/**
	 * getter method for the status of the player
	 *
	 * return returns the Status of the Player as an int
	 */
	public int getStatus() {
		return this.player.getStatus();
	}
	
	/**
	 * let's the player load the given file
	 *
	 * @param url is the URL to open 
	 */
	public void open(URL url) {
		try {
			this.player.open(url);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * let's the player load the given file
	 *
	 * @param song is the Song to open
	 */
	public void open(Song song) {
		try {
			this.player.open(new File(song.getPath()));
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * plays the loaded song
	 */
	public void play() {
		try {
			this.player.play();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * pauses the current song
	 */
	public void pause() {
		try {
			this.player.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * resumes a paused song
	 */
	public void resume() {
		try {
			this.player.resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * stops the current song
	 */
	public void stop() {
		try {
			this.player.stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
}
