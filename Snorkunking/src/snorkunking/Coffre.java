package snorkunking;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Coffre {
	 Object[][] tab;
	    Image treasure;

	    Coffre(int c1, int c2, int c3) {
	        try {
	            this.treasure = new Image("img/chest.jpg");
	        } catch (SlickException var8) {
	            var8.printStackTrace();
	        }

	        int i = 0;
	        int j = 0;
	        int done = 0;
	        this.tab_init();

	        for(; i < 25; j = 0) {
	            for(; j < 25; ++j) {
	                int random = (int)(Math.random() * 11.0D);
	                if (random % 10 == 0) {
	                    ++done;
	                    this.tab[i][j] = Integer.valueOf(1);
	                }
	            }

	            if (done >= c1) {
	                c1 = 100;
	                done = 0;
	                i = 11;
	            } else if (done >= c2) {
	                c2 = 100;
	                done = 0;
	                i = 20;
	            } else if (done >= c3) {
	                i = 25;
	            } else {
	                ++i;
	            }
	        }
	    }

	    void tab_init() {
	        this.tab = new Object[25][25];

	        for(int i = 0; i < this.tab.length; ++i) {
	            for(int j = 0; j < this.tab.length; ++j) {
	                this.tab[i][j] = Integer.valueOf(0);
	            }
	        }

	    }

}
