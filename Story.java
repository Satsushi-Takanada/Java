package dark_horizon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Story {
	String storyPath = "C:/Programming/Java Codes/RPG_Game/Text Files/Intro_Story.txt"; //File for story
	String[] storySentences = File_String_Array(storyPath); //Array that holds the story per sentence
	
	int count  = 0; //index of story sentence array
	UI ui;
	Music music;
	Location location;
	Display_Stats Dstats;
	
	public Story(UI ui, Music music, Location location, Display_Stats Dstats) {
		this.ui = ui;
		this.music = music;
		this.location = location;
		this.Dstats = Dstats;
	} //Constructor for the classes
	
	public String[] File_String_Array(String filePath) { 
		 StringBuilder storyContent = new StringBuilder();
		 
		 try (BufferedReader storyReader = new BufferedReader(new FileReader(filePath))) {
			 String storyLine;
			 while ((storyLine = storyReader.readLine()) != null) {
				 storyContent.append(storyLine).append(" ");
	         }
		 } catch (IOException e) {
			 System.err.println("Error reading file: " + e.getMessage());
	         return null; 
		 }
		 
		 return storyContent.toString().split("(?<=[.!?])\\s+");
	} /*This method will get all the text in the file and put them in the array sentence by
	  sentence using string builder for single thread and the split method*/
	
	public void Update_Story(String userChoice) {
		try {
	        if (userChoice.equals("next")) {
	            if (count < storySentences.length - 1) {  
	                count++;
	            }
	        } else if (userChoice.equals("back")) {
	            if (count > 0) {  
	                count--;
	            }
	        }
	    } catch(Exception e) {
	    	System.out.println("ERROR");
	    }
		finally {
	        ui.mainTextArea.setText(storySentences[count]);
	    }
		ui.choice3Button.setVisible(count == storySentences.length - 1);
	} //This will next and back the story depending on the button that is pressed and displays it
	
	public void Story_Start() {
		music.Background_Music(music.storyMusic);
		ui.mainPanel.setBounds(20, 60, 750, 280);
		ui.mainTextArea.setBounds(20, 60, 750, 280);
		ui.mainTextArea.setText(storySentences[count]);
		
		ui.choice1Button.setText("NEXT");
		ui.choice1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
	            Update_Story("next");
            }
        });
		
		ui.choice2Button.setText("BACK");
		ui.choice2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
	            Update_Story("back");
            }
        });
		
		ui.choice3Button.setText("FINISH");
		ui.choice3Button.setVisible(false);
		ui.choice3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Dstats.Stats();
            	location.Town();
            }
        });
	} //Button for next and back and finish
}
