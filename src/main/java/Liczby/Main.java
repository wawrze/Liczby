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
        System.out.println("\nTrojki slownie: ");
        liczbaTrojkami.stream()
                .forEach(l -> System.out.println(trojkaSlownie(l)));
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

    private static String trojkaSlownie(Integer[] trojka) {
        if(trojka.length == 1) {
            return jednosciSlownie(trojka[0]);
        }
        else if(trojka.length == 2) {
            return dwucyfrowaSlownie(trojka[0], trojka[1]);
        }
        else {
            return trzyCyfrowaSlownie(trojka[0], trojka[1], trojka[2]);
        }
    }

    private static String trzyCyfrowaSlownie(int setki, int dziesiatki, int jednosci) {
        switch(setki) {
            case 1:
                return "sto " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 2:
                return "dwieście " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 3:
                return "trzysta " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 4:
                return "czterysta " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 5:
                return "pięćset " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 6:
                return "sześćset " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 7:
                return "siedemset " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 8:
                return "osiemset " + dwucyfrowaSlownie(dziesiatki, jednosci);
            case 9:
                return "dziewięćset " + dwucyfrowaSlownie(dziesiatki, jednosci);
            default:
                return dwucyfrowaSlownie(dziesiatki, jednosci);
        }
    }

    private static String dwucyfrowaSlownie(int dziesiatki, int jednosci) {
        switch(dziesiatki) {
            case 1:
                return nascieSlownie(jednosci);
            case 2:
                return "dwadzieścia " + jednosciSlownie(jednosci);
            case 3:
                return "trzydzieści " + jednosciSlownie(jednosci);
            case 4:
                return "czterdzieści " + jednosciSlownie(jednosci);
            case 5:
                return "pięćdziesiąt " + jednosciSlownie(jednosci);
            case 6:
                return "sześćdziesiąt " + jednosciSlownie(jednosci);
            case 7:
                return "siedemdziesiąt " + jednosciSlownie(jednosci);
            case 8:
                return "osiemdziesiąt " + jednosciSlownie(jednosci);
            case 9:
                return "dziewięćdziesiąt " + jednosciSlownie(jednosci);
            default:
                return jednosciSlownie(jednosci);
        }
    }

    private static String nascieSlownie(int cyfra) {
        switch(cyfra) {
            case 1:
                return "jedenaście";
            case 2:
                return "dwanaście";
            case 3:
                return "trzynaście";
            case 4:
                return "czternaście";
            case 5:
                return "pietnaście";
            case 6:
                return "szesnaście";
            case 7:
                return "siedemnaście";
            case 8:
                return "osiemnaście";
            case 9:
                return "dziewiętnaście";
            default:
                return "dziesięć";
        }
    }

    private static String jednosciSlownie(int cyfra) {
        switch(cyfra) {
            case 1:
                return "jeden";
            case 2:
                return "dwa";
            case 3:
                return "trzy";
            case 4:
                return "cztery";
            case 5:
                return "pięc";
            case 6:
                return "sześć";
            case 7:
                return "siedem";
            case 8:
                return "osiem";
            case 9:
                return "dziewięć";
            default:
                return "";
        }
    }

}