class Girokonto extends Konto {
    private final int limit; // Dispo in EUR

    public Girokonto(String kontoinhaber, int startguthaben, int limit) {
        super(kontoinhaber, startguthaben, "Girokonto");
        this.limit = Math.max(0, limit);
    }

    @Override
    protected boolean kannAbheben(int betrag) {
        return getKontostand() + limit >= betrag;
    }

    @Override
    public void kontoauszug() {
        super.kontoauszug();
        System.out.println("Dispo-Limit:  " + limit + " EUR");
        System.out.println("-------------------------------------------------");
    }
}
