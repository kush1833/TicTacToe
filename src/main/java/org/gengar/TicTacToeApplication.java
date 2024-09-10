package org.gengar;

import org.gengar.constant.GameSymbol;
import org.gengar.model.Board;
import org.gengar.model.Player;
import org.gengar.service.GameOrchestrator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeApplication {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter board size");
        int size = Integer.parseInt(bufferedReader.readLine());
        if((size-1) > GameSymbol.values().length) {
            throw new IOException("Size cannot be greater than: " + GameSymbol.values().length);
        }
        List<Player> playerList = new ArrayList<>();
        for(int i = 0; i < size - 1; i++) {
            System.out.println("Enter Player " + (i+1) + " name.");
            String name = bufferedReader.readLine();
            GameSymbol symbol = GameSymbol.values()[i];
            Player player = new Player();
            player.setPlayerName(name);
            player.setGameSymbol(symbol);
            playerList.add(player);
            System.out.println("Player: " + name+ " is assigned symbol: " + symbol);
        }
        System.out.println("Starting the Game...");
        Board board = new Board(size);

        GameOrchestrator gameOrchestrator = new GameOrchestrator(board, playerList);
        gameOrchestrator.startGame();
    }
}