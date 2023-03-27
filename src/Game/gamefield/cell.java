package Game.gamefield;

import Game.gamefield.creatures.*;

public class cell{
    int x;
    int y;
    int state;
    Entity creature;
    public cell(){
        this.state = 0;
        this.x = 0;
        this.y = 0;
        this.creature = new dummy(0,0);
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCreature(Entity creature){
        this.creature = creature;
    }

    public int getState() {
        return state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Entity getCreature() {
        return creature;
    }
}
