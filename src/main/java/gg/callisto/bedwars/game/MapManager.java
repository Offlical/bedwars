package gg.callisto.bedwars.game;

import gg.callisto.bedwars.generators.Generator;
import gg.callisto.bedwars.generators.GeneratorList;
import gg.callisto.bedwars.teams.TeamType;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.stream.Location;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

@Getter
@Setter
public class MapManager  {

    /**
     *  A class containing all the map data we need to start a game;
     */

    private HashSet<GeneratorList> generatorLists;
    /*
    a GeneratorList is a class of a couple of gens together, so they're all timed up, this where would diamond gens, emerald gens and base gen go to.
     */
    private HashSet<Location> spawnLocations;
    // self explanatory
    private HashSet<Location> bedLocations;
    // self explanatory
    private TeamType[] supportedModes;
    // self explanatory

    /**
     *
     * @param path - Location of the file
     * @return - a MapManager to get all the map info from
     * @throws IOException - If the file doesn't exist or other file errors
     * @throws ParseException - If something failed to parse successfully
     *
     *  Gets a new MapManager object from reading a JSON file preferably in the plugin's folder
     *
     *  ** if it's better to change it to just a constructor with a path, just change it to a constructor;
     *
     */
    public static MapManager loadMapfromJSON(String path) throws IOException, ParseException {

        // Stuff that needs to be set
        HashSet<Location> spawnLocs;
        HashSet<Location> bedLocs;
        HashSet<GeneratorList> generatorLists;
        TeamType[] modes;

        MapManager mngr = new MapManager();

        // Getting the file
        File file = new File(path);

        // if the file doesn't exist, throw an error
        if(!file.exists())
            throw new IOException("File doesn't exist!");

        // gets json object;
        Object obj = new JSONParser().parse(new FileReader(path));

        JSONObject jsonObject = (JSONObject) obj;

        // not sure if this is how you do it
        JSONArray modesJSON = (JSONArray) jsonObject.get("supportedGamemodes");

        modes = new TeamType[modesJSON.size()];

        // is this how json arrays work?
        // since if im not wrong they seem like {"test","test2","test3"} even you can use arrays for more complicated stuff?
        int i = 0;
        for(Object o : modesJSON.toArray()) {
            if(o instanceof String) {
                String mode = (String) o;
                modes[i] = TeamType.valueOf(mode);
                i++;
            }
        }
        mngr.setSupportedModes(modes);

        // someone else try and figure out how to do the generator/locations im not experienced enough for that, will try to learn later on


        return mngr;
    }

}
