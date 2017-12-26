package database;

import model.Game;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.PlayerDB.getPlayerByID;

public class GameDB {

    private static Connection conn;

    public GameDB() {

    }

    private static Connection getConnection() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:.\\database\\database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            System.out.println("" + conn.isClosed());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    private static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<Game> getAllGamesBySetid(int setid) {
        conn = getConnection();
        Game game;
        List<Game> games = new ArrayList<Game>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id, set_id, gamedate, p1_id, p2_id, p3_id, p4_id, p5_id, p6_id FROM games WHERE set_id = ?");
            ps.setInt(setid, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int set_Id = rs.getInt("set_id");
                String gamedate = rs.getString("gamedate");

                List<Integer> playerIDs = new ArrayList<Integer>();
                playerIDs.add(rs.getInt("p1_id"));
                playerIDs.add(rs.getInt("p2_id"));
                playerIDs.add(rs.getInt("p3_id"));
                playerIDs.add(rs.getInt("p4_id"));
                playerIDs.add(rs.getInt("p5_id"));
                playerIDs.add(rs.getInt("p6_id"));

                List<Player> players = new ArrayList<>();

                for (int pid : playerIDs) {
                    if (pid != -1) {
                        players.add(getPlayerByID(pid));
                    }
                }

                game = new Game(id, set_Id, gamedate, players);

                games.add(game);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return games;
    }

    public static Game getGameByID(int id) {
        conn = getConnection();
        Game game = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id, set_id, gamedate, p1_id, p2_id, p3_id, p4_id, p5_id, p6_id  FROM games WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
                int set_Id = rs.getInt("set_id");
                String gamedate = rs.getString("gamedate");

                List<Integer> playerIDs = new ArrayList<Integer>();
                playerIDs.add(rs.getInt("p1_id"));
                playerIDs.add(rs.getInt("p2_id"));
                playerIDs.add(rs.getInt("p3_id"));
                playerIDs.add(rs.getInt("p4_id"));
                playerIDs.add(rs.getInt("p5_id"));
                playerIDs.add(rs.getInt("p6_id"));

                List<Player> players = new ArrayList<>();

                for (int pid : playerIDs) {
                    if (pid != -1) {
                        players.add(getPlayerByID(pid));
                    }
                }

                return new Game(id, set_Id, gamedate, players);
            } else {
                System.out.println("Spiel nicht gefunden");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return game;
    }

    public static boolean insertGame(Game game) {
        conn = getConnection();
        try {
            int p1 = -1;
            int p2 = -1;
            int p3 = -1;
            int p4 = -1;
            int p5 = -1;
            int p6 = -1;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO games (gamedate, p1_id, p2_id, p3_id, p4_id, P5_id, P6_id, set_id)VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, game.getDate());
            List<Player> players = game.getPlayers();
            playerlistToPlayerID(players, p1, p2, p3, p4, p5 ,p6);
            ps.setInt(8, game.getSet_id());
            ps.executeUpdate();
            System.out.println("added Player " + game.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    private static void playerlistToPlayerID(List<Player> players, int p1, int p2, int p3, int p4, int p5, int p6) {
        int size = players.size();
        switch (size)
        {
            case 6: p6 = players.get(5).getId();
            case 5: p5 = players.get(4).getId();
            case 4: p4 = players.get(3).getId();
            case 3: p3 = players.get(2).getId();
            case 2: p2 = players.get(1).getId();
            case 1: p1 = players.get(0).getId();
            default:
                break;
        }
    }
}
