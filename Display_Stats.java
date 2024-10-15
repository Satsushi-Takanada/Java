package dark_horizon;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Display_Stats {
	UI ui;
	Player_Stats.Health_Stat hstat;
	Player_Stats.Gold_Stat gstat;
	
	JPanel healthStatPanel, moneyStatPanel, holdStatsPanel, Money; // Panel for health, gold and weapon
	JLabel showPlayerStats, playerMoney; //WIll show health gold and weapon as pictures
	JLayeredPane statsPane; //WIll show all stats
	
	ImageIcon healthBar = new ImageIcon("C:/Programming/Java Codes/Text_Adventure/Game Pics/HealthBar.png.png"); //Image of health
	ImageIcon displayBoard = new ImageIcon("C:/Programming/Java Codes/Text_Adventure/Game Pics/WoodBoard.png.png"); //Image that will show stats
	ImageIcon moneyBag = new ImageIcon("C:/Programming/Java Codes/Text_Adventure/Game Pics/MoneyBag.png.png"); //IMage of Gold
	
	public Display_Stats(UI ui, Player_Stats.Health_Stat hstats, Player_Stats.Gold_Stat gstat) {
		this.ui = ui;
		this.hstat = hstats;
		this.gstat = gstat;
	} //Constructor for the classes
	
	public void Stats() {
		statsPane = new JLayeredPane();
		statsPane.setBounds(0, 0, 800, 110);
		ui.gameContainer.add(statsPane);
		//Will declare a layered pane so that i can put what on the top and bottom
		
		healthStatPanel = new JPanel();	
		healthStatPanel.setBounds(20, 20, 220, 80);
		healthStatPanel.setOpaque(false);
		statsPane.add(healthStatPanel, Integer.valueOf(1));

		ui.healthProgressBar.setMaximum(100);
		ui.healthProgressBar.setValue(hstat.Get_Health());
		ui.healthProgressBar.setBounds(95, 39, 128, 32);
		
		JLabel showHealthStat = new JLabel();
		healthStatPanel.add(showHealthStat);		
        showHealthStat.setIcon(healthBar);
        
        moneyStatPanel = new JPanel();	
        moneyStatPanel.setBounds(275, 17, 220, 75);
        moneyStatPanel.setOpaque(false);
		
		JLabel showMoneyStat = new JLabel();	
		moneyStatPanel.add(showMoneyStat);
		showMoneyStat.setIcon(moneyBag);
		statsPane.add(moneyStatPanel, Integer.valueOf(1));
		
		Money = new JPanel();
		Money.setOpaque(false);
		Money.setBounds(411, 33, 50, 50);
		playerMoney = new JLabel();
		playerMoney.setText(String.valueOf(gstat.Get_Gold()));
		playerMoney.setFont(ui.textFont);
		Money.add(playerMoney);
		statsPane.add(Money, Integer.valueOf(2));
        
        holdStatsPanel = new JPanel();	
		holdStatsPanel.setBounds(0, 0, 790, 110);
		holdStatsPanel.setOpaque(false);
		statsPane.add(holdStatsPanel, Integer.valueOf(0));
		
		JLabel showDisplayBoard = new JLabel();
		showDisplayBoard.setIcon(displayBoard);
		holdStatsPanel.add(showDisplayBoard);
	}
}
