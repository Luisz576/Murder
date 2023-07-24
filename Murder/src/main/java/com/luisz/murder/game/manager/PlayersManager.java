package com.luisz.murder.game.manager;

import com.luisz.lapi.common.tuple.Tuple;
import com.luisz.luisz576api.api.Luisz576Api;
import com.luisz.luisz576api.domain.playerprofile.PlayerProfile;
import com.luisz.murder.exceptions.ErrorLoadingPlayerProfileException;
import com.luisz.murder.game.Game;
import com.luisz.murder.game.enums.GameState;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.language.Texts;
import com.luisz.murder.language.TextsVar;
import com.luisz.murder.language.serializer.TextSerializer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PlayersManager {
    private final Game game;
    private final HashMap<Player, Profile> profiles = new HashMap<>();

    public PlayersManager(Game game){
        this.game = game;
    }

    public Profile joinPlayer(Player player) throws ErrorLoadingPlayerProfileException{
        try{
            PlayerProfile playerProfile = Luisz576Api.getApi().getProfile(player.getUniqueId());
            if(playerProfile == null){
                throw new Exception("PlayerProfile not found");
            }
            Profile profile = new Profile(player, playerProfile, (
                this.profiles.size() < game.arena.MAX_PLAYERS || game.getGameState() != GameState.RECRUITING
            ));
            this.profiles.put(player, profile);
            return profile;
        }catch (Exception e){
            e.printStackTrace();
            throw new ErrorLoadingPlayerProfileException();
        }
    }
    public boolean contains(Player player){
        return this.profiles.containsKey(player);
    }
    public Profile getProfile(Player player){
        return this.profiles.get(player);
    }
    public Profile quitPlayer(Player player){
        return this.profiles.remove(player);
    }

    protected Collection<Profile> getAllProfiles(){
        return this.profiles.values();
    }
    public void sendMessageForEveryone(Texts texts, List<Tuple<TextsVar, String>> vars, String pre){
        Collection<Profile> ps = profiles.values();
        for(Profile p : ps){
            p.sendMessage(pre + TextSerializer.a(
                texts,
                p.getLanguage(),
                vars
            ));
        }
    }
}