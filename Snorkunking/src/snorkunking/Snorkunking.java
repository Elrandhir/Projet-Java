package snorkunking;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


public class Snorkunking extends BasicGame {
    private TiledMap sunkenMap;
    private Coffre treasurMap;
    private Map map;
    private Oxygene reserve;
    private float x = 25.0F;
    private float y = 25.0F;
    private Diver j1;
    private Diver j2;
    private Image treasure;
    private Image tile;
    private boolean moving = false;
    private int direction = 0;
    public static final int MAP_SIZE_W = 25;
    public static final int MAP_SIZE_H = 25;
    private static int scoreJ1 = 0;
    private static int scoreJ2 = 0;
    private static int phase = 0;
    private static boolean IA = true;
    private static boolean J1win = true;



    public Snorkunking(String gamename) {

        super(gamename);

    }

    public void init(GameContainer gc) throws SlickException {
        this.sunkenMap = new TiledMap("data/sunken_map.tmx");
        this.map = new Map();
        this.reserve = new Oxygene(this.map.nb_niveau);
        this.treasurMap = new Coffre(this.map.cave1, this.map.cave2, this.map.cave3);
        this.treasure = new Image("img/chest.jpg");
        this.j1 = new Diver();
        this.j2 = new Diver();

    }
// Cette partie gere les entrées clavier
    public void update(GameContainer gc, int i) throws SlickException {
        Input input = gc.getInput();
        this.j1.futurX = this.j1.posX;
        this.j1.futurY = this.j1.posY;
        this.j2.futurX = this.j2.posX;
        this.j2.futurY = this.j2.posY;

        if (IA == true) {
            int randomInt = new Random().nextInt(5);

            if (this.moving) {

                if (randomInt == 0) {
                    //this.j2.pickup();
                }
                if (randomInt == 1) {
                    this.j2.monter(i);
                }
                if (randomInt == 2) {
                    this.j2.descendre(i);
                }
                if (randomInt == 3) {
                    this.j2.gauche(i);
                }
                if (randomInt == 4) {
                    this.j2.droite(i);
                }
            }

            if (this.moving) {
                switch (this.direction) {
                    case 0:
                        this.j1.monter(i);
                        break;
                    case 1:
                        this.j1.gauche(i);
                        break;
                    case 2:
                        this.j1.descendre(i);
                        break;
                    case 3:
                        this.j1.droite(i);
                }
// La partie suivante gere les collisions en interrogeant la carte logique
                this.tile = this.sunkenMap.getTileImage((int) this.j1.futurX / this.sunkenMap.getTileWidth(), (int) this.j1.futurY / this.sunkenMap.getTileHeight(), this.sunkenMap.getLayerIndex("Logic"));
                this.j1.colision = this.tile != null;
                if (this.j1.colision) {
                    this.moving = false;
                } else {
                    this.j1.posX = this.j1.futurX;
                    this.j1.posY = this.j1.futurY;
                }
                this.tile = this.sunkenMap.getTileImage((int) this.j2.futurX / this.sunkenMap.getTileWidth(), (int) this.j2.futurY / this.sunkenMap.getTileHeight(), this.sunkenMap.getLayerIndex("Logic"));
                this.j2.colision = this.tile != null;
                if (this.j2.colision) {
                    this.moving = false;
                } else {
                    this.j2.posX = this.j2.futurX;
                    this.j2.posY = this.j2.futurY;
                }
            }

        }
    }

    public static void finPartie(int phase, boolean J1win) {
        if(scoreJ1 > scoreJ2){
            J1win = true;
        }
        else {
            J1win = false;
        }
        if (phase == 3) {
            if (J1win == true) {
                System.out.println("J1 vainqueur");
            } else {
                System.out.println("J2 vainqueur");
            }

            System.out.println("Try again?");
            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();

            if (answer == "yes") {
            }
        }
    }


    public void render(GameContainer gc, Graphics g) throws SlickException {

        this.sunkenMap.render(0, 0);
        this.generateChest(this.sunkenMap, this.treasurMap);
        this.j1.sprite.draw(this.j1.posX, this.j1.posY);
        this.j2.sprite.draw(this.j2.posX, this.j2.posY);
        if(Oxygene.quantity >= 0) {
            g.drawString("Réserve", 650, 300);
            g.drawString("d'oxygène:", 650, 325);
            g.drawString("" + Oxygene.quantity, 650, 350);
        }
        if (Oxygene.quantity <= 0) {
            g.drawString("FIN DE PHASE !", 300, 700);
            if (j1.posY == 0) {
                scoreJ1 += j1.nb_point;
            }
            else {
                // C'est la qu'on ajoute a un coffre du fond les tresors de J1
            }
            if (j2.posY == 0) {
                scoreJ2 += j2.nb_point;
            }
            else {
                // C'est la qu'on ajoute a un coffre du fond les tresors de J2
            }
            phase += 1;
            g.drawString("Score du J1:" + scoreJ1, 300, 725);
            g.drawString("Score du J2:" + scoreJ2, 300, 750);

            this.moving = false;

        }


    }

    public void menu() {
    }

    public void startGame() {
    }

    public void startTurn() {
    }

    public void keyReleased(int key, char c) {
        this.moving = false;
    }

    public void keyPressed(int key, char c) {
        switch(key) {
        case 32:
            this.j1.pickup();
            this.moving = false;
            break;
        case 200:
            this.direction = 0;
            this.moving = true;
            break;
        case 203:
            this.direction = 1;
            this.moving = true;
            break;
        case 205:
            this.direction = 3;
            this.moving = true;
            break;
        case 208:
            this.direction = 2;
            this.moving = true;
        }

    }

    public void generateChest(TiledMap map, Coffre treasure) {
        for(int i = 0; i < map.getTileWidth(); ++i) {
            for(int j = 0; j < map.getTileHeight(); ++j) {
                Image tile = map.getTileImage(i, j, map.getLayerIndex("Logic"));
                if (tile != null) {
                    int y = j * 25;
                    int x = i * 25;
                    treasure.treasure.draw((float)x, (float)y);
                }
            }
        }

    }



    public static void main(String[] args) {

        try {
            AppGameContainer appgc = new AppGameContainer(new Snorkunking("Snorkunking"));
            appgc.setDisplayMode(800, 800, false);
            appgc.start();

            if(phase == 3){
                if (J1win == true){
                    System.out.println("J1 vainqueur");
                }
                else {
                    System.out.println("J2 vainqueur");
                }
                appgc.exit();

                System.out.println("Try again?");
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();

                if (answer == "yes"){
                    main(args); //Faire attention au stackoverflow si ça tourne trop longtemps
                }

            }

        } catch (SlickException var2) {
            Logger.getLogger(Snorkunking.class.getName()).log(Level.SEVERE, (String)null, var2);
        }


    }
}
