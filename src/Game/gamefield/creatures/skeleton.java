package Game.gamefield.creatures;

public class skeleton extends Entity {
    Entity skeleton;
    String weakness;
    public skeleton(int health, int damage){
        super(health,damage, "skeleton");
        this.weakness = "smash";
    }

    public String getWeakness() {
        super.getWeakness();
        return weakness;
    }
}
