package de.unistuttgart.iaas.icetea;

import java.util.HashMap;
import java.util.Scanner;

import de.unistuttgart.iaas.icetea.player.Player;
import de.unistuttgart.iaas.icetea.player.PlayerState;
import de.unistuttgart.iaas.icetea.player.Playlist;
import de.unistuttgart.iaas.icetea.player.Song;
import de.unistuttgart.iaas.icetea.user.Hash;
import de.unistuttgart.iaas.icetea.user.UserManager;

/**
 * Class which connects to the BasicPlayer and is used to control it
 * @author Daniel Capkan, Matrikelnummer: 3325960, st156303@stud.uni-stuttgart.de
 * @author Mario Scheich, Matrikelnummer: 3232655, st151491@stud.uni-stuttgart.de
 * @author Florian Walcher, Matrikelnummer: 3320185, st156818@stud.uni-stuttgart.de
 * @version 1.0
 */
public class CInterface {
	
	UserManager manager = new UserManager();
	/** scanner is used to read user input from console */
	private Scanner scan;

	/** Used for media management and playback */
	private Player player;
	
	//TODO add suitable attribute
	/**can be 0. not logged in, 1. logged in, 2. admin */
	protected String username;
	private String password;
	private HashMap<String, String> map = new HashMap<String, String>();
	private boolean login = false;

	/**
	 * Constructor initializes scanner and player and starts the menu loop
	 */
	public CInterface() {
		this.scan = new Scanner(System.in);
		this.player = new Player();
		map.put("admin", Hash.hash("password"));
		run();
	}

	/**
	 * prints out the main menu to the console
	 * 
	 * @return the chosen item
	 */
	private int printMainMenu() {
		if(login == false) {
			return 0;
			
		} else if(manager.isAdmin()) {
			System.out.println("Hauptmenue:");
			System.out.println("1. Lied hinzufuegen");
			System.out.println("2. Playlist hinzufuegen");
			System.out.println("   -");
			System.out.println("3. Lied abspielen");
			System.out.println("4. Playlist abspielen");
			System.out.println("   -");
			System.out.println("5. Play");
			System.out.println("6. Skip");
			System.out.println("7. Pause");
			System.out.println("8. Stop");
			System.out.println("   -");
			System.out.println("9. Benutzermenue");
			System.out.println("   -");
			System.out.println("0. Programm beenden");
			return readInt();
			
		}  else if(manager.hasUser(username)) {
			System.out.println("Hauptmenue:");
			System.out.println("1. Lied hinzufuegen");
			System.out.println("2. Playlist hinzufuegen");
			System.out.println("   -");
			System.out.println("3. Lied abspielen");
			System.out.println("4. Playlist abspielen");
			System.out.println("   -");
			System.out.println("5. Play");
			System.out.println("6. Skip");
			System.out.println("7. Pause");
			System.out.println("8. Stop");
			System.out.println("   -");
			System.out.println("9. Abmelden");
			System.out.println("   -");
			System.out.println("0. Programm beenden");
			return readInt();
			
		} else {
			System.err.println("Ein Fehler ist aufgetreten, dieser Text sollte nicht erscheinen.");
			return 0;
		}
		
		
	}

	/**
	 * Asks for a path to a song file, creates a new song object and adds it do
	 * the player
	 * 
	 * @return the new song object for further use
	 */
	private Song createSongMenu() {
		System.out.println("Neues Lied:");
		System.out.println("");
		System.out.println("Bitte gib den Pfad zur Datei an");

		String path = readStringWithSpaces();
		
		System.out.println("");
		System.out.println("Bitte gib den Titel des Liedes ein");
		
		String name = readStringWithSpaces();
		
		Song song = new Song(name, path);
		this.player.addSong(song);

		return song;
	}

	/**
	 * Asks for the name of the new playlist and then displays a menu to
	 * populate the playlist with songs
	 * 
	 * @return the newly created playlist
	 */
	private Playlist createPlaylistMenu() {
		System.out.println("Neue Playlist:");
		System.out.println("");
		System.out.println("Bitte gib den Namen der Playlist ein");

		String name = readStringWithSpaces();

		Playlist playlist = new Playlist(name);

		System.out.println("Die neue Playlist \"" + name + "\" wurde erstellt.");

		int input = -1;

		// Print the new menu for populating playlist
		while (input != 0) {
			System.out.println("Fuege Lieder zu " + name + " hinzu:");
			System.out.println("");
			System.out.println("1. Lied aus der Datenbank");
			System.out.println("2. Neues Lied");
			System.out.println("   -");
			System.out.println("3. Zeige Lider von " + name);
			System.out.println("   -");
			System.out.println("0. Zurueck zum Hauptmenue");

			input = readInt();

			Song song = null;
			switch (input) {
			case 1:
				song = getSongMenu();
				break;
			case 2:
				song = createSongMenu();
				break;
			case 3: {
				for (Song s : playlist.getSongs()) {
					System.out.println(s.getName());
				}
			}
				break;
			}
			if (song != null) {
				playlist.addSong(song);
			}
		}
		return playlist;
	}

	/**
	 * prints out a numbered list of songs
	 * 
	 * @return the chosen song or null if Back was chosen
	 */
	private Song getSongMenu() {
		// search menu for a song
		int i = 1;

		System.out.println("Lieder in Datenbank:");
		System.out.println("");

		for (Song song : this.player.getSongs()) {
			System.out.print(i);
			System.out.println(". " + song.getName());
			i++;
		}

		System.out.println("");
		System.out.println("0. Zurueck");

		int input = readInt();

		if (input == 0) {
			// chose to go back without choosing a song
			return null;
		}

		if (this.player.getSongs().size() < input) {
			// avoid index out of bounds
			return null;
		}

		return this.player.getSongs().get(input - 1); // return the song
	}

	/**
	 * displays all playlists in a numbered list
	 * 
	 * @return the choosen playlist or null
	 */
	private Playlist getPlaylistMenu() {
		int i = 1;

		System.out.println("Playlisten in Datenbank:");
		System.out.println("");

		for (Playlist playlist : this.player.getPlaylists()) {
			System.out.print(i);
			System.out.println(". " + playlist.getName());
			i++;
		}

		System.out.println("");
		System.out.println("0. Zurueck");

		int input = readInt();

		if (input == 0) {
			// chose to go back without choosing a playlist
			return null;
		}

		if (this.player.getPlaylists().size() < input) {
			// avoid index out of bounds
			return null;
		}

		return this.player.getPlaylists().get(input - 1); // return the playlist
	}

	/**
	 * function to take care of printing the user menu
	 * @return returns the choice of the user as an Integer
	 */
	private int printUserMenu() {
		//TODO work more here
		if(login == false) {
			System.out.println("1. Anmelden");
			System.out.println("0. Programm beenden");
			int in = readInt();
			if(in == 0) {
				System.exit(in);
			}
			return in;
		} 
		else if(manager.isAdmin()) {
			System.out.println("Benutzermenue:");
			System.out.println("2. Abmelden");
			System.out.println("3. Benutzer hinzufuegen");
			System.out.println("4. Benutzer promovieren");
			System.out.println("5. Benutzer loeschen");
			System.out.println("   -");
			System.out.println("0. Zurueck");
			
			return readInt();
		} else if(manager.hasUser(username)) {
			return 2;
		} else {
			System.err.println("Irgendwas stimmt hier nicht...");
			return 0;
		}
	}

	/**
	 * gets an integer value from the user input if input is not an integer it
	 * prints out only integers allowed and waits for an integer value
	 * 
	 * @return the given number
	 */
	private int readInt() {
		int input = Integer.MIN_VALUE;
		while (input == Integer.MIN_VALUE) {
			if (this.scan.hasNextInt()) {
				// input was an int
				input = this.scan.nextInt();

			} else if (this.scan.hasNext()) {
				// Input was not an int
				this.scan.nextLine();
				System.out.println("Nur ganze Zahlen als Eingabe erlaubt!");
			}
		}
		return input;
	}

	/**
	 * reads a non empty string from user input with spaces
	 * 
	 * @return the read string
	 */
	private String readStringWithSpaces() {
		String input = "";

		while (input.equals("")) {
			if (this.scan.hasNext()) {
				input = this.scan.nextLine();
			}
		}

		return input;
	}

	/**
	 * Prints the main menu and does the wanted action
	 */
	private void run() {
		int input = -1;

		do {
			input = printMainMenu();
			while(login == false) {
				runUserMenu();	
				input = printMainMenu();
			}
			
			try {
				switch (input) {
				case 0:
					this.player.stop();
					break;
				case 1:
					createSongMenu();
					break;
				case 2:
					this.player.addPlaylist(createPlaylistMenu());
					break;
				case 3:
					this.player.play(getSongMenu());
					break;
				case 4:
					this.player.play(getPlaylistMenu());
					break;
				case 5:
					if (this.player.getState() == PlayerState.PAUSED) {
						this.player.resume();
						break;
					}
					this.player.play();
					break;
				case 6:
					this.player.skip();
					break;
				case 7:
					if (this.player.getState() == PlayerState.PAUSED) {
						this.player.resume();
						break;
					}
					this.player.pause();
					break;
				case 8:
					this.player.stop();
					break;
				case 9:
						runUserMenu();
					break;
				default:
					System.out.print(input);
					System.out.println(" ist nicht Vorhanden, versuchen Sie es erneut:");
					System.out.println("\n\n");
					break;
				}
			} catch (IllegalStateException e) {
				System.err.println(e.getMessage());
			}

		} while (input != 0);
	}

	/**
	 * prints the user menu and takes care of the wanted actions, e.g.: login, logout, etc.
	 */
	private void runUserMenu() {
		//TODO work here
		
		int input = printUserMenu();
		switch (input) {
		case 1: //Anmelden
			if(login == true) {
				System.out.println("Ungueltige Eingabe.");
				break;
			}
			System.out.println("Bitte geben Sie ihren Benutzernamen an.");
			username = readStringWithSpaces();
			if(manager.hasUser(username)) {
				System.out.println("Bitte geben Sie ihr Passwort an.");
				password = Hash.hash(readStringWithSpaces());
				if(manager.login(username, password)) {
					login = true;
				}
			} else {
				System.out.println("Dieser Benutzername ist nicht vergeben.");
			}
			break;
		case 2: //Abmelden
			manager.logout();
			login = false;
			System.out.println("Sie sind abgemeldet.");
			break;
		case 3: //Account erstellen
			int pswcreate = 1;
			while(pswcreate == 1) {
				System.out.println("Bitte geben Sie den Benutzernamen ein.");
				username = readStringWithSpaces();
				if(manager.hasUser(username) == false) {
					System.out.println("Bitte geben Sie das Passwort ein.");
					password = readStringWithSpaces();
					System.out.println("Bitte bestätigen Sie ihre Eingabe indem Sie das Passwort wiederholen.");
					String rep = readStringWithSpaces();
					if(password.equals(rep)) {
						manager.addUser(username, password);
						System.out.println("Erstellung erfolgreich.");
						pswcreate = 0;
					} else {
						System.out.println("Falsche Eingabe.");
					}
				} else {
					System.out.println("Dieser Benutzername ist bereits vergeben.");
				}
			}
			break;
		case 4: //Benutzer promovieren 
			System.out.println("Welchen Benutzer moechten Sie promovieren?");
			String prom = readStringWithSpaces();
			if(manager.users.contains(prom) == false) {
				manager.promoteUser(prom);
				System.out.println(prom + " ist jetzt Admin.");
			} else {
				System.out.println("Dieser Benutzer ist bereits Admin.");
			}
			break;
		case 5: //Benutzer loeschen
			System.out.println("Welchen Benutzer moechten Sie loeschen?");
			String del = readStringWithSpaces();
			if(manager.hasUser(del) == false) {
				System.out.println("Diesen Benutzer gibt es nicht.");
				break;
			}
			if(manager.users.contains(del)) {
				System.out.println("Dieser Benutzer ist ein Administrator. Sind Sie sicher, dass Sie ihn löschen möchte?");
				System.out.println("1.Ja, 0.Nein");
				if(readInt() == 1) {
					if(manager.getCurrentUser().equals(del)) {
						login = false;
					}
					manager.deleteUser(del);
				} else {
					break;
				}
			} else {
				manager.deleteUser(del);
			}
			break;
		case 0:
			System.out.println("Zurueck zum Hauptmenue.");
			break;
		default:
			System.out.println("Ungueltige Eingabe.");
			break;
		}
		 
		
	}

	/**
	 * just for startup here
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new CInterface();
	}
}
