package Game.gamefield.creatures;

public class ghost extends Entity{
    Entity ghost;
    String weakness;
    public ghost(int health, int damage) {
        super(health, damage,"ghost");
        this.weakness = "will";
    }
    public String getWeakness() {
        super.getWeakness();
        return weakness;
    }
}
