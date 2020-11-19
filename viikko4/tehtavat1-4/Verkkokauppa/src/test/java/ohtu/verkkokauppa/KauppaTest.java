package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    String nimi;
    String tilinumero;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k = new Kauppa(varasto, pankki, viite);

        nimi ="pekka";
        tilinumero = "12345";

        k.aloitaAsiointi();
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu(nimi, tilinumero);

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodinTilisiirtoKutsussaOikeatArvot() {
        k.lisaaKoriin(1);
        k.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinumero),
                                anyString(), eq(5));
    }

    @Test
    public void kahdenEriTuotteenOstosKutsuuPankinMetodiaTilisiirtoOikein() {
        k.lisaaKoriin(1);

        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jäätelö", 3));
        k.lisaaKoriin(2);

        k.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinumero),
                                anyString(), eq(8));
    }

    @Test
    public void kahdenSamanTuotteenOstosKutsuuPankinMetodiaTilisiirtoOikein() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinumero),
                                anyString(), eq(10));
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinJosKaikkiaEiVarastossa() {
        k.lisaaKoriin(1);

        when(varasto.saldo(3)).thenReturn(0);
        k.lisaaKoriin(3);

        k.tilimaksu(nimi, tilinumero);

        verify(pankki).tilisiirto(eq(nimi), anyInt(), eq(tilinumero),
                                anyString(), eq(5));
    }

}
