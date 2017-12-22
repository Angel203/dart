package model;

import lombok.Getter;

import java.util.List;

public class Game {
    private @Getter int id=-1;
    private @Getter String date="";
    private @Getter List<Player> players=null;

    public Game(int id, String date, List<Player> players)
    {
        this.id = id;
        this.date = date;
        this.players = players;
    }

}
