package org.gengar.model;

import org.gengar.constant.GameSymbol;

public class Player {
    private String playerName;
    private GameSymbol gameSymbol;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GameSymbol getGameSymbol() {
        return gameSymbol;
    }

    public void setGameSymbol(GameSymbol gameSymbol) {
        this.gameSymbol = gameSymbol;
    }
}
