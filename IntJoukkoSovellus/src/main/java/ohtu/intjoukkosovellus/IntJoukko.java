package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;
    private int kasvatuskoko;   // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;       // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;   // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alkiot = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
        alkioidenLkm = 0;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            alkiot[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTaulukkoa();

            return true;
        }

        return false;
    }

    public void lisaaMonta(int[] luvut) {
        for (int i = 0; i < luvut.length; i++) {
            lisaa(luvut[i]);
        }
    }

    private void kasvataTaulukkoa() {
        if (alkioidenLkm % alkiot.length == 0) {
            alkiot = Arrays.copyOf(alkiot, alkioidenLkm + kasvatuskoko);
        }
    }

    public boolean kuuluu(int luku) {
        return etsiLuvunSijainti(luku) != -1;
    }

    private int etsiLuvunSijainti(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {

                return i;
            }
        }
        // luku ei joukossa
        return -1;
    }

    public boolean poista(int luku) {
        int luvunIndeksi = etsiLuvunSijainti(luku);

        if (luvunIndeksi != -1) {
            alkiot[luvunIndeksi] = 0;
            pakkaaTaulukkoLopustaIndeksiin(luvunIndeksi);
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void pakkaaTaulukkoLopustaIndeksiin(int alku) {
        for (int i = alku; i < alkioidenLkm - 1; i++) {
            int apu = alkiot[i];
            alkiot[i] = alkiot[i + 1];
            alkiot[i + 1] = apu;
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (alkioidenLkm > 0) {
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                sb.append(alkiot[i]).append(", ");
            }
            sb.append(alkiot[alkioidenLkm - 1]);
        }
        sb.append("}");

        return sb.toString();
    }

    public int[] toIntArray() {
        return Arrays.copyOf(alkiot, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        yhdiste.lisaaMonta(aTaulu);
        yhdiste.lisaaMonta(bTaulu);

        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                leikkaus.lisaa(aTaulu[i]);
            }
        }

        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        erotus.lisaaMonta(aTaulu);

        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(i);
        }

        return erotus;
    }

}
