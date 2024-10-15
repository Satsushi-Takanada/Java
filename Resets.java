package dark_horizon;

import java.awt.event.ActionListener;

public class Resets {
	UI ui;
	
	public Resets(UI ui) {
		this.ui = ui;
	}
	
	public void Reset_Window() {
		ui.gameContainer.remove(ui.mainPanel);
		ui.gameContainer.remove(ui.choicesPanel);
        
		ui.gameContainer.add(ui.mainPanel);
		ui.gameContainer.add(ui.choicesPanel);
		
		ui.choice1Button.setVisible(true);
		ui.choice2Button.setVisible(true);
		ui.choice3Button.setVisible(true);
		
		ui.mainTextArea.setText("");
		
		for (ActionListener AL : ui.choice1Button.getActionListeners()) {
			ui.choice1Button.removeActionListener(AL);
        }
        for (ActionListener AL : ui.choice2Button.getActionListeners()) {
        	ui.choice2Button.removeActionListener(AL);
        }
        for (ActionListener AL : ui.choice3Button.getActionListeners()) {
        	ui.choice3Button.removeActionListener(AL);
        }
        
        ui.gameContainer.repaint();
	} /*This will reset everything so that it wont overlap in the window*/
}
