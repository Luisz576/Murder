package com.luisz.murder.game.items;

import com.luisz.lapi.common.language.Language;
import com.luisz.lapi.common.text.language.MultipleLanguageString;
import com.luisz.murder.language.Texts;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum GameItem {
    SWORD(Material.IRON_SWORD, ChatColor.DARK_GRAY, Texts.ITEM_SWORD.get()),
    PISTOL(Material.BOW, ChatColor.DARK_PURPLE, Texts.ITEM_PISTOL.get()),
    AMMO(Material.ARROW, ChatColor.YELLOW, Texts.ITEM_AMMO.get()),
    COIN(Material.EMERALD, ChatColor.GREEN, Texts.ITEM_COIN.get());

    public final Material material;
    public final ChatColor colorName;
    public final MultipleLanguageString name;
    public String getName(Language language){
        return colorName + name.getString(language);
    }
    GameItem(Material material, ChatColor colorName, MultipleLanguageString name){
        this.material = material;
        this.colorName = colorName;
        this.name = name;
    }
}