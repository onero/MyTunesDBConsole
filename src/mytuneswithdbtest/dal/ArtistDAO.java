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
import mytuneswithdbtest.be.Artist;

public class ArtistDAO {

    private static ArtistDAO instance;

    private final ConnectionManager cm;

    public static ArtistDAO getInstance() {
        if (instance == null) {
            try {
                instance = new ArtistDAO();
            } catch (IOException ex) {
                System.out.println("Couldn't create new ArtistDAO");
                Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    private ArtistDAO() throws IOException {
        cm = ConnectionManager.getInstance();
    }

    /**
     * Add parsed artist to DB
     *
     * @param artist
     */
    public void addArtistToDB(Artist artist) {
        String sql = "INSERT INTO Artist(Name) VALUES(?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, c.getID());
            ps.setString(1, artist.getName());

            ps.executeUpdate();
            System.out.println();
            System.out.println("Artist added to database!");
//            ResultSet generatedKey = ps.getGeneratedKeys();
//            generatedKey.next();
//            int id = generatedKey.getInt(1);
//            return new Song(id, c);
        } catch (SQLException sqlException) {
            System.out.println();
            System.out.println("Couldn't add artist to DB");
            System.out.println(sqlException);
        }
    }

    /**
     * Update song in DB
     *
     * @param artist
     */
    public void updateArtist(Artist artist) {
        String sql = "UPDATE Artist "
                + "SET Name = ? "
                + "WHERE ID = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, artist.getID());
            ps.setString(2, artist.getName());

            ps.executeUpdate();
            System.out.println();
            System.out.println("Updated artist!");
        } catch (SQLException e) {
            System.out.println("Couldn't update artist");
            System.out.println(e);
        }
    }

    /**
     * Delete artist from DB
     *
     * @param id
     */
    public void deleteArtist(int id) {
        String sql = "DELETE FROM Artist where ID = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println();
            System.out.println("Song deleted!");
        } catch (SQLException e) {
            System.out.println("Couldn't delete artist");
            System.out.println(e);
        }
    }

    /**
     * Get a list of all artists from the DB
     *
     * @return
     * @throws SQLException
     */
    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artists = new ArrayList<>();

        String sql = "SELECT * FROM Artist";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                artists.add(getOneArtist(rs));
            }
            return artists;
        }
    }

    /**
     * Retrieve a single artist from the DB
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Artist getOneArtist(ResultSet rs) throws SQLException {
        int ID = rs.getInt("ID");
        String name = rs.getString("Name");

        Artist newArtist = new Artist(ID, name);

        return newArtist;
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
