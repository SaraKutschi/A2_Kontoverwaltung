class Kreditkonto extends Konto {
    private final int kreditlimit;

    public Kreditkonto(String kontoinhaber, int startguthaben, int kreditlimit) {
        super(kontoinhaber, startguthaben, "Kreditkonto");
        this.kreditlimit = Math.max(0, kreditlimit);
    }

    @Override
    protected boolean kannAbheben(int betrag) {
        return getKontostand() - betrag >= -kreditlimit;
    }

    @Override
    public void kontoauszug() {
        super.kontoauszug();
        System.out.println("Kreditlimit:  " + kreditlimit + " EUR");
        System.out.println("-------------------------------------------------");
    }
}