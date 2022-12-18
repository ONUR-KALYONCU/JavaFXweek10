module com.example.hafta10uygulamaprojesi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hafta10uygulamaprojesi to javafx.fxml;
    exports com.example.hafta10uygulamaprojesi;
}