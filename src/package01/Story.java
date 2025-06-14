package package01;

import java.util.Random;

import package02.Monster_Goblin;
import package02.Monster_Ogre;
import package02.SuperMonster;
import package02.Weapon_Knife;
import package02.Weapon_LongSword;

public class Story {

	Game game;
	UI ui;
	VisibilityManager vm;
	Player player = new Player();
	SuperMonster monster;
	int silverRing;
	
	public Story(Game game, UI ui, VisibilityManager vm) {
		this.game = game;
		this.ui = ui;
		this.vm = vm;
	}
	
	public void defaultSetup() {
		
		player.playerHP = 10;
		ui.hpLabelNumber.setText("" + player.playerHP);
		
		player.currentWeapon = new Weapon_Knife();
		ui.weaponLabelName.setText(player.currentWeapon.name);
		
		silverRing = 0;
	}
	
	public void selectPosition(String nextPosition) {
		switch(nextPosition) {
		case "talkGuard": talkGuard(); break;
		case "attackGuard": attackGuard(); break;
		case "crossRoad": crossRoad(); break;
		case "north": north(); break;
		case "east": east(); break;
		case "west": west(); break;
		case "townGate": townGate(); break;
		case "playerAttack": playerAttack(); break;
		case "monsterAttack": monsterAttack(); break;
		case "win": win(); break;
		case "lose": lose(); break;
		case "fight": fight(); break;
		case "toTitle": toTitle(); break;
		}
	}
	
	public void townGate() {
		ui.mainTextArea.setText("You are at the gate of the town. \nA guard is standing in front of you. \n\nWhat do you do?");
		ui.choice1.setText("Talk to the guard");
		ui.choice2.setText("Attack the guard");
		ui.choice3.setText("Leave");
		ui.choice4.setText("");
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
		
		
		game.nextPosition1 = "talkGuard";
		game.nextPosition2 = "attackGuard";
		game.nextPosition3 = "crossRoad";
		game.nextPosition4 = "";
		
	}
	
	public void talkGuard() {
		
		if(silverRing!=1) {
			ui.mainTextArea.setText("Guard: Hello stranger. I have never seen your face. \nI'm sorry but we cannot let a stranger enter our town.");
			ui.choice1.setText("<");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "townGate";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
		else
		{
			ending();
		}
	}
	
	public void attackGuard() {
		if(player.playerHP>0) {
			ui.mainTextArea.setText("Guard: Hey! Don't be stupid.\n\nThe guard fought back and hit you hard. \n(You receive 3 damage)");
			player.playerHP = player.playerHP - 3;
			if(player.playerHP<0) {lose();}
			ui.hpLabelNumber.setText("" + player.playerHP);
			ui.choice1.setText("<");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "townGate";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
		else
		{
			lose();
		}
		
	}
	
	public void crossRoad() {
		ui.mainTextArea.setText("You are at the crossroad. \nIf you go south, you will go back to the town.");
		ui.choice1.setText("Go North");
		ui.choice2.setText("Go East");
		ui.choice3.setText("Go South");
		ui.choice4.setText("Go West");
		
		game.nextPosition1 = "north";
		game.nextPosition2 = "east";
		game.nextPosition3 = "townGate";
		game.nextPosition4 = "west";
		
	}
	
	public void north() {
		ui.mainTextArea.setText("There is a river. \nYou drink the water and rest at the riverside. \n\n(Your HP is recovered by 2)");
		
		player.playerHP = player.playerHP + 2;
		ui.hpLabelNumber.setText("" + player.playerHP);
		
		ui.choice1.setText("Go South");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	
	public void east() {
		ui.mainTextArea.setText("You walked into a forest and found a Long Sword! \n\n(You obtained a Long Sword)");
		
		player.currentWeapon = new Weapon_LongSword();
		ui.weaponLabelName.setText(player.currentWeapon.name);
		
		ui.choice1.setText("Go West");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	
	public void west() {
		
		int i = new Random().nextInt(100) + 1;
		
		if(i<70)
			monster = new Monster_Goblin();
		else
			monster = new Monster_Ogre();
		
		ui.mainTextArea.setText("You encounter a " + monster.name + "!");
		ui.choice1.setText("Fight");
		ui.choice2.setText("Run");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "fight";
		game.nextPosition2 = "crossRoad";
		game.nextPosition3 = "";
		game.nextPosition4 = "";

	}
	
	public void fight() {
		
		ui.mainTextArea.setText(monster.name + ": " + monster.hp + "\n\nWhat do you do?");
		ui.choice1.setText("Attack");
		ui.choice2.setText("Run");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "playerAttack";
		game.nextPosition2 = "crossRoad";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	
	public void playerAttack() {
		int playerDamage = new Random().nextInt(player.currentWeapon.damage);
		monster.hp = monster.hp - playerDamage;
		ui.mainTextArea.setText("You attacked " + monster.name + " and gave " + playerDamage + " damage");
		ui.choice1.setText(">");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		if(monster.hp>0) {
			game.nextPosition1 = "monsterAttack";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
		else if(monster.hp<1) {
			game.nextPosition1 = "win";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}		
		
	}
	
	public void monsterAttack() {
		int monsterDamage = new Random().nextInt(monster.attack);
		player.playerHP = player.playerHP - monsterDamage;
		ui.hpLabelNumber.setText(""+player.playerHP);
		ui.mainTextArea.setText( monster.name + " attacked you and gave " + monsterDamage + " damage");
		ui.choice1.setText(">");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		if(player.playerHP>0) {
			game.nextPosition1 = "fight";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
		else if(player.playerHP<1) {
			game.nextPosition1 = "lose";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
	}
	
	public void win() {
		silverRing = 1;
		ui.mainTextArea.setText("You defeated the monster! \nThe monster dropped a ring! \n\n(You obtained a silver ring)");
		ui.choice1.setText("Go East");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	
	public void lose() {
		ui.mainTextArea.setText("You are dead! \n\nGAME OVER");
		ui.choice1.setText("To the title Screen");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
		
		game.nextPosition1 = "toTitle";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	
	public void ending() {
		ui.mainTextArea.setText("Guard: Oh you killed that goblin! \nThank you so much. Welcome to our town! \n\nTHE END");
		ui.choice1.setText("");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		ui.choice1.setVisible(false);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void toTitle() {
		defaultSetup();
		vm.showTitleScreen();
		
	}
}
