package modul9tugas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modul9tugas.models.KategoriBuku;

public class KategoriBukuDAO {

    public List<KategoriBuku> getAllKategoriBuku() {
        List<KategoriBuku> kategoriBukuList = new ArrayList<>();
        String query = "SELECT * FROM kategori_buku";

        try (
            Connection koneksi = DBConnection.getConnection();
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                String kodeKategori = rs.getString("kode_kategori");
                String namaKategori = rs.getString("nama_kategori");

                kategoriBukuList.add(new KategoriBuku(kodeKategori, namaKategori));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kategoriBukuList;
    }
}
