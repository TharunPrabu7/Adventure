package package01;

public class VisibilityManager {
	
	UI ui;

	public VisibilityManager(UI ui) {
		this.ui = ui;
	}
	
	public void showTitleScreen() {
		
		// Show the title screen
		ui.titleNamePanel.setVisible(true);
		ui.startButtonPanel.setVisible(true);
		
		// Hide the game screen
		ui.mainTextPanel.setVisible(false);
		ui.choiceButtonPanel.setVisible(false);
		ui.playerPanel.setVisible(false);
		
		ui.window.revalidate();
		ui.window.repaint();
	}
	
	public void titleToTown() {
		// Hide the title screen
		ui.titleNamePanel.setVisible(false);
		ui.startButtonPanel.setVisible(false);
		
		// Show the game screen
		ui.mainTextPanel.setVisible(true);
		ui.choiceButtonPanel.setVisible(true);
		ui.playerPanel.setVisible(true);
	}
}
