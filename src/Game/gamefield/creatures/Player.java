package Game.gamefield.creatures;


public class Player extends Entity {
    Player player;
    String swipe;
    String smash;
    String will;

    public Player(int health, int damage) {
        super(health, damage, "player");
        this.swipe = "swipe";
        this.smash = "smash";
        this.will = "will";
    }
    public void displayAttacks(){
        System.out.println("1) Рубануть");
        System.out.println("2) Вмазать");
        System.out.println("3) Игнорировать на характере");
    }
}