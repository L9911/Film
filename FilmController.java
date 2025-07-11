package uas;

import java.util.ArrayList;
import java.util.List;

public class FilmController {
    private List<Film> daftarFilm = new ArrayList<>();
    private int nextId = 1;

    public void tambahTask(String judul, String deskripsi, int TahunLaunching, String Genre, String Rating) {
        Film task = new Film(nextId++, judul, deskripsi, TahunLaunching, Genre, Rating);
        daftarFilm.add(task);
    }

    public List<Film> getAll() {
        return daftarFilm;
    }

    public Film cariById(int id) {
        return daftarFilm.stream()
                         .filter(t -> t.getId() == id)
                         .findFirst()
                         .orElse(null);
    }

    public boolean updateTask(int id, String judulBaru, String deskripsiBaru, int TahunLaunching, String Genre, String Rating) {
        Film task = cariById(id);
        if (task != null) {
            task.setJudul(judulBaru);
            task.setDeskripsi(deskripsiBaru);
            task.setTahunLaunching(TahunLaunching);
            task.setGenre(Genre);
            task.setrating(Rating);
            return true;
        }
        return false;
    }

    public boolean hapusTask(int id) {
        Film task = cariById(id);
        return task != null && daftarFilm.remove(task);
    }
}
