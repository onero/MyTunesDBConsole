/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytuneswithdbtest.dal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytuneswithdbtest.be.Song;

public class SongDAO {

    private static SongDAO instance;

    private final ConnectionManager cm;

    public static SongDAO getInstance() {
        if (instance == null) {
            try {
                instance = new SongDAO();
            } catch (IOException ex) {
                System.out.println("Couldn't create new DB manager");
                Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    private SongDAO() throws IOException {
        cm = ConnectionManager.getInstance();
    }

    /**
     * Add parsed song to DB
     *
     * @param song
     */
    public void addSongToDB(Song song) {
        String sql = "INSERT INTO Song(Title, ArtistID, CategoryID, FileName, Duration) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, c.getID());
            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getArtistID());
            ps.setInt(3, song.getCategoryID());
            ps.setString(4, song.getFileName());
            ps.setInt(5, song.getDuration());

            ps.executeUpdate();
            System.out.println();
            System.out.println("Song added to database!");
//            ResultSet generatedKey = ps.getGeneratedKeys();
//            generatedKey.next();
//            int id = generatedKey.getInt(1);
//            return new Song(id, c);
        } catch (SQLException sqlException) {
            System.out.println();
            System.out.println("Couldn't add song to DB");
            System.out.println(sqlException);
        }
    }

    /**
     * Update song in DB
     *
     * @param song
     */
    public void updateSong(Song song) {
        String sql = "UPDATE Song "
                + "SET Title = ?, "
                + "    ArtistID = ?, "
                + "    CategoryID = ?, "
                + "    FileName = ?, "
                + "    Duration = ? "
                + "WHERE ID = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getArtistID());
            ps.setInt(3, song.getCategoryID());
            ps.setString(4, song.getFileName());
            ps.setInt(5, song.getDuration());
            ps.setInt(6, song.getID());

            ps.executeUpdate();
            System.out.println();
            System.out.println("Updated song!");
        } catch (SQLException e) {
            System.out.println("Coudn't update song");
            System.out.println(e);
        }
    }

    public void deleteSong(int id) {
        String sql = "DELETE FROM Song where ID = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println();
            System.out.println("Song deleted!");
        } catch (SQLException e) {
            System.out.println("Couldn't delete song");
            System.out.println(e);
        }
    }

    /**
     * Get a list of all songs from the DB
     *
     * @return
     * @throws SQLException
     */
    public List<Song> getAllSongs() throws SQLException {
        List<Song> songs = new ArrayList<>();

        String sql = "SELECT * FROM Song";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                songs.add(getOneSong(rs));
            }
            return songs;
        }
    }

    /**
     * Retrieve a single song from the DB
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Song getOneSong(ResultSet rs) throws SQLException {
        int ID = rs.getInt("ID");
        String title = rs.getString("Title");
        int artistID = rs.getInt("ArtistID");
        int categortyID = rs.getInt("CategoryID");
        String fileName = rs.getString("FileName");
        int duration = rs.getInt("Duration");

        Song newSong = new Song(ID, title, artistID, categortyID, fileName, duration);

        return newSong;
    }

    //
//    public Department getById(int id) throws SQLException {
//        String sql = "SELECT * FROM Department WHERE DNumber = ?";
//        try (Connection con = cm.getConnection()) {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return getOneCompany(rs);
//            } else {
//                return null;
//            }
//        }
//    }
//
}
