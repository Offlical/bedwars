package dev.offlical.bedwars.teams;

import lombok.Getter;

@Getter
public enum TeamType {

    /**
     * if we ever do multiple gamemodes, this would change depending on the mode
     */

    SOLO(1,8),DOUBLE(2,8),TRIOS(3,4),FOURS(4,4);

    private int playersPerTeam;
    private int maxTeams;

    TeamType(int playersPerTeam,int maxTeams) {
        this.playersPerTeam = playersPerTeam;
        this.maxTeams = maxTeams;
    }


}
