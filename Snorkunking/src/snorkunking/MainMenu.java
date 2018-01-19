package snorkunking;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.gui.MouseOverArea;

public class MainMenu extends BasicGameState {

    //Constructeur rapide pour les etats
    public MainMenu(int startMenu){

    }

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Player vs Player", 400, 400);
		g.drawString("Player vs Computer", 400, 500);
		g.drawString("Exit", 400, 600);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		// Pour reperer les limites des boutons a creer
		System.out.println("X:" + posX + "Y:" + posY);
		
		// A faire pour chaque bouton
		/*if((posX > && posX < ) && (posY > && posY < )) {
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(id); //System.exit(0) pour quitter
			}
		}*/
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
