package Game.gamefield;

import Game.gamefield.creatures.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class gameEngine{

    lab gameField;
    Entity player;
    Stack<cell> playerMovement;
    public gameEngine(int n){
        this.gameField = new lab(n);
        this.player = gameField.labirinth.field[1][1].getCreature();
        this.playerMovement = new Stack<>();
    }

    private ArrayList<String> movementDirections(cell grid) {
        ArrayList<String> dirs = new ArrayList<>();
        if (gameField.labirinth.field[grid.getX() + 1][grid.getY()].getState() == 1) {
            dirs.add("r");
        }
        if (gameField.labirinth.field[grid.getX() - 1][grid.getY()].getState() == 1) {
            dirs.add("l");
        }
        if (gameField.labirinth.field[grid.getX()][grid.getY() + 1].getState() == 1) {
            dirs.add("d");
        }
        if (gameField.labirinth.field[grid.getX()][grid.getY() - 1].getState() == 1) {
            dirs.add("u");
        }
        return dirs;
    }
    public void displayOptionsAndMakeAMove(){
        Scanner option = new Scanner(System.in);
        playerMovement.push(gameField.labirinth.field[1][1]);
        while (true){
            printField();
            ArrayList<String> dirs = movementDirections(playerMovement.peek());
            for (int i = 0; i < dirs.size(); i++) {
                System.out.print((i+1)+")");
                if (dirs.get(i).equals("r")){
                    System.out.println(" Пойти на право");
                }
                if (dirs.get(i).equals("l")){
                    System.out.println(" Пойти на лево");
                }
                if (dirs.get(i).equals("d")){
                    System.out.println(" Пойти вниз");
                }
                if (dirs.get(i).equals("u")){
                    System.out.println(" Пойти вверх");
                }
            }
            int var = option.nextInt();
            if (!checkForEnemy(playerMovement.peek(), dirs.get(var-1))) {
                movePlayer(playerMovement.peek(), dirs.get(var-1));
            }
            else {
                initiateCombat(playerMovement.peek(), dirs.get(var-1));
            }
        }
    }
    public boolean checkForEnemy(cell currentPos, String dir){
        int x = currentPos.getX();
        int y = currentPos.getY();
        if (dir.equals("r")){
            if (!gameField.labirinth.field[x+1][y].getCreature().getType().equals("dummy")){
                return true;
            }
        }
        if (dir.equals("l")){
            if (!gameField.labirinth.field[x-1][y].getCreature().getType().equals("dummy")){
                return true;
            }
        }
        if (dir.equals("d")){
            if (!gameField.labirinth.field[x][y+1].getCreature().getType().equals("dummy")){
                return true;
            }
        }
        if (dir.equals("u")){
            if (!gameField.labirinth.field[x][y-1].getCreature().getType().equals("dummy")){
                return true;
            }
        }
        return false;
    }

    public void initiateCombat(cell currentPos, String dir) {
        Scanner option = new Scanner(System.in);
        int x = currentPos.getX();
        int y = currentPos.getY();
        System.out.print("Вы встретели врага! Это ");
        Player currPlayer = (Player)currentPos.getCreature();
        cell monsterLocation = null;
        if (dir.equals("r")){
            Entity monster = gameField.labirinth.field[x+1][y].getCreature();
            monsterLocation = gameField.labirinth.field[x+1][y];
            if (monster.getType().equals("skeleton")){
                System.out.println("скелет! Любопытно, каким образом он способен стоять, если у него нет мышц, ну да ладно..\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это былo жесть сложно, не так ли?");
            }
            if (monster.getType().equals("dragon")){
                System.out.println("дракон! Вау... Никто же не ожидал дракона в игре про подземелья, да?\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("ghost")){
                System.out.println("призрак! , или приведение! , или полтэргейст, а вообще это сгусток метафизической энерг.. в прочем не важно.\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
        }
        if (dir.equals("l")){
            Entity monster = gameField.labirinth.field[x-1][y].getCreature();
            monsterLocation = gameField.labirinth.field[x-1][y];
            if (monster.getType().equals("skeleton")){
                System.out.println("скелет! Любопытно, каким образом он способен стоять, если у него нет мышц, ну да ладно..\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("dragon")){
                System.out.println("дракон! Вау... Никто же не ожидал дракона в игре про подземелья, да?\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("ghost")){
                System.out.println("призрак! , или приведение! , или полтэргейст, а вообще это сгусток метафизической энерг.. в прочем не важно.\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
        }
        if (dir.equals("d")){
            Entity monster = gameField.labirinth.field[x][y+1].getCreature();
            monsterLocation = gameField.labirinth.field[x][y+1];
            if (monster.getType().equals("skeleton")){
                System.out.println("скелет! Любопытно, каким образом он способен стоять, если у него нет мышц, ну да ладно..\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("dragon")){
                System.out.println("дракон! Вау... Никто же не ожидал дракона в игре про подземелья, да?\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("ghost")){
                System.out.println("призрак! , или приведение! , или полтэргейст, а вообще это сгусток метафизической энерг.. в прочем не важно.\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
        }
        if (dir.equals("u")){
            Entity monster = gameField.labirinth.field[x][y-1].getCreature();
            monsterLocation = gameField.labirinth.field[x][y-1];
            if (monster.getType().equals("skeleton")){
                System.out.println("скелет! Любопытно, каким образом он способен стоять, если у него нет мышц, ну да ладно..\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("dragon")){
                System.out.println("дракон! Вау... Никто же не ожидал дракона в игре про подземелья, да?\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
            if (monster.getType().equals("ghost")){
                System.out.println("призрак! , или приведение! , или полтэргейст, а вообще это сгусток метафизической энерг.. в прочем не важно.\nИспользуй что-то из своих умений!");
                currPlayer.displayAttacks();
                while (monster.getHealth() > 0){
                    int var = option.nextInt();
                    String attack = "none";
                    if (var == 1){
                        attack = "swipe";
                    }
                    if (var == 2){
                        attack = "smash";
                    }
                    if (var == 3){
                        attack = "will";
                    }
                    System.out.println("Ты используешь " + attack + "!");
                    if (attack.equals(monster.getWeakness())) {
                        monster.setHealth(monster.getHealth()-currPlayer.getDamage());
                    }
                    else {
                        monster.setHealth(monster.getHealth()-(currPlayer.getDamage()-4));
                    }
                    if (monster.getHealth() <= 0){
                        break;
                    }
                    else {
                        System.out.println("Ай! Монстр оказался не так прост, как казалось, он выжил и ударил в ответ! Ты теряешь 5 ед. здоровья(считай, руку отрезали, ведь у тебя всего 30 хп)\nПродолжай бить его!");
                        currPlayer.setHealth(currPlayer.getHealth()-monster.getDamage());
                    }
                }
                System.out.println("Фух! Ты выжил в этой схватке, это был жесть сложно, не так ли?");
            }
        }
        monsterLocation.setCreature(new dummy(0, 0));
        currentPos.setCreature(currPlayer);
    }
    public void movePlayer(cell currentPos, String dir){
        int x = currentPos.getX();
        int y = currentPos.getY();
        Entity dummy = new dummy(0,0);
        if (dir.equals("r")){
            gameField.labirinth.field[x+1][y].setCreature(currentPos.getCreature());
            currentPos.setCreature(dummy);
            playerMovement.push(gameField.labirinth.field[x+1][y]);
        }
        if (dir.equals("l")){
            gameField.labirinth.field[x-1][y].setCreature(currentPos.getCreature());
            currentPos.setCreature(dummy);
            playerMovement.push(gameField.labirinth.field[x-1][y]);
        }
        if (dir.equals("d")){
            gameField.labirinth.field[x][y+1].setCreature(currentPos.getCreature());
            currentPos.setCreature(dummy);
            playerMovement.push(gameField.labirinth.field[x][y+1]);
        }
        if (dir.equals("u")){
            gameField.labirinth.field[x][y-1].setCreature(currentPos.getCreature());
            currentPos.setCreature(dummy);
            playerMovement.push(gameField.labirinth.field[x][y-1]);
        }
    }
    public void printField(){
        int n = gameField.field.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (gameField.labirinth.field[j][i].getState() == 1 && gameField.labirinth.field[j][i].getCreature().getType().equals("player")) {
                    System.out.print("P ");
                } else if (gameField.labirinth.field[j][i].getState() == 1 && !gameField.labirinth.field[j][i].getCreature().getType().equals("player") && !gameField.labirinth.field[j][i].getCreature().getType().equals("dummy")){
                    System.out.print("X ");
                } else if (gameField.labirinth.field[j][i].getState() == 1) {
                    System.out.print("  ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
        System.out.println("Твое текущее здоровье: "+playerMovement.peek().getCreature().getHealth());
    }
}