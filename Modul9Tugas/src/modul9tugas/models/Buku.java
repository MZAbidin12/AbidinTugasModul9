/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul9tugas.models;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Buku {
    private String kodebuku;
    private String kodeKategori;
    private String judul;
    private String pengarang;
    private String penerbit;
    private int tahun;
    private int edisi;
    private String tglpengadaan;
    private String namaKategori;

    public Buku(String kodebuku, String kodekategori, String judul, 
            String pengarang, String penerbit, int tahun, int edisi, 
            String tglpengadaan, String namaKategori) {
        this.kodebuku = kodebuku;
        this.kodeKategori = kodekategori;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.edisi = edisi;
        this.tglpengadaan = tglpengadaan;
        this.namaKategori = namaKategori;
        
    }

    public String getKodebuku() {
        return kodebuku;
    }

    public void setKodebuku(String kodebuku) {
        this.kodebuku = kodebuku;
    }
    
     public String getKodeKategori() {
        return kodeKategori;
    }

    public void setKodeKategori(String kodekategori) {
        this.kodeKategori = kodekategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    
    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    
    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
    
    public int getEdisi() {
        return edisi;
    }

    public void setEdisi(int edisi) {
        this.edisi = edisi;
    }
    
    public String getTglpengadaan(){
        return tglpengadaan;
    }
    
    public void setTglpengadaan(String tglpengadaan){
        this.tglpengadaan = tglpengadaan;
    }
    
     public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
    
}
