
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden määrä on nolla.

    public IntJoukko() {
        this.lukujono = new int[OLETUSKAPASITEETTI];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        this.lukujono = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Negatiivinen kapasiteetti");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Negatiivinen kasvatuskoko");
        }
        this.lukujono = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }

        lukujono[alkioidenLkm] = luku;
        alkioidenLkm++;

        if (alkioidenLkm % lukujono.length == 0) {
            int[] vanhaTaulukko = lukujono;
            lukujono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, lukujono);
        }

        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public boolean kuuluu(int luku) {
        int indeksi = etsiIndeksi(luku);
        return indeksi >= 0;
    }

    private int etsiIndeksi(int luku) {
        int indeksi = -1;

        for (int i = 0; i < alkioidenLkm; i++) {
            if (lukujono[i] == luku) {
                indeksi = i;
                break;
            }
        }

        return indeksi;
    }

    public boolean poista(int luku) {
        int indeksi = etsiIndeksi(luku);
        if (indeksi == -1) {
            return false;
        }

        for (int i = indeksi; i < alkioidenLkm - 1; i++) {
            int apu = lukujono[i];
            lukujono[i] = lukujono[i + 1];
            lukujono[i + 1] = apu;
        }

        alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String mjono = Arrays.toString(this.toIntArray());
            mjono = mjono.replace("[", "{");
            mjono = mjono.replace("]", "}");
            return mjono;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }

        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }

        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }

        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }

        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }
 
        return erotus;
    }

}
