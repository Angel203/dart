package model;

import lombok.Getter;

import java.util.List;

public class Game {
    private @Getter int id=-1;
    private @Getter int set_id = -1;
    private @Getter String date="";
    private @Getter List<Player> players=null;

    public Game(int id, int set_id, String date, List<Player> players)
    {
        this.id = id;
        this.date = date;
        this.set_id = set_id;
        this.players = players;
    }

}
