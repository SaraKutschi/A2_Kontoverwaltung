class Sparkonto extends Konto {
    public Sparkonto(String kontoinhaber, int startguthaben) {
        super(kontoinhaber, startguthaben, "Sparkonto");
    }

    @Override
    protected boolean kannAbheben(int betrag) {
        return getKontostand() >= betrag;
    }
}