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
    GAME_RUNNING_PLAYER_DIE(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, TextsVar.FIRST_PLAYER_NAME.VAR_NAME + " was killed by " + TextsVar.SECOND_PLAYER_NAME + "!"),
            new Tuple<>(Language.PT, TextsVar.FIRST_PLAYER_NAME.VAR_NAME + " foi morto por " + TextsVar.SECOND_PLAYER_NAME + "!"),
            new Tuple<>(Language.ES, TextsVar.FIRST_PLAYER_NAME.VAR_NAME + " fue asesinada por " + TextsVar.SECOND_PLAYER_NAME + "!")
    )),
    // GAME_MESSAGE
    GAME_MESSAGE_PLAYER_JOIN_LIKE_PLAYER(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player " + TextsVar.FIRST_PLAYER_NAME + " has joined!"),
            new Tuple<>(Language.PT, "Jogador " + TextsVar.FIRST_PLAYER_NAME + " entrou!"),
            new Tuple<>(Language.ES, "El jugador " + TextsVar.FIRST_PLAYER_NAME + " se ha unido!")
    )),
    GAME_MESSAGE_PLAYER_JOIN_LIKE_SPECTATOR(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player " + TextsVar.FIRST_PLAYER_NAME + " is watching!"),
            new Tuple<>(Language.PT, "Jogador " + TextsVar.FIRST_PLAYER_NAME + " está assistindo!"),
            new Tuple<>(Language.ES, "El jugador " + TextsVar.FIRST_PLAYER_NAME + " está mirando!")
    )),
    GAME_MESSAGE_PLAYER_QUIT_LIKE_PLAYER(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player " + TextsVar.FIRST_PLAYER_NAME + " has quited!"),
            new Tuple<>(Language.PT, "Jogador " + TextsVar.FIRST_PLAYER_NAME + " abandonou a partida!"),
            new Tuple<>(Language.ES, "El jugador " + TextsVar.FIRST_PLAYER_NAME + " ha abandonado el partido!")
    )),
    GAME_MESSAGE_PLAYER_QUIT_LIKE_SPECTATOR(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Player " + TextsVar.FIRST_PLAYER_NAME + " is no longer watching!"),
            new Tuple<>(Language.PT, "Jogador " + TextsVar.FIRST_PLAYER_NAME + " não está mais assistindo!"),
            new Tuple<>(Language.ES, "El jugador " + TextsVar.FIRST_PLAYER_NAME + " ya no está viendo!")
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