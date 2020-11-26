package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    private int edellinen;

    public Nollaa(TextField tuloskentta, TextField syotekentta,
                Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        nollaa.setDisable(false);
        this.undo = undo;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        edellinen = Integer.parseInt(tuloskentta.getText());
        sovellus.nollaa();
        paivita();
        undo.setDisable(false);
    }

    @Override
    public void peru() {
        sovellus.plus(edellinen);
        paivita();
        undo.setDisable(true);
    }

    private void paivita() {
        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        nollaa.setDisable(true);
    }
}
