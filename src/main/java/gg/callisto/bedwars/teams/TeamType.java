package gg.callisto.bedwars.teams;

import lombok.Getter;

@Getter
public enum TeamType {

    /**
     * if we ever do multiple gamemodes, this would change depending on the mode
     */

    SOLO(1,8,"SOLO"),DOUBLE(2,8,"DOUBLES")
    ,TRIOS(3,4,"THRIOS")
    ,FOURS(4,4,"FOURS");

    private int playersPerTeam;
    private int maxTeams;
    private String name;

    TeamType(int playersPerTeam,int maxTeams,String name) {
        this.playersPerTeam = playersPerTeam;
        this.maxTeams = maxTeams;
        this.name = name;
    }

}
