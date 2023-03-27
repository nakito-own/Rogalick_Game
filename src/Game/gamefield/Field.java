package Game.gamefield;

public class Field {
    public cell[][] field;

    public Field(int n){
        this.field = new cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[j][i] = new cell();
                if ((j % 2 != 0 && i % 2 != 0) && (j < n - 1 && i < n - 1)) {
                    field[j][i].setState(0);
                    field[j][i].setX(j);
                    field[j][i].setY(i);
                } else {
                    field[j][i].setState(-1);
                    field[j][i].setX(j);
                    field[j][i].setY(i);
                }
            }
        }

    }
}
