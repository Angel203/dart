package controller;

import database.PlayerDB;
import model.Player;

import java.util.List;

public final class DatabaseController {
    PlayerDB playerDB = new PlayerDB();
    public DatabaseController(){

    }

    public static List<Player> getAllPlayers(){
        return PlayerDB.getAllPlayers();
    }

    public static boolean insertPlayer(Player player){
        //TODO implement checks
        return PlayerDB.insertPlayer(player);
    }

    public static int getPlayerIdByNickname(String nickname) {
        return PlayerDB.getPlayerIdByNickname(nickname);
    }

    public static boolean deletePlayerByID(int playerID) {
        return PlayerDB.deletePlayerByID(playerID);
    }
}
