package org.gengar.constant;

public enum GameSymbol {
    CROSS('X'),
    CIRCLE('O');
    private final char symbol;
    GameSymbol(char ch) {
        this.symbol = ch;
    }
    public char getSymbol() {
        return this.symbol;
    }
}
