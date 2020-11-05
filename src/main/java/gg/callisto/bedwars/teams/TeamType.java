package gg.callisto.bedwars.teams;

import lombok.Getter;

@Getter
public enum TeamType {

    /**
     * if we ever do multiple gamemodes, this would change depending on the mode
     */

    SOLO(1,8,4),DOUBLE(2,8,8),TRIOS(3,4,6),FOURS(4,4,8);

    private int playersPerTeam;
    private int maxTeams;
    private int minPlayers;

    TeamType(int playersPerTeam,int maxTeams,int minPlayers) {
        this.playersPerTeam = playersPerTeam;
        this.maxTeams = maxTeams;
        this.minPlayers = minPlayers;
    }


}
