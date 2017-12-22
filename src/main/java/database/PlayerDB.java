package database;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDB {

    private static Connection conn;

    public PlayerDB(){

    }

    private static Connection getConnection() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:.\\database\\database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            System.out.println(""+conn.isClosed());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    private static void closeConnection()
    {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<Player> getAllPlayers() {
        conn = getConnection();
        Player player;
        List<Player> players = new ArrayList<Player>();
        try {
            PreparedStatement ps =  conn.prepareStatement("SELECT id, name, surname, nickname FROM player");
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String nickname = rs.getString("nickname");

                player = new Player(id, name, surname, nickname);

                players.add(player);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
          closeConnection();
        }
        return players;
    }

    public static Player getPlayerByID(int id){
        conn = getConnection();
        Player player=null;
        try {
            PreparedStatement ps =  conn.prepareStatement("SELECT name, surname, nickname FROM player WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs  = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String nickname = rs.getString("nickname");
                player = new Player(id, name, surname, nickname);

            }
            else
            {
                System.out.println("Spieler nicht gefunden");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return player;
    }

    public static boolean insertPlayer(Player player)
    {
        conn = getConnection();
        try {
            PreparedStatement ps =  conn.prepareStatement("INSERT INTO player (name, surname, nickname)VALUES ( ?, ?, ?)");
            ps.setString(1, player.getName());
            ps.setString(2, player.getSurname());
            ps.setString(3, player.getNickname());
            ps.executeUpdate();
            System.out.println("added Player " + player.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            closeConnection();
        }
        return true;
    }

    /**
     * only for testing purpose
     * @param id
     * @return
     */
    public static boolean deletePlayerByID(int id)
    {
        conn = getConnection();
        try {
            PreparedStatement ps =  conn.prepareStatement("DELETE FROM player WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            closeConnection();
        }
        return true;
    }

    public static int getPlayerIdByNickname(String nickname) {
        conn = getConnection();
        Player player=null;
        try {
            PreparedStatement ps =  conn.prepareStatement("SELECT id, name, surname FROM player WHERE nickname=?");
            ps.setString(1, nickname);
            ResultSet rs  = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                player = new Player(id, name, surname, nickname);
            }
            else
            {
                System.out.println("Spieler nicht gefunden");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return player.getId();
    }
}
