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
            new Tuple<>(Language.PT, "Municao"),
            new Tuple<>(Language.ES, "Municion")
    )),
    ITEM_COIN(MultipleLanguageString.builder(
            new Tuple<>(Language.EN, "Coin"),
            new Tuple<>(Language.PT, "Moeda"),
            new Tuple<>(Language.ES, "moneda")
    ))
    ;

    private final MultipleLanguageString text;
    public final MultipleLanguageString get(){
        return this.text.createNew();
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