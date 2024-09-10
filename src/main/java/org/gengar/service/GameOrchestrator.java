package org.gengar.service;

import org.gengar.constant.GameState;
import org.gengar.exception.InvalidMoveException;
import org.gengar.model.Board;
import org.gengar.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.gengar.constant.GameState.DRAW;
import static org.gengar.constant.GameState.NO_WINNER_CURRENTLY;

/**
 * This class contains business logic to carry out the game until a player wins or game is a draw
 */
public class GameOrchestrator {
    private final BoardService boardService;
    private final BufferedReader bufferedReader;

    public GameOrchestrator(Board board, List<Player> playerList) {
        assert board.getSize() - 1 == playerList.size();
        this.boardService = new BoardService(board, playerList);
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Display name of winner or draw message in case no player wins
     */
    public void startGame() throws IOException {
        GameState currentGameState = NO_WINNER_CURRENTLY;
        while (NO_WINNER_CURRENTLY.equals(currentGameState)) {
            boardService.displayCurrentBoardState();
            System.out.println("Current Player: " + boardService.getCurrentPlayer().getPlayerName() +
                    ". Player Symbol: " + boardService.getCurrentPlayer().getGameSymbol());
            int retry = 3;
            while(true) {
                System.out.println("Enter row and column to mark. Format row <space> column");
                String[] input = bufferedReader.readLine().split(" ");
                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);
                try {
                    currentGameState = boardService.makePlayerMove(row, col);
                    break;
                } catch (InvalidMoveException e) {
                    System.out.println(e.getMessage());
                    retry--;
                    if(retry == 0)
                        throw e;
                    System.out.println("Retries remaining: " + retry);
                }
            }
        }
        if(DRAW.equals(currentGameState))
            System.out.println("Game Ended in A Draw");
        else {
            System.out.println("Player: " + boardService.getCurrentPlayer().getPlayerName() + " won the game.");
            boardService.displayCurrentBoardState();
        }
    }
}
