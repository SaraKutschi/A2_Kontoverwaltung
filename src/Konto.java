abstract class Konto {
    private static int NEXT_KONTO_NR = 1;

    private String kontoinhaber;
    private final int kontonummer;
    private final int bankleitzahl;
    private int kontostand;           // in EUR (vereinfachend als int)
    private final String kontoart;

    protected Konto(String kontoinhaber, int startguthaben, String kontoart) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = Math.max(0, startguthaben);
        this.kontoart = kontoart;
        this.kontonummer = NEXT_KONTO_NR++;
        this.bankleitzahl = 12000;    // Dummy-BLZ für Demo
    }

    public void einzahlen(int einzahlung) {
        if (einzahlung <= 0) {
            System.out.println("Betrag muss > 0 sein.");
            return;
        }
        kontostand += einzahlung;
        System.out.println(einzahlung + " EUR eingezahlt. Neuer Kontostand: " + kontostand + " EUR");
    }

    public void abheben(int auszahlung) {
        if (auszahlung <= 0) {
            System.out.println("Betrag muss > 0 sein.");
            return;
        }
        if (kannAbheben(auszahlung)) {
            kontostand -= auszahlung;
            System.out.println(auszahlung + " EUR abgehoben. Neuer Kontostand: " + kontostand + " EUR");
        } else {
            System.out.println("Abhebung nicht möglich (Deckung/Limit).");
        }
    }

    protected abstract boolean kannAbheben(int betrag);

    public void kontoauszug() {
        System.out.println("-------------------Kontoauszug-------------------");
        System.out.println("Kontoart:     " + kontoart);
        System.out.println("Kontoinhaber: " + kontoinhaber);
        System.out.println("Kontonummer:  " + kontonummer);
        System.out.println("Bankleitzahl: " + bankleitzahl);
        System.out.println("-------------------------------------------------");
        System.out.println("Kontostand:   " + kontostand + " EUR");
        System.out.println("-------------------------------------------------");
    }

    // Getter/Setter (minimales Set)
    public int getKontostand() { return kontostand; }
    protected void setKontostand(int v) { kontostand = v; }
    public String getKontoart() { return kontoart; }

    public String getKontoinhaber() {
        return kontoinhaber;
    }
}