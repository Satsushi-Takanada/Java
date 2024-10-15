package dark_horizon;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class Location {
	UI ui;
	Music music;
	Resets reset;
	Player_Stats.Health_Stat hstats;
	Player_Stats.Gold_Stat gstat;
	Display_Stats dstats;
	
	public Location(UI ui, Music music, Resets reset, Player_Stats.Health_Stat hstats, Display_Stats dstats, 	Player_Stats.Gold_Stat gstat) {
		this.ui = ui;
		this.music = music;
		this.reset = reset;
		this.hstats = hstats;
		this.dstats = dstats;
		this.gstat = gstat;
	}
	
	public void Town() {
		reset.Reset_Window();
		music.Background_Music(music.townMusic);
		ui.mainPanel.setBounds(20, 110, 750, 220);
		ui.mainTextArea.setBounds(20, 110, 750, 220);
		ui.healthProgressBar.setVisible(true);
		
		ui.mainTextArea.setText("Welcome to Town!");
		
		ui.choice1Button.setText("Tavern");
		ui.choice1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Tavern();
            }
        });
		
		ui.choice2Button.setText("Shop");
		ui.choice2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Shop();
            }
        });
		
		ui.choice3Button.setText("Dungeon");
		ui.choice3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Dungeon();
            }
        });
	} //Button for all of the plaves that can be visited
	
	public void Tavern() {
		reset.Reset_Window();
		music.Background_Music(music.tavernMusic);
		
		ui.mainTextArea.setText("Welcome to the The Iron Chalice!\n\n"
				+ "Come and Drink or Rest whatever you want!");
		
		ui.choice1Button.setText("Rest");
		ui.choice1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		if (hstats.Get_Health() == 100) {
            			ui.mainTextArea.setText("You're at full health!!");
                	}
            		else if (gstat.Get_Gold() < 5) {
            			ui.mainTextArea.setText("I'm sorry you do not have enough funds.");
            		}
                	else {
                		hstats.Set_Health(100);
                		gstat.Set_Gold(gstat.Get_Gold() - 5);
                		ui.healthProgressBar.setValue(hstats.Get_Health());
                		dstats.playerMoney.setText(String.valueOf(gstat.Get_Gold()));
                		ui.mainTextArea.setText("Have a Nice Rest!!");
                	}  
            	}
            	finally {
            		Timer timer = new Timer(2500, new ActionListener() {
        	            public void actionPerformed(ActionEvent e) {
        	            	ui.mainTextArea.setText("Welcome to the The Iron Chalice!\n\n"
        	        				+ "Come and Drink or Rest whatever you want!");
        	            }
        	        });
            		timer.setRepeats(false);
            		timer.start();
            	}
            }
        });
		
		ui.choice2Button.setText("Leave");
		ui.choice2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ui.mainTextArea.setText("Have a nice day!!");
            	Timer timer = new Timer(2500, new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	            	Town();
    	            }
    	        });
            	timer.setRepeats(false);
        		timer.start();
            }
        });
		
		ui.choice3Button.setVisible(false);
	} /*Will set the text to be the tavern the timer will pause a few sec before changing to another
	 	text*/
	
	public void Shop() {
		reset.Reset_Window();
		music.Background_Music(music.shopMusic);
		
		ui.mainTextArea.setText("Welcome to Herald’s Hammer.\n\n"
				+ "What could i get ya?");
		
		ui.choice1Button.setText("Shop");
		ui.choice1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Shop_Items();
            	
            }
        });
		
		ui.choice2Button.setText("Leave");
		ui.choice2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ui.mainTextArea.setText("Be sure to comeback!");
            	Timer timer = new Timer(2500, new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	            	Town();
    	            }
    	        });
            	timer.setRepeats(false);
        		timer.start();
            }
        });
		
		ui.choice3Button.setVisible(false);
	} /*Will set the text to be the shop the timer will pause a few sec before changing to another
 		text*/
	
	public void Dungeon() {
		reset.Reset_Window();

		ui.choice1Button.setText("");
		ui.choice1Button.setActionCommand("");
		
		ui.choice2Button.setText("");
		ui.choice2Button.setActionCommand("");
		
		ui.choice3Button.setText("");
		ui.choice3Button.setActionCommand("");
	} // Dungeon Method
	
	public void Shop_Items() {
		reset.Reset_Window();
		
		Map<String, Integer> Items = new HashMap<>();
		
		Items.put("Sword", 25);
		Items.put("Swod", 25);
		Items.put("Sord", 25);
		Items.put("Swrd", 25);
		Items.put("Swor", 25);
		
		DefaultListModel<String> itemList = new DefaultListModel<>();
		for (Map.Entry<String, Integer> entry : Items.entrySet()) {
	        String itemWithValue = entry.getKey() + " Price: " + entry.getValue(); // Format as "Key (Value)"
	        itemList.addElement(itemWithValue); // Add formatted string to the list model
	    }
        
		JList<String> list = new JList<>(itemList);
		list.setFont(ui.textFont);
		list.setBackground(Color.black);
		list.setForeground(Color.white);
		list.setBorder(null);
		
        JScrollPane shopPane = new JScrollPane(list);
        shopPane.setBounds(150, 150, 500, 200); 
        shopPane.setBorder(null);
        ui.gameContainer.add(shopPane);
        
        ui.choice1Button.setText("Buy");
		ui.choice1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (itemList.isEmpty()) {
                	ui.mainTextArea.setText("No more stock comeback next time!");
                	Timer timer = new Timer(2500, new ActionListener() {
        	            public void actionPerformed(ActionEvent e) {
        	            	ui.gameContainer.remove(shopPane);
        	            	Town();
        	            }
        	        });
                	timer.setRepeats(false);
            		timer.start();
                }
            	else {
            		int selectedIndex = list.getSelectedIndex();
            		String selectedItem = list.getSelectedValue();
            		String[] parts = selectedItem.split(" Price: "); // Split the string to get the item name and price
                    String itemName = parts[0].trim(); // The item name is the first part
                    int itemPrice = Integer.parseInt(parts[1].trim()); // The price is the second part, parsed to an integer
                    
                    // Now you can use itemName and itemPrice as needed
                    ui.mainTextArea.setText("You bought " + itemName + " for " + itemPrice + " gold!");
                    
                    // Remove the item from the list
                    itemList.removeElementAt(selectedIndex);// Remove the item at the selected index
            	}
            	
            }
        });
		
		ui.choice2Button.setText("Leave");
		ui.choice2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ui.mainTextArea.setText("Be sure to comeback!");
            	Timer timer = new Timer(2500, new ActionListener() {
    	            public void actionPerformed(ActionEvent e) { 
    	            	ui.gameContainer.remove(shopPane);
    	            	Town();
    	            }
    	        });
            	timer.setRepeats(false);
        		timer.start();
            }
        });
		
		ui.choice3Button.setVisible(false);
	}
}
