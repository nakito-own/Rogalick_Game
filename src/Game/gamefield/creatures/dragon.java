package Game.gamefield.creatures;

public class dragon extends Entity {
    Entity dragon;
    String weakness;
    public dragon(int health, int damage) {
        super(health, damage,"dragon");
        this.weakness = "swipe";
    }
    public String getWeakness() {
        super.getWeakness();
        return weakness;
    }
}
