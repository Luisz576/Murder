package com.luisz.murder.language;

public enum TextsVar {
    FIRST_PLAYER_NAME("%PLAYER1%"),
    SECOND_PLAYER_NAME("%PLAYER2%"),
    FIRST_NUMBER("%NUMBER1%")
    ;
    public final String VAR_NAME;
    TextsVar(String VAR_NAME){
        this.VAR_NAME = VAR_NAME;
    }
}