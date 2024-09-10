package org.gengar.model;

public class Board {
    private final int size;
    private final Player[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new Player[size][size];
    }
    public int getSize() {
        return size;
    }

    public Player[][] getBoard() {
        return board;
    }
}
