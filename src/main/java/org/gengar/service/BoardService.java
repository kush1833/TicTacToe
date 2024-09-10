package org.gengar.service;

import org.gengar.constant.GameState;
import org.gengar.exception.InvalidMoveException;
import org.gengar.model.Board;
import org.gengar.model.Player;

import java.util.List;

/**
 * This class contains business logic to perform all operations that will happen on TicTacToe board during the game
 */
public class BoardService {
    private final Board board;
    private final List<Player> playerList;
    private Player currentPlayer;
    private int totalMoves = 0;


    public BoardService(Board board, List<Player> playerList) {
        this.board = board;
        this.playerList = playerList;
        this.currentPlayer = playerList.getFirst();
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    private void updateCurrentPlayer() {
        int currentPlayerPosition = playerList.indexOf(currentPlayer);
        currentPlayerPosition = (currentPlayerPosition + 1) % (board.getSize()-1);
        this.currentPlayer = playerList.get(currentPlayerPosition);
    }

    public GameState makePlayerMove(int row, int column) throws InvalidMoveException {
        if(row < 0 || row >= board.getSize() || column < 0 || column >= board.getSize()) {
            System.out.println("Invalid Move. Cell is out of bounds");
            throw new InvalidMoveException(row, column);
        }
        if(board.getBoard()[row][column] != null) {
            System.out.println("Invalid Move. Cell is already occupied");
            throw new InvalidMoveException(row, column);
        }
        board.getBoard()[row][column] = currentPlayer;
        totalMoves++;
        if(checkForWinner(currentPlayer, row, column))
            return GameState.WINNER_FOUND;
        if(checkForDraw()) {
            return GameState.DRAW;
        }
        updateCurrentPlayer();
        return GameState.NO_WINNER_CURRENTLY;
    }
    public void displayCurrentBoardState() {
        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                if(board.getBoard()[i][j] == null)
                    System.out.print(" - ");
                else
                    System.out.print(" " + board.getBoard()[i][j].getGameSymbol().getSymbol() + " ");
            }
            System.out.println();
        }
    }
    private boolean checkForWinner(Player player, int row, int column) {
        //check row
        boolean rowWin = true;
        for(int col = 0; col < board.getSize(); col++) {
            if(!player.equals(board.getBoard()[row][col])) {
                rowWin = false;
                break;
            }
        }
        //check col
        boolean colWin = true;
        for(int r = 0; r < board.getSize(); r++) {
            if(!player.equals(board.getBoard()[r][column])) {
                colWin = false;
                break;
            }
        }
        //check diagonal 1
        boolean diag1Win = false;
        if(row == column) {
            diag1Win = true;
            for(int i = 0; i < board.getSize(); i++) {
                if(!player.equals(board.getBoard()[i][i])) {
                    diag1Win = false;
                    break;
                }
            }
        }
        //check diagonal 2
        boolean diag2Win = false;
        if((board.getSize() - 1 - row) == column) {
            diag2Win = true;
            for(int i = 0; i < board.getSize(); i++) {
                if(!player.equals(board.getBoard()[board.getSize() - 1 - i][i])) {
                    diag2Win = false;
                    break;
                }
            }
        }
        return rowWin || colWin || diag1Win || diag2Win;
    }
    private boolean checkForDraw() {
        return totalMoves == (board.getSize() * board.getSize());
    }
}
