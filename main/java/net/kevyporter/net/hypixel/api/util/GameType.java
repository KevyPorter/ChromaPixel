package net.kevyporter.net.hypixel.api.util;

public enum GameType {
    QUAKECRAFT("Quakecraft", 2),
    WALLS("Walls", 3),
    PAINTBALL("Paintball", 4),
    SURVIVAL_GAMES("Blitz Survival Games",5),
    TNTGAMES("TNTGames", 6),
    VAMPIREZ("VampireZ", 7),
    WALLS3("MegaWalls", 13),
    ARCADE("Arcade", 14),
    ARENA("Arena", 17),
    MCGO("Cops and Crims", 21),
    UHC("UHC Champions", 20),
    BATTLEGROUND("Warlords", 23);

    private static GameType[] v = values();
    private final String name;
    private final int id;

    private GameType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static GameType fromId(int id) {
        for(GameType gameType : v) {
            if(gameType.id==id) {
                return gameType;
            }
        }
        return null;
    }

    public static GameType fromDatabase(String name) {
        if(name.equals("Quake")) {
            return QUAKECRAFT;
        } else if(name.equals("Walls")) {
            return WALLS;
        } else if(name.equals("Paintball")) {
            return PAINTBALL;
        } else if(name.equals("HungerGames")) {
            return SURVIVAL_GAMES;
        } else if(name.equals("TNTGames")) {
            return TNTGAMES;
        } else if(name.equals("VampireZ")) {
            return VAMPIREZ;
        } else if(name.equals("Walls3")) {
            return WALLS3;
        } else if(name.equals("Arcade")) {
            return ARCADE;
        } else if(name.equals("Arena")) {
            return ARENA;
        } else if(name.equals("MCGO")) {
            return MCGO;
        } else if(name.equals("UHC")) {
            return UHC;
        } else if(name.equals("Battleground")) {
            return BATTLEGROUND;
        }
        return null;
    }
}