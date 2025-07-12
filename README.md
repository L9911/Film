Aplikasi Manajemen Data Film (UAS - JavaFX)
Proyek ini adalah aplikasi CRUD sederhana berbasis JavaFX untuk mengelola data film. Aplikasi ini dibangun sebagai tugas akhir (UAS) pemrograman berorientasi objek.


Fitur Aplikasi
Tambah data film

Edit data film

Hapus data film

Tampilkan semua film dalam tabel

Desain antarmuka menggunakan JavaFX dengan tampilan modern

Struktur Data Film
Setiap entri film memiliki:

Properti	Tipe Data	Keterangan
ID	int	ID unik film (auto increment)
Judul	String	Judul film
Deskripsi	String	Ringkasan atau sinopsis
Tahun Rilis	int	Tahun launching film
Genre	String	Action, Drama, dll
Rating	String	G, PG, PG-13, R, NC-17

Tampilan Antarmuka
Tabel Film	Form Input
Menampilkan semua data film	Tambah, Edit, dan Hapus data dengan mudah
Kolom: ID, Judul, Deskripsi, dll.	Form di bawah tabel, dalam TitledPane

Cara Menjalankan
1. Clone Repository
bash
Salin
Edit
git clone https://github.com/username/uas-film-javafx.git
cd uas-film-javafx
2. Buka di IDE
Gunakan IntelliJ IDEA / NetBeans / VS Code dengan Java Support

Pastikan Java 11+ dan JavaFX sudah di-setup

3. Jalankan App.java
Struktur Folder
bash
Salin
Edit
uas-film-javafx/
├── src/
│   └── uas/
│       ├── App.java             # Main GUI
│       ├── Film.java            # Model data
│       └── FilmController.java  # Logika bisnis
└── README.md
Dibuat Oleh
Nama: Gabriel L. Renleeuw

Kelas/NIM: 2B/24083000055

Tugas: UAS Pemrograman Berorientasi Objek

Nilai Plus
Menggunakan pendekatan Object-Oriented sepenuhnya (Model-Controller)  
UI bersih dan modern  
Validasi input  
Modular dan bisa dikembangkan lebih lanjut  
