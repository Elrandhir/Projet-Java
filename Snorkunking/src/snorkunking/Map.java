package snorkunking;

public class Map {
	int nb_niveau;
    int size_h;
    int size_l;
    int cave1;
    int cave2;
    int cave3;

    Map() {
        int Min1 = 9;
        int Min2 = 6;
        int Min3 = 3;
        int Max1 = 12;
        int Max2 = 9;
        int Max3 = 6;
        this.cave1 = Min1 + (int)(Math.random() * (double)(Max1 - Min1 + 1));
        this.cave2 = Min2 + (int)(Math.random() * (double)(Max2 - Min2 + 1));
        this.cave3 = Min3 + (int)(Math.random() * (double)(Max3 - Min3 + 1));
        this.nb_niveau = this.cave1 + this.cave2 + this.cave3;
        this.size_h = 625;
        this.size_l = 625;
    }

}
