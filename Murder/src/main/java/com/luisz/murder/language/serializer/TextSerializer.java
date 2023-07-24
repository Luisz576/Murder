package com.luisz.murder.language.serializer;

import com.luisz.lapi.common.language.Language;
import com.luisz.lapi.common.tuple.Tuple;
import com.luisz.murder.language.Texts;
import com.luisz.murder.language.TextsVar;

import java.util.List;

public class TextSerializer {
    public static String a(Texts texts, Language language, List<Tuple<TextsVar, String>> values){
        String message = texts.getString(language);
        for(Tuple<TextsVar, String> v : values){
            message = message.replace(v.a.VAR_NAME, v.b);
        }
        return message;
    }
}