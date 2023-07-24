package com.luisz.murder.language;

import com.luisz.lapi.common.language.Language;
import com.luisz.lapi.common.text.language.MultipleLanguageString;
import com.luisz.lapi.common.tuple.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Texts {
    // ITEMS
    ITEM_SWORD(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Knife"),
            new Tuple<>(Language.PT, "Adaga"),
            new Tuple<>(Language.ES, "Cuchillo")
    )),
    ITEM_PISTOL(MultipleLanguageString.builder(
            new Tuple<>(Language.EN,"Pistol"),
            new Tuple<>(Language.PT, "Pistola"),
            new Tuple<>(Language.ES, "Pistola")
    )),
    ITEM_AMMO(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Ammo"),
            new Tuple<>(Language.PT, "Munição"),
            new Tuple<>(Language.ES, "Municion")
    )),
    ITEM_COIN(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Coin"),
            new Tuple<>(Language.PT, "Moeda"),
            new Tuple<>(Language.ES, "moneda")
    )),
    // GAME_RUNNING
    GAME_RUNNING_PICKUP_COIN(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "[+1] Coin"),
            new Tuple<>(Language.PT, "[+1] Moeda"),
            new Tuple<>(Language.ES, "[+1] Moneda")
    )),
    // GAME_MESSAGE
    GAME_MESSAGE_PLAYER_JOIN_LIKE_PLAYER(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player %PLAYER% has joined!"),
            new Tuple<>(Language.PT, "Jogador %PLAYER% entrou!"),
            new Tuple<>(Language.ES, "El jugador %PLAYER% se ha unido!")
    )),
    GAME_MESSAGE_PLAYER_JOIN_LIKE_SPECTATOR(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player %PLAYER% is watching!"),
            new Tuple<>(Language.PT, "Jogador %PLAYER% está assistindo!"),
            new Tuple<>(Language.ES, "El jugador %PLAYER% está mirando!")
    )),
    GAME_MESSAGE_PLAYER_QUIT_LIKE_PLAYER(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player %PLAYER% has quited!"),
            new Tuple<>(Language.PT, "Jogador %PLAYER% abandonou a partida!"),
            new Tuple<>(Language.ES, "El jugador %PLAYER% ha abandonado el partido!")
    )),
    GAME_MESSAGE_PLAYER_QUIT_LIKE_SPECTATOR(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player %PLAYER% is no longer watching!"),
            new Tuple<>(Language.PT, "Jogador %PLAYER% não está mais assistindo!"),
            new Tuple<>(Language.ES, "El jugador %PLAYER% ya no está viendo!")
    )),
    ;

    private final MultipleLanguageString text;
    public final MultipleLanguageString getMultipleLanguageString(){
        return this.text.createNew();
    }
    public final String getString(Language language){
        return this.text.getString(language);
    }
    Texts(MultipleLanguageString text){
        this.text = text;
    }

    private static final List<Language> supportedLanguages = new ArrayList<>();
    static {
        supportedLanguages.add(Language.EN);
        supportedLanguages.add(Language.PT);
        supportedLanguages.add(Language.ES);
    }

    public static List<Language> getSupportedLanguages(){
        return Collections.unmodifiableList(supportedLanguages);
    }
}