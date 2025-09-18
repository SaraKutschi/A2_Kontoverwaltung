import java.util.*;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final List<Konto> konten = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenü");
            System.out.println("1 - Neues Konto anlegen");
            System.out.println("2 - Einzahlen");
            System.out.println("3 - Abheben");
            System.out.println("4 - Kontoauszug");
            System.out.println("5 - Alle Konten anzeigen");
            System.out.println("0 - Programm beenden");
            System.out.print("Auswahl: ");

            int auswahl = input.nextInt();
            input.nextLine();

            switch (auswahl) {
                case 1 -> kontoAnlegen();
                case 2 -> kontoAuswählen().ifPresent(k -> {
                    System.out.print("Betrag einzahlen: ");
                    int betrag = input.nextInt();
                    input.nextLine();
                    k.einzahlen(betrag);
                });
                case 3 -> kontoAuswählen().ifPresent(k -> {
                    System.out.print("Betrag abheben: ");
                    int betrag = input.nextInt();
                    input.nextLine();
                    k.abheben(betrag);
                });
                case 4 -> kontoAuswählen().ifPresent(Konto::kontoauszug);
                case 5 -> alleKontenAnzeigen();
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Ungültig!");
            }
        }
    }

    private static void kontoAnlegen() {
        System.out.println("Kontoart wählen: 1) Giro  2) Spar  3) Kredit");
        int art = input.nextInt(); input.nextLine();
        System.out.print("Kontoinhaber: ");
        String inhaber = input.nextLine();
        System.out.print("Startguthaben: ");
        int guthaben = input.nextInt(); input.nextLine();

        switch (art) {
            case 1 -> {
                System.out.print("Dispo-Limit: ");
                int limit = input.nextInt(); input.nextLine();
                konten.add(new Girokonto(inhaber, guthaben, limit));
            }
            case 2 -> konten.add(new Sparkonto(inhaber, guthaben));
            case 3 -> {
                System.out.print("Kreditlimit: ");
                int kl = input.nextInt(); input.nextLine();
                konten.add(new Kreditkonto(inhaber, guthaben, kl));
            }
            default -> System.out.println("Ungültige Auswahl!");
        }
    }

    private static Optional<Konto> kontoAuswählen() {
        if (konten.isEmpty()) {
            System.out.println("Keine Konten vorhanden!");
            return Optional.empty();
        }
        alleKontenAnzeigen();
        System.out.print("Bitte Kontonummer eingeben (Index): ");
        int idx = input.nextInt(); input.nextLine();
        if (idx < 0 || idx >= konten.size()) {
            System.out.println("Ungültige Auswahl!");
            return Optional.empty();
        }
        return Optional.of(konten.get(idx));
    }

    private static void alleKontenAnzeigen() {
        for (int i = 0; i < konten.size(); i++) {
            Konto k = konten.get(i);
            System.out.println(i + ": " + k.getKontoart() + " von " + k.getKontoinhaber());
        }
    }
}
