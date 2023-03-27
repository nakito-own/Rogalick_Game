package Game.gamefield.creatures;
import Game.gamefield.*;

public class Entity {
    int health;
    int damage;
    String type;
    Entity(int health, int damage,String type){
        this.health = health;
        this.damage = damage;
        this.type = type;
    }

    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }

    public String getWeakness(){
        return "none";
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setType(String type) {
        this.type = type;
    }


}
