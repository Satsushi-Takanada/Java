package dark_horizon;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	public String storyMusic = "C:/Programming/Java Codes/Text_Adventure/Game Music/Story Music.wav"; //Music for story
	public String townMusic = "C:/Programming/Java Codes/Text_Adventure/Game Music/Town Music.wav"; //Music for town
	public String tavernMusic = "C:/Programming/Java Codes/Text_Adventure/Game Music/Tavern Music.wav"; //Music for tavern
	public String shopMusic = "C:/Programming/Java Codes/Text_Adventure/Game Music/Shop Music.wav"; //Music for shop
	
	Clip currentMusic; //Hold current music
	
	public void Background_Music(String current_music) {
		File soundFile = new File(current_music);
		
		try {   
			if (currentMusic != null && currentMusic.isRunning()) {
                currentMusic.stop();
                currentMusic.close();
            }
			
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            currentMusic = AudioSystem.getClip();
            currentMusic.open(audioStream);
            currentMusic.loop(Clip.LOOP_CONTINUOUSLY);  
            currentMusic.start();  
        } catch (Exception e) {
            System.out.println("ERROR");
        }
	} /*This will play the music checks if it is null if not will stop and play the supposed
	 	music for the event*/
}
