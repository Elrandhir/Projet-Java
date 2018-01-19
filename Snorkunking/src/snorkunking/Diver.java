package snorkunking;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Diver {
    int nb_point = 0;
    int nb_coffre = 0;
    float posX = 150.0F;
    float posY = 25.0F;
    float futurX;
    float futurY;
    Image sprite = new Image("img/diver.png");
    boolean colision = false;

    Diver() throws SlickException {
    }

    void descendre(int delta) {
        this.futurY = this.posY + 0.2F * (float)delta;
        if (this.futurY >= 600.0F) {
            this.futurY = 599.0F;
        }

        Oxygene.quantity -= 1 + this.nb_coffre;
    }

    void monter(int delta) {
        this.futurY = this.posY - 0.2F * (float)delta;
        if (this.futurY <= 0.0F) {
            this.futurY = 0.1F;
        }

        Oxygene.quantity -= 1 + this.nb_coffre;
    }

    void pickup() {

        nb_coffre += 1;
        --Oxygene.quantity;
        if(this.posY <= 12*25){
            int rand = (int)Math.random()*(3-1);
            nb_point += rand;
        }
        if(this.posY >= 12*25 && posY <= 20*25){
            int rand = (int)Math.random()*(8-5);
            nb_point += rand;
        }
        if(this.posY >= 21*25){
            int rand = (int)Math.random()*(12-10);
            nb_point += rand;
        }
    }

    void gauche(int delta) {
        this.futurX = this.posX - 0.2F * (float)delta;
        if (this.futurX <= 0.0F) {
            this.futurX = 0.1F;
        }

        Oxygene.quantity -= 1 + this.nb_coffre;

    }

    void droite(int delta) {
        this.futurX = this.posX + 0.2F * (float)delta;
        if (this.futurX >= 600.0F) {
            this.futurX = 599.0F;
        }

        Oxygene.quantity -= 1 + this.nb_coffre;

    }
}
