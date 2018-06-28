package Liczby;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String liczba = wczytajLiczbe();
        List<Integer> cyfry = new ArrayList<>();
        try {
            cyfry = stringNaCyfry(liczba);
        }
        catch (Exception e) {
            System.out.println("To nie jest liczba!\nKończę działanie.");
        }
        List<Integer[]> liczbaTrojkami = podzielNaTrojki(cyfry);
        System.out.print("Liczba po podziale na trojki: ");
        for(int i = 0;i < liczbaTrojkami.size();i++) {
            System.out.print(liczbaTrojkami.get(i)[0]);
            if(liczbaTrojkami.get(i).length >= 2)
                System.out.print(liczbaTrojkami.get(i)[1]);
            if(liczbaTrojkami.get(i).length == 3)
                System.out.print(liczbaTrojkami.get(i)[2]);
            System.out.print(" ");
        }
    }

    private static List<Integer> stringNaCyfry(String string) throws Exception {
        List<Integer> cyfry = new ArrayList<>();
        for(int i = 0;i < string.length();i++) {
            char c = string.charAt(i);
            if(!Character.isDigit(c))
                throw new Exception();
            cyfry.add(Character.getNumericValue(c));
        }
        return cyfry;
    }

    private static String wczytajLiczbe() {
        Scanner sc = new Scanner(System.in);
        String liczba;
        do {
            System.out.print("Podaj liczbę: ");
            liczba = sc.nextLine();
        } while(liczba.isEmpty());
        return liczba;
    }

    private static List<Integer[]> podzielNaTrojki(List<Integer> liczba) {
        List<Integer[]> nowaLiczba = new ArrayList<>();
        Integer[] trojka = null;
        for(int i = (liczba.size() - 1), j = 1;i >= 0;i--, j++) {
            if((j % 3) == 1) {
                if (j == (liczba.size())) {
                    trojka = new Integer[1];
                }
                else if((j + 1) == (liczba.size())) {
                    trojka = new Integer[2];
                }
                else {
                    trojka = new Integer[3];
                }
            }
            if(trojka.length == 3) {
                if((j % 3) == 1) {
                    trojka[2] = liczba.get(i);
                }
                else if((j % 3) == 2) {
                    trojka[1] = liczba.get(i);
                }
                else {
                    trojka[0] = liczba.get(i);
                }
            }
            else if(trojka.length == 2) {
                if((j % 3) == 1) {
                    trojka[1] = liczba.get(i);
                }
                else {
                    trojka[0] = liczba.get(i);
                }
            }
            else {
                trojka[0] = liczba.get(i);
            }
            if((j % 3) == 0 || j == liczba.size()) {
                nowaLiczba.add(trojka);
            }
        }
        List<Integer[]> tymczasowy = new ArrayList(nowaLiczba);
        nowaLiczba = new ArrayList<>();
        for(int i = (tymczasowy.size() - 1);i >= 0;i--) {
            nowaLiczba.add(tymczasowy.get(i));
        }
        return nowaLiczba;
    }

}