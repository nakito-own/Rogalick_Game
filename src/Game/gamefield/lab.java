package Game.gamefield;

import Game.gamefield.creatures.*;

import java.util.ArrayList;
import java.util.Stack;

public class lab extends Field{

    public Field labirinth;
    int n;
    public lab(int n){
        super(n);
        this.n = n;
        this.labirinth = new Field(n);
        generateLab(n);
    }
    int unvisitedCount(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (labirinth.field[j][i].getState() == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void generateLab(int n){
        Stack<cell> path = new Stack<>();
        ArrayList<String> creatures = new ArrayList<>();
        creatures.add("skeleton");
        creatures.add("dragon");
        creatures.add("ghost");
        creatures.add("dummy");
        creatures.add("dummy");
        creatures.add("dummy");
        labirinth.field[1][1].setState(1);
        path.push(labirinth.field[1][1]);
        path.peek().setCreature(new Player(30,10));
        while (unvisitedCount(n) != 0) {
            int x = path.peek().getX();
            int y = path.peek().getY();
            String dir = lookAround(path.peek());
            if (dir.equals("r")) {
                labirinth.field[x+1][y].setState(1);
                labirinth.field[x+2][y].setState(1);
                path.push(labirinth.field[x+2][y]);
            }
            if (dir.equals("l")) {
                labirinth.field[x-1][y].setState(1);
                labirinth.field[x-2][y].setState(1);
                path.push(labirinth.field[x-2][y]);
            }
            if (dir.equals("d")) {
                labirinth.field[x][y+1].setState(1);
                labirinth.field[x][y+2].setState(1);
                path.push(labirinth.field[x][y+2]);
            }
            if (dir.equals("u")) {
                labirinth.field[x][y-1].setState(1);
                labirinth.field[x][y-2].setState(1);
                path.push(labirinth.field[x][y-2]);
            }
            if (dir.equals("back")) {
                path.pop();
            }
            if(creatures.get((int)Math.round(Math.random()*(creatures.size()-1))).equals("dummy")) {
                path.peek().setCreature(new dummy(0,0));
            }
            if(creatures.get((int)Math.round(Math.random()*(creatures.size()-1))).equals("skeleton")) {
                path.peek().setCreature(new skeleton(7,5));
            }
            if(creatures.get((int)Math.round(Math.random()*(creatures.size()-1))).equals("dragon")) {
                path.peek().setCreature(new dragon(7,5));
            }
            if(creatures.get((int)Math.round(Math.random()*(creatures.size()-1))).equals("ghost")) {
                path.peek().setCreature(new ghost(7,5));
            }

        }
        System.out.println();

    }

    public String lookAround(cell grid){
        ArrayList<String> dir = new ArrayList<>();

        if (grid.getX() + 2 < n-1 && labirinth.field[grid.getX() + 2][grid.getY()].getState() == 0) {
            dir.add("r");
        }
        if (grid.getX() - 2 > 0 && labirinth.field[grid.getX() - 2][grid.getY()].getState() == 0) {
            dir.add("l");
        }
        if (grid.getY() + 2 < n-1 && labirinth.field[grid.getX()][grid.getY() + 2].getState() == 0) {
            dir.add("d");
        }
        if (grid.getY() - 2 > 0 && labirinth.field[grid.getX()][grid.getY() - 2].getState() == 0){
            dir.add("u");
        }
        return dir.size() != 0 ? dir.get((int)Math.round(Math.random()*(dir.size()-1))) : "back";
    }

}
