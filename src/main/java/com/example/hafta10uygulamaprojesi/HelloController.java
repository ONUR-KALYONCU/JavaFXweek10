package com.example.hafta10uygulamaprojesi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> cmbDepartman;

    @FXML
    private Spinner<Integer> spnSicilNo;

    @FXML
    private TableColumn<Personel, String> sutunAd;

    @FXML
    private TableColumn<Personel, String> sutunDepartman;

    @FXML
    private TableColumn<Personel, Integer> sutunSicilNo;

    @FXML
    private TableColumn<Personel, String> sutunSoyad;

    @FXML
    private TableView<Personel> tblPersonel;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtSoyad;

    private ObservableList<Personel> calisanlar= FXCollections.observableArrayList();

    private Alert hatamesaji=new Alert(Alert.AlertType.ERROR);
    private Alert bilgimesaji=new Alert(Alert.AlertType.INFORMATION);

    @FXML
    void personelGuncelle(ActionEvent event) {

        int index=tblPersonel.getSelectionModel().getSelectedIndex();

        if (index != -1) {

            int sicilno=spnSicilNo.getValue();

            for (int i = 0; i < calisanlar.size(); i++) {

                if (sicilno == calisanlar.get(i).getSicilno() && sicilno!=calisanlar.get(index).getSicilno()) {

                    hatamesaji.setTitle("Hata");
                    hatamesaji.setHeaderText("Sicil numarası zaten kayılıt...");
                    hatamesaji.show();
                    return;
                }
            }
            String ad=txtAd.getText();
            String soyad=txtSoyad.getText();
            String departman=cmbDepartman.getValue();

            Personel personel=new Personel(sicilno,ad,soyad,departman);
            calisanlar.set(index,personel);

        }else {

            hatamesaji.setTitle("Hata");
            hatamesaji.setHeaderText("Güncellenecek Personel Kaydı Yok veya Seçilmedi...");
            hatamesaji.show();
        }
    }

    @FXML
    void personelKayit(ActionEvent event) {

        int sicilno=spnSicilNo.getValue();

        for (int i = 0; i < calisanlar.size(); i++) {
            if (sicilno == calisanlar.get(i).getSicilno()) {
                Alert mesaj=new Alert(Alert.AlertType.ERROR);
                mesaj.setTitle("Hata");
                mesaj.setHeaderText("Personel zaten kayıtlı...");
                mesaj.show();
                return;
            }
        }

        String ad=txtAd.getText();
        String soyad=txtSoyad.getText();
        String departman=cmbDepartman.getValue();

        Personel personel=new Personel(sicilno,ad,soyad,departman);
        calisanlar.add(personel);

    }

    @FXML
    void personelSil(ActionEvent event) {

        int index=tblPersonel.getSelectionModel().getSelectedIndex();

        if (index != -1) {
            calisanlar.remove(index);
            bilgimesaji.setTitle("Silme");
            bilgimesaji.setHeaderText("Silme işlemi başarıyla gerçekleştirildi...");
            bilgimesaji.show();
        }else {

            hatamesaji.setTitle("Hata");
            hatamesaji.setHeaderText("Tabloda Silinecek Kayıt Yok veya Silinecek Kayıt Seçilmedi...");
            hatamesaji.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spnSicilNo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000,1,1));

        cmbDepartman.getItems().add("Pazarlama");
        cmbDepartman.getItems().add("Satış");
        cmbDepartman.getItems().add("Bilgi İşlem");
        cmbDepartman.getItems().add("İnsan Kaynakları");

        sutunAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
        sutunSoyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        sutunDepartman.setCellValueFactory(new PropertyValueFactory<>("depertman"));
        sutunSicilNo.setCellValueFactory(new PropertyValueFactory<>("sicilno"));

        tblPersonel.setItems(calisanlar);

        tblPersonel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {

                int index=newValue.intValue();

                if (index != -1) {

                    Personel secilenpersonel=calisanlar.get(index);
                    spnSicilNo.getValueFactory().setValue(secilenpersonel.getSicilno());
                    txtAd.setText(secilenpersonel.getAd());
                    txtSoyad.setText(secilenpersonel.getSoyad());
                    cmbDepartman.setValue(secilenpersonel.getDepertman());
                }else {
                    /*
                    hatamesaji.setTitle("Hata");
                    hatamesaji.setHeaderText("Tabloda gösterilecek kayıt bulunamadı...");
                    hatamesaji.show();*/
                }
            }
        });
    }
}
