/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package modul9tugas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modul9tugas.dao.KategoriBukuDAO;
import modul9tugas.dao.BukuDAO;
import modul9tugas.models.Buku;
import modul9tugas.models.KategoriBuku;



/**
 *
 * @author Probook
 */
public class MainController implements Initializable {

//    FILTER
    @FXML private ComboBox<KategoriBuku> cbxKategori;     // Untuk memilih kategori
    @FXML private DatePicker dpcDari;                     // Untuk filter tanggal mulai
    @FXML private DatePicker dpcSampai;                   // Untuk filter tanggal akhir
    @FXML private TextField txtJudul;                     // Untuk filter berdasarkan judul
    @FXML private Button btnFilter;                       // Untuk memproses filter

//    SORTING
    @FXML private ComboBox<String> cbxSorting;
    @FXML private Button btnSorting;
    
    // Komponen TableView dan kolom-kolomnya untuk menampilkan data buku
    @FXML private TableView<Buku> tblViewBuku;
    @FXML private TableColumn<Buku, String> colJudul;
    @FXML private TableColumn<Buku, String> colKategori;
    @FXML private TableColumn<Buku, String> colKodeBuku;
    @FXML private TableColumn<Buku, String> colPengarang;
    @FXML private TableColumn<Buku, String> colPenerbit;
    @FXML private TableColumn<Buku, String> colTahun;
    @FXML private TableColumn<Buku, String> colEdisi;
    @FXML private TableColumn<Buku, String> colPengadaan;
    
//    RESET
    @FXML private Button btnReset;


    private ObservableList<Buku> bukuList;

    /**
     * Method untuk memuat data kategori ke dalam ComboBox
     */
    private void loadDataKategori() {
        // Membuat instance DAO untuk kategori buku
        KategoriBukuDAO kategoriBukuDAO = new KategoriBukuDAO();

        // Mengambil semua data kategori dari database
        // dan mengonversinya ke ObservableList
        ObservableList<KategoriBuku> kategoriBukuList = FXCollections.observableArrayList(
            kategoriBukuDAO.getAllKategoriBuku()
        );

        // Mengisi ComboBox dengan data kategori
        cbxKategori.setItems(kategoriBukuList);
    }

    /**
     * Method untuk mendapatkan kode kategori yang dipilih di ComboBox
     * 
     * @return String kode kategori, atau null jika tidak ada yang dipilih
     */
    public String getSelectedKodeKategori() {
        // Mengambil item yang dipilih dari ComboBox
        KategoriBuku selectedKategori = cbxKategori.getSelectionModel().getSelectedItem();

        // Mengembalikan kode kategori jika ada yang dipilih
        if (selectedKategori != null) {
            return selectedKategori.getKodeKategori();
        }

        return null;
    }

    private void loadDataBuku() {
    // Mengambil semua data buku dari database melalui dao
    // dan mengkonversinya ke ObservableList
    bukuList = FXCollections.observableArrayList(BukuDAO.getAllBuku());

    // Mengisi TableView dengan data buku
    tblViewBuku.setItems(bukuList);
    }
    
    private void initComboboxSorting() {
        cbxSorting.setItems(
            FXCollections.observableArrayList(
                "",
                "Judul A-Z",
                "Judul Z-A",
                "Pengadaan Terbaru",
                "Pengadaan Lama"
            )
        );
        
        cbxSorting.getSelectionModel().selectFirst();
    }
    
    public String getSelectedComboboxSorting() {
        String sortingOption = cbxSorting.getSelectionModel().getSelectedItem();
        return sortingOption;
    }
    
    @FXML
    private void handleButtonSorting(ActionEvent event){
        bukuList.clear();
        BukuDAO.sortingOption = getSelectedComboboxSorting();
        loadDataBuku();
    }

    @FXML
    private void handleBtnFilter(ActionEvent event) {
        // Mengosongkan data buku sebelum memuat yang baru
        bukuList.clear();

        // Mengatur filter judul dari input TextField
        BukuDAO.filterJudul = txtJudul.getText();

        // Mengatur filter tanggal jika ada input
        LocalDate dari = dpcDari.getValue();
        LocalDate sampai = dpcSampai.getValue();

        if (dari != null || sampai != null) {
            BukuDAO.filterTanggalDari = dari.toString();
            BukuDAO.filterTanggalSampai = sampai.toString();
        }

        // Mengatur filter kategori jika ada yang dipilih
        if (getSelectedKodeKategori() != null) {
            BukuDAO.filterKategori = getSelectedKodeKategori();
        }

        // Memuat ulang data buku dengan filter yang telah ditetapkan
        loadDataBuku();
    }
    
    @FXML
    private void handleBtnReset(ActionEvent event) {
    // Reset semua filter input
    cbxKategori.getSelectionModel().clearSelection();
    dpcDari.setValue(null);
    dpcSampai.setValue(null);
    txtJudul.clear();

    // Reset sorting
    cbxSorting.getSelectionModel().selectFirst();

    // Hapus filter di DAO
    BukuDAO.filterJudul = null;
    BukuDAO.filterTanggalDari = null;
    BukuDAO.filterTanggalSampai = null;
    BukuDAO.filterKategori = null;
    BukuDAO.sortingOption = "";

    // Muat ulang data buku tanpa filter/sorting
    bukuList.clear();
    loadDataBuku();
}

    private void initTableViewBuku() {
    // Menghubungkan setiap kolom TableView dengan properti di model Buku
    colKodeBuku.setCellValueFactory(
        new PropertyValueFactory<>("kodebuku")
    );

    colKategori.setCellValueFactory(
        new PropertyValueFactory<>("namaKategori")
    );

    colJudul.setCellValueFactory(
        new PropertyValueFactory<>("judul")
    );

    colPengarang.setCellValueFactory(
        new PropertyValueFactory<>("pengarang")
    );

    colPenerbit.setCellValueFactory(
        new PropertyValueFactory<>("penerbit")
    );
    
    colTahun.setCellValueFactory(
        new PropertyValueFactory<>("tahun")
    );

    colEdisi.setCellValueFactory(
        new PropertyValueFactory<>("edisi")
    );

    colPengadaan.setCellValueFactory(
        new PropertyValueFactory<>("tglpengadaan")
    );
    }

@Override
    public void initialize(URL url, ResourceBundle rb) {
        // Memuat data kategori ke ComboBox
        loadDataKategori();

        // Menginisialisasi TableView
        initTableViewBuku();

        // Memuat data buku ke TableView
        loadDataBuku();
        
        initComboboxSorting();

        // Menginisialisasi TableView
        initTableViewBuku();
    }
    
}
