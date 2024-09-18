package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class Konto {
    private final String inhaber;
    private final int kontonummer;
    private BigDecimal kontostand;
    private Integer pinCode;

    private static int anzahlAllerKonten = 1;

    private Konto(String inhaber, int kontonummer, BigDecimal kontostand, Integer pinCode) {
        this.inhaber = inhaber;
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
        this.pinCode = pinCode;
    }

    public Konto(String inhaber, BigDecimal kontostand) {
        this(inhaber, anzahlAllerKonten++, kontostand, null);
    }

    public Konto(String inhaber) {
        this(inhaber, new BigDecimal(0));
    }


    private void change(BigDecimal change) {
        System.out.printf("Vor der Transaktion: %.2f\n", this.kontostand);
        this.kontostand = this.kontostand.add(change);
        System.out.printf("Nach der Transaktion: %.2f\n", this.kontostand);
    }

    public void einzahlen(BigDecimal change) {
        this.change(change);
    }

    public void abheben(BigDecimal change) {
        this.change(change.negate());
    }

    public void einzahlenKarte(BigDecimal change) {
        if(this.checkPin()) {
            this.einzahlen(change);
        }
    }

    public void abhebenKarte(BigDecimal change) {
        if(this.checkPin()) {
            this.abheben(change);
        }
    }

    public void setPin() {
        if(this.pinCode != null) {
            return;
        }

        String s = null;
        Scanner scanner = new Scanner(System.in);

        while (s == null || s.length() != 4 || Utils.getInteger(s) == null) {
            System.out.print("Neuer Pin: ");
            s = scanner.nextLine();
        }

        this.pinCode = Utils.getInteger(s);
    }

    public boolean checkPin() {
        if(this.pinCode == null) {
            throw new RuntimeException("Pin code is null");
        }

        String s = null;
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        while (count < 3) {

            s = null;

            while (s == null || s.length() != 4 || Utils.getInteger(s) == null) {
                System.out.print("PIN: ");
                s = scanner.nextLine();
            }

            if(this.pinCode.equals(Utils.getInteger(s))) {
                return true;
            }
            System.out.println("ungültiger Pin code");
            count++;
        }


        return false;
    }

    public String printKontoData() {
        return String.format("Kontonummer: %d\nInhaber: %s\nKontostand: %.2f", this.kontonummer, this.inhaber, this.kontostand);
    }
}
