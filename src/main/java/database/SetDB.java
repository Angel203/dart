package database;

import model.Player;
import model.Set;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SetDB {

    private static Connection conn;

    public SetDB(){

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

    public static List<Set> getAllSets() {
        conn = getConnection();
        Set set = null;
        List<Set> sets = new ArrayList<Set>();
        try {
            PreparedStatement ps =  conn.prepareStatement("SELECT id, wins_needed FROM sets");
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int wins_needed = rs.getInt("wins_needed");

                set = new Set(id, wins_needed);

                sets.add(set);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
          closeConnection();
        }
        return sets;
    }


    public static boolean insertSet(Set set)
    {
        conn = getConnection();
        try {
            PreparedStatement ps =  conn.prepareStatement("INSERT INTO sets (wins_needed)VALUES (?)");
            ps.setInt(1, set.getWins_needed());
            ps.executeUpdate();
            System.out.println("added Player " + set.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            closeConnection();
        }
        return true;
    }
}
