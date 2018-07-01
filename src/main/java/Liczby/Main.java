package Liczby;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String wczytanaLiczba;
        do {
            System.out.print("Podaj liczbę: ");
            wczytanaLiczba = sc.nextLine();
        } while(wczytanaLiczba.isEmpty());
        Liczby liczba = null;
        try {
            liczba = new Liczby(wczytanaLiczba);
        }
        catch (Exception e) {
            System.out.println("To nie jest liczba!\nKończę działanie.");
        }
        System.out.print("Podana liczba słownie: " + liczba.liczbaSlownie());
    }

}