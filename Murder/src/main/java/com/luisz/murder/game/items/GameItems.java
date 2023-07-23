package com.luisz.murder.game.items;

import com.luisz.lapi.common.language.Language;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameItems {
    public ItemStack build(GameItem item, int amount, Language language){
        ItemStack i = new ItemStack(item.material, amount);
        ItemMeta meta = i.getItemMeta();
        if(meta == null) return null;
        meta.setDisplayName(item.getName(language));
        i.setItemMeta(meta);
        return i;
    }
}