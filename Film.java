package uas;

public class Film {

    private int id;
    private String judul;
    private String deskripsi;
    private int TahunLaunching;
    private String Genre;
    private String Rating;

    public Film(int id, String judul, String deskripsi, int TahunLaunching, String Genre, String Rating) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.TahunLaunching = TahunLaunching;
        this.Genre = Genre;
        this.Rating = Rating;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getTahunLaunching() {
        return TahunLaunching;
    }

    public void setTahunLaunching(int TahunLaunching) {
        this.TahunLaunching = TahunLaunching;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getRating() {
        return Rating;
    }

    public void setrating(String Rating) {
        this.Rating = Rating;
    }
}
