package dark_horizon;

public class Screen_Show {
	UI ui;
	Display_Stats dstats;
	
	public Screen_Show(UI ui, Display_Stats dstats) {
		this.ui = ui;
		this.dstats = dstats;
	} //Constructor for the classes
	
	public void Title_Screen() {
		ui.titlePanel.setVisible(true);
		ui.startButtonPanel.setVisible(true);
		
		ui.mainPanel.setVisible(false);
		ui.choicesPanel.setVisible(false);
		ui.healthProgressBar.setVisible(false);
	} //CAlls the title screen upon starting
	
	public void Game_Start() {
		ui.gameContainer.remove(ui.titlePanel);
		ui.gameContainer.remove(ui.startButtonPanel);
		
		ui.mainPanel.setVisible(true);
		ui.choicesPanel.setVisible(true);	
	} //Switches to stoty after pressing start hides the title and start button panel
}
