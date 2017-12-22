package database;

import controller.DatabaseController;
import model.Player;

import java.util.List;

public class testDatabase {

    public static void main(String[] args) {
        PlayerDB db = new PlayerDB();

        List<Player> players  = DatabaseController.getAllPlayers();
        System.out.println(players.size());


        Player player = new Player("Test", "Stefan", "Engel222");
        DatabaseController.insertPlayer(player);
        players  = DatabaseController.getAllPlayers();
        System.out.println(players.size());

        DatabaseController.deletePlayerByID(DatabaseController.getPlayerIdByNickname(player.getNickname()));
        players  = DatabaseController.getAllPlayers();
        System.out.println(players.size());
    }
}
