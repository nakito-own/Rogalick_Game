package Game.gamefield.creatures;

public class dummy extends Entity{
    Entity dummy;
    public dummy(int health, int damage){
        super(health, damage, "dummy");
        this.dummy = new Entity(health, damage, "dummy");
    }
}
