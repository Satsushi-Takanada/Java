package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class Game {
	
	JFrame window; //Create a frame called window that will show up on screen
	Container game_container; //Create a container for the frame
	JPanel title_panel, start_button_panel, main_panel, choices_panel; //Panel like div for html
	JLabel title_name; //Label for title like a header
	Font title_font = new Font ("Baskerville Old Face", Font.PLAIN, 100); //Font for title screen
	Font text_font = new Font ("Times New Roman", Font.PLAIN, 33); //Font for text
	Font button_font = new Font ("Times New Roman", Font.PLAIN, 35); //Font for Button
	JButton start_button, exit_button, choice1_button, choice2_button, choice3_button; //Buttons
	JTextArea main_text_area; //Displays Text
	
	Start_Game Enter_Game = new Start_Game(); //Event creating the gamescreen
	Exit_Game Exit_The_Game = new Exit_Game(); //Event to exit the game
	
	StoryScreen StoryShow = new StoryScreen(); //Story Class for all story method
	Location Enter = new Location(); //Location class for all 4 location method
	
	String Story_Music = "C:/Programming/Java Codes/RPG_Game/Game Music/Story Music.wav";
	String Town_Music = "C:/Programming/Java Codes/RPG_Game/Game Music/Town Music.wav";
	String Tavern_Music = "C:/Programming/Java Codes/RPG_Game/Game Music/Tavern Music.wav";
	String Shop_Music = "C:/Programming/Java Codes/RPG_Game/Game Music/Shop Music.wav";
	
	String filePath = "C:/Programming/Java Codes/RPG_Game/Text Files/Intro_Story.txt"; //File for story
	String[] sentences = File_String_Array(filePath); //Array that holds the story per sentence
	
	int count = 0;
	Clip currentClip;
	
	public String[] File_String_Array(String filePath) { 
		 StringBuilder fileContent = new StringBuilder();
		 
		 try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			 String line;
			 while ((line = reader.readLine()) != null) {
				 fileContent.append(line).append(" ");
	         }
		 } catch (IOException e) {
			 System.err.println("Error reading file: " + e.getMessage());
	         return null; 
		 }
		 
		 return fileContent.toString().split("(?<=[.!?])\\s+");
	} /*This method will get all the text in the file and put them in the array sentence by
	  sentence using string builder for single thread and the split method*/
	
	public void Background_Music(String current_music) {
		File soundFile = new File(current_music);
		
		try {   
			if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }
			
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);  
            currentClip.start();  
        } catch (Exception e) {
            System.out.println("ERROR");
        }
	} //Plays background music
	
	public void Reset() {
		game_container.remove(main_panel);
        game_container.remove(choices_panel);
        
        game_container.add(main_panel);
        game_container.add(choices_panel);
		
		choice1_button.setVisible(true);
		choice2_button.setVisible(true);
		choice3_button.setVisible(true);
		
		for (ActionListener AL : choice1_button.getActionListeners()) {
            choice1_button.removeActionListener(AL);
        }
        for (ActionListener AL : choice2_button.getActionListeners()) {
            choice2_button.removeActionListener(AL);
        }
        for (ActionListener AL : choice3_button.getActionListeners()) {
            choice3_button.removeActionListener(AL);
        }
	} /*This will reset everything so that it wont overlap in the window*/
	
	public static void main(String[] args) {
		new Game();
	} // Declares the game
	
	public Game() {
		JFrame window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setTitle("Text Adventure");
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		game_container = window.getContentPane();
		
		title_panel = new JPanel();
		title_panel.setBounds(94, 120, 600, 150);
		title_panel.setBackground(Color.black);
		title_name = new JLabel("Dark Horizon");
		title_name.setForeground(Color.white);
		title_name.setFont(title_font);

		start_button_panel = new JPanel(new GridLayout(2, 1));
		start_button_panel.setBounds(295, 380, 200, 100);
		start_button_panel.setBackground(Color.black);
		
		start_button = new JButton("START");
		start_button.setBackground(Color.black);
		start_button.setForeground(Color.white);
		start_button.setFont(button_font);
		start_button.setBorder(null);
		start_button.setFocusPainted(false);
		start_button.addActionListener(Enter_Game);
		
		exit_button = new JButton("EXIT");
		exit_button.setBackground(Color.black);
		exit_button.setForeground(Color.white);
		exit_button.setFont(button_font);
		exit_button.setBorder(null);
		exit_button.setFocusPainted(false);
		exit_button.addActionListener(Exit_The_Game);
		
		title_panel.add(title_name);
		start_button_panel.add(start_button);
		start_button_panel.add(exit_button);
		
		game_container.add(title_panel);
		game_container.add(start_button_panel);
		
		window.setVisible(true);
	} //Creates a Jframe initially creating the window and title screen
	
	public void GameScreen() {	
		game_container.remove(title_panel);
		game_container.remove(start_button_panel);
		
		main_panel = new JPanel();
		main_panel.setBounds(20, 100, 750, 220);
		main_panel.setBackground(Color.black);
		
		main_text_area = new JTextArea(sentences[count]);
		main_text_area.setBounds(100, 100, 750, 250);
		main_text_area.setBackground(Color.black);
		main_text_area.setForeground(Color.white);
		main_text_area.setFont(text_font);
		main_text_area.setLineWrap(true);
		main_text_area.setHighlighter(null);
		main_text_area.setEditable(false);
		main_text_area.setFocusable(false);
		
		choices_panel = new JPanel(new GridLayout(3, 1));
		choices_panel.setBounds(295, 350, 200, 200);
		choices_panel.setBackground(Color.black);
		choices_panel.setFocusable(false);
		
		choice1_button = new JButton();
		choice1_button.setBackground(Color.black);
		choice1_button.setForeground(Color.white);
		choice1_button.setFont(button_font);
		choice1_button.setBorder(null);
		choice1_button.setFocusPainted(false);
		
		choice2_button = new JButton();
		choice2_button.setBackground(Color.black);
		choice2_button.setForeground(Color.white);
		choice2_button.setFont(button_font);
		choice2_button.setBorder(null);
		choice2_button.setFocusPainted(false);
		
		choice3_button = new JButton();
		choice3_button.setBackground(Color.black);
		choice3_button.setForeground(Color.white);
		choice3_button.setFont(button_font);
		choice3_button.setBorder(null);
		choice3_button.setFocusPainted(false);
		
		main_panel.add(main_text_area);
		choices_panel.add(choice1_button);
		choices_panel.add(choice2_button);
		choices_panel.add(choice3_button);
		
		game_container.add(main_panel);
		game_container.add(choices_panel);
		
		StoryShow.Story();
	} //Creates the gamescreen for all of the text and edits of text
	
	public class Start_Game implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			GameScreen();
		} //Event to call the gamescreen when pressed start
	}
	
	public class Exit_Game implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			System.exit(0);
		} //Event to exit the game when pressed exit
	}
	
	public class StoryScreen {
		public void Update_Story(String user_choice) {
			try {
		        if (user_choice.equals("next")) {
		            if (count < sentences.length - 1) {  
		                count++;
		            }
		        } else if (user_choice.equals("back")) {
		            if (count > 0) {  
		                count--;
		            }
		        }
		    } catch(Exception e) {
		    	System.out.println("ERROR");
		    }
			finally {
		        main_text_area.setText(sentences[count]);
		    }
			choice3_button.setVisible(count == sentences.length - 1);
		} //This will next and back the story depending on the button that is pressed and displays it
		
		public void Story() {
			Background_Music(Story_Music);
			
			choice1_button.setText("NEXT");
			choice1_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
		            Update_Story("next");
	            }
	        });
			
			choice2_button.setText("BACK");
			choice2_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
		            Update_Story("back");
	            }
	        });
			
			choice3_button.setText("FINISH");
			choice3_button.setVisible(false);
			choice3_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Enter.Town();
	            }
	        });
		} //Button for next and back and finish
	} //Class that holds all the story for the game
	
	public class Location {
		
		public void Town() {
			Background_Music(Town_Music);
			Reset();
			
			main_text_area.setText("Welcome to Town!");
			
			choice1_button.setText("Tavern");
			choice1_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Tavern();
	            }
	        });
			
			choice2_button.setText("Shop");
			choice2_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Shop();
	            }
	        });
			
			choice3_button.setText("Dungeon");
			choice3_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Dungeon();
	            }
	        });
		} //Button for all of the plaves that can be visited
		
		public void Tavern() {
			Background_Music(Tavern_Music);
			Reset();
			
			main_text_area.setText("Welcome to the The Iron Chalice!\n\n"
					+ "Come and Drink or Rest whatever you want!");
			
			choice1_button.setText("Rest");
			choice1_button.setActionCommand("");
			
			choice2_button.setText("Leave");
			choice2_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Enter.Town();
	            }
	        });
			
			choice3_button.setVisible(false);
		} //Tavern method
		
		public void Shop() {
			Background_Music(Shop_Music);
			Reset();
			
			main_text_area.setText("Welcome to Herald’s Hammer.\n\n"
					+ "What could i get ya?");
			
			choice1_button.setText("Shop");
			choice1_button.setActionCommand("");
			
			choice2_button.setText("Leave");
			choice2_button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Enter.Town();
	            }
	        });
			
			choice3_button.setVisible(false);
		} // Shop Method
		
		public void Dungeon() {
			Reset();
			
			main_text_area.setText("");
			
			choice1_button.setText("");
			choice1_button.setActionCommand("");
			
			choice2_button.setText("");
			choice2_button.setActionCommand("");
			
			choice3_button.setText("");
			choice3_button.setActionCommand("");
		} // Dungeon Method
	} //Class that holds all the location
}

