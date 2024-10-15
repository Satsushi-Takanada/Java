package dark_horizon;

public class Player_Stats {
	public class Health_Stat {
		private int health = 80;
		
		public void Set_Health(int health) {
			this.health = health;
		}
		
		public int Get_Health() {
			return health;
		}
	} //Sets and Gets the health of the player
	
	public class Gold_Stat {
		private int gold = 50;
		
		public void Set_Gold(int gold) {
			this.gold = gold;
		}
		
		public int Get_Gold() {
			return gold;
		}
	} //Sets and Gets the gold of the player
	
	public class Weapon_Equip {
		private String weapon;
		
		public void Set_Weapon(String weapon) {
			this.weapon = weapon;
		}
		
		public String Get_Weapon() {
			return weapon;
		}
	} //Sets and Gets the Weapon of the player
}
