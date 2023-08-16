package com.luisz.murder.game.scorerender;

import com.luisz.lapi.common.tuple.Tuple;
import com.luisz.murder.game.data.ScoreboardData;
import com.luisz.murder.game.profile.Profile;
import com.luisz.murder.language.Texts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MurderScoreRender implements ScoreRender{
    public final String SCOREBOARD_TITLE = ChatColor.YELLOW + "<<" + ChatColor.RED + "Murder" + ChatColor.YELLOW + ">>";
    private final Map<Profile, Tuple<Scoreboard, Objective>> scoreboards = new HashMap<>();

    private Tuple<Scoreboard, Objective> getScoreboardOrRegisterNew(Profile profile){
        if(profile == null){
            return null;
        }
        Tuple<Scoreboard, Objective> values = this.scoreboards.get(profile);
        if(values == null){
            values = buildScoreboard(profile);
            this.scoreboards.put(profile, values);
        }
        return values;
    }

    private Tuple<Scoreboard, Objective> buildScoreboard(Profile profile){
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("LMurderScoreboard", "", SCOREBOARD_TITLE);
        objective.setDisplayName(SCOREBOARD_TITLE);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        buildScoreboardTeamsAndScores(scoreboard, objective, profile);

        return new Tuple<>(scoreboard, objective);
    }
    private final String TEAM_ROLE_KEY = "teamrole1",
            TEAM_ROLE_NAME = "teamrole";
    private void buildScoreboardTeamsAndScores(Scoreboard scoreboard, Objective objective, Profile profile){
        // ROLE
        Team teamRole = scoreboard.registerNewTeam(TEAM_ROLE_NAME);
        teamRole.addEntry(TEAM_ROLE_KEY);
        teamRole.setPrefix(ChatColor.YELLOW + Texts.SCOREBOARD_ROLE_PREFIX.getString(profile.getLanguage()) + ": ");
        teamRole.setSuffix(ChatColor.RED + "?");
        objective.getScore(TEAM_ROLE_KEY).setScore(0);

        // TODO
    }

    private void updateScoreboard(Tuple<Scoreboard, Objective> s, ScoreboardData data, Profile profile){
        // ROLE
        String playerRole = "?";
        switch (profile.type){
            case VICTIM:
                playerRole = Texts.VICTIM.getString(profile.getLanguage());
                break;
            case DETECTIVE:
                playerRole = Texts.DETECTIVE.getString(profile.getLanguage());
                break;
            case ASSASSIN:
                playerRole = Texts.ASSASSIN.getString(profile.getLanguage());
                break;
            case SPECTATOR:
                playerRole = Texts.SPECTATOR.getString(profile.getLanguage());
                break;
        }
        Objects.requireNonNull(s.a.getTeam(TEAM_ROLE_NAME)).setSuffix(playerRole);

        // TODO
    }

    @Override
    public void render(ScoreboardData data, Profile profile) {
        Tuple<Scoreboard, Objective> scoreboard = getScoreboardOrRegisterNew(profile);

        updateScoreboard(scoreboard, data, profile);

        profile.player.setScoreboard(scoreboard.a);
    }
}