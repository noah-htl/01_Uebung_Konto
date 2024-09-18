package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Konto konto = new Konto("Noah");
        konto.einzahlen(new BigDecimal(100));

        konto.setPin();

        konto.abhebenKarte(new BigDecimal(10));

        konto.einzahlenKarte(new BigDecimal(10));

        System.out.println(konto.printKontoData());
    }
}