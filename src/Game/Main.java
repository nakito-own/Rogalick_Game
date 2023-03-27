package Game;

import Game.gamefield.creatures.*;
import Game.gamefield.gameEngine;

public class Main {
    public static void main(String[] args) {
        int n = 15;
        gameEngine game = new gameEngine(n);
        game.displayOptionsAndMakeAMove();
    }
}