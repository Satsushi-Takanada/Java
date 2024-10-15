package dark_horizon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
	Choice choice = new Choice();
	UI ui = new UI();
	Music music = new Music();
	Resets reset = new Resets(ui);
	Player_Stats pstats = new Player_Stats();
	Player_Stats.Health_Stat hstat = pstats.new Health_Stat();
	Player_Stats.Gold_Stat gstat = pstats.new Gold_Stat();
	Player_Stats.Weapon_Equip wequip = pstats.new Weapon_Equip();
	Display_Stats dstats = new Display_Stats(ui, hstat, gstat);
	Location location = new Location(ui, music, reset, hstat, dstats, gstat);
	Story story = new Story(ui, music, location, dstats);
	Screen_Show show = new Screen_Show(ui, dstats);
	//Objects for all of the classes and the parameterss
	
	public static void main(String[] args) {
		new Game();
	} 
	
	public Game() {
		ui.Game_UI(choice);
		show.Title_Screen();
	} //will show ui and title
	
	public class Choice implements ActionListener {
		public void actionPerformed(ActionEvent getChoice) {
           String userChoice = getChoice.getActionCommand();
           
           switch (userChoice) {
           case "Start":
        	   show.Game_Start();
        	   story.Story_Start();
        	   break;
           case "Exit":
        	   System.exit(0);
           }
        }
	} //This will check if the user starts or exits the game
}
