package uas;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    private final FilmController service = new FilmController();
    private final ObservableList<Film> data = FXCollections.observableArrayList();
    private final TableView<Film> table = new TableView<>();
    private final TextField tfJudul = new TextField();
    private final TextArea taDeskripsi = new TextArea();
    private final TextField tfTahun = new TextField();
    private final ComboBox<String> cbGenre = new ComboBox<>();
    private final ComboBox<String> cbRating = new ComboBox<>();
    private Film selectedFilm = null;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("🎬 Aplikasi Manajemen Data Film");

        Label labelTitle = new Label("🎬 Manajemen Data Film");
        labelTitle.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
        labelTitle.setPadding(new Insets(20, 0, 10, 0));
        labelTitle.setAlignment(Pos.CENTER);

        // Kolom Tabel
        TableColumn<Film, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Film, String> colJudul = new TableColumn<>("Judul");
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));

        TableColumn<Film, String> colDeskripsi = new TableColumn<>("Deskripsi");
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        TableColumn<Film, Integer> colTahun = new TableColumn<>("Tahun");
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahunLaunching"));

        TableColumn<Film, String> colGenre = new TableColumn<>("Genre");
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Film, String> colRating = new TableColumn<>("Rating");
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        table.getColumns().addAll(colId, colJudul, colDeskripsi, colTahun, colGenre, colRating);
        table.setItems(data);
        table.setPrefHeight(300);

        // Form Input
        tfJudul.setPromptText("Judul Film");
        tfTahun.setPromptText("Tahun Rilis");
        taDeskripsi.setPromptText("Deskripsi Film");
        taDeskripsi.setPrefRowCount(3);

        cbGenre.getItems().addAll("Action", "Drama", "Comedy", "Horror", "Sci-Fi");
        cbGenre.setPromptText("Genre");

        cbRating.getItems().addAll("G", "PG", "PG-13", "R", "NC-17");
        cbRating.setPromptText("Rating");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        formGrid.add(new Label("Judul:"), 0, 0);
        formGrid.add(tfJudul, 1, 0);
        formGrid.add(new Label("Tahun:"), 0, 1);
        formGrid.add(tfTahun, 1, 1);
        formGrid.add(new Label("Deskripsi:"), 0, 2);
        formGrid.add(taDeskripsi, 1, 2);
        formGrid.add(new Label("Genre:"), 0, 3);
        formGrid.add(cbGenre, 1, 3);
        formGrid.add(new Label("Rating:"), 0, 4);
        formGrid.add(cbRating, 1, 4);

        Button btnTambah = new Button("Tambah");
        Button btnUpdate = new Button("Update");
        Button btnHapus = new Button("Hapus");

        HBox buttonBox = new HBox(10, btnTambah, btnUpdate, btnHapus);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        VBox formBox = new VBox(10, formGrid, buttonBox);
        formBox.setPadding(new Insets(10));

        TitledPane formPane = new TitledPane("➕ Tambah / Edit Film", formBox);
        formPane.setCollapsible(false);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedFilm = newVal;
            if (newVal != null) {
                tfJudul.setText(newVal.getJudul());
                taDeskripsi.setText(newVal.getDeskripsi());
                tfTahun.setText(String.valueOf(newVal.getTahunLaunching()));
                cbGenre.setValue(newVal.getGenre());
                cbRating.setValue(newVal.getRating());
            }
        });

        btnTambah.setOnAction(e -> tambahFilm());
        btnUpdate.setOnAction(e -> updateFilm());
        btnHapus.setOnAction(e -> hapusFilm());

        VBox root = new VBox(20, labelTitle, table, formPane);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f2f2f2;");

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();

        refreshTable();
    }

    private void tambahFilm() {
        try {
            String judul = tfJudul.getText().trim();
            String deskripsi = taDeskripsi.getText().trim();
            int tahun = Integer.parseInt(tfTahun.getText().trim());
            String genre = cbGenre.getValue();
            String rating = cbRating.getValue();

            if (judul.isEmpty() || deskripsi.isEmpty() || genre == null || rating == null) {
                showError("Semua field wajib diisi & dipilih.");
                return;
            }

            service.tambahTask(judul, deskripsi, tahun, genre, rating);
            refreshTable();
            clearForm();
        } catch (NumberFormatException e) {
            showError("Tahun harus berupa angka.");
        }
    }

    private void updateFilm() {
        if (selectedFilm != null) {
            try {
                String judul = tfJudul.getText().trim();
                String deskripsi = taDeskripsi.getText().trim();
                int tahun = Integer.parseInt(tfTahun.getText().trim());
                String genre = cbGenre.getValue();
                String rating = cbRating.getValue();

                if (judul.isEmpty() || deskripsi.isEmpty() || genre == null || rating == null) {
                    showError("Semua field wajib diisi & dipilih.");
                    return;
                }

                service.updateTask(selectedFilm.getId(), judul, deskripsi, tahun, genre, rating);
                refreshTable();
                clearForm();
            } catch (NumberFormatException e) {
                showError("Tahun harus berupa angka.");
            }
        }
    }

    private void hapusFilm() {
        if (selectedFilm != null) {
            service.hapusTask(selectedFilm.getId());
            refreshTable();
            clearForm();
        }
    }

    private void refreshTable() {
        data.setAll(service.getAll());
    }

    private void clearForm() {
        tfJudul.clear();
        taDeskripsi.clear();
        tfTahun.clear();
        cbGenre.setValue(null);
        cbRating.setValue(null);
        table.getSelectionModel().clearSelection();
        selectedFilm = null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
