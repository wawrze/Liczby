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
        System.out.print("Podana liczba słownie: ");
        for(int i = 0,j = (liczbaTrojkami.size() - 1);i < liczbaTrojkami.size();i++, j--) {
            Integer[] tymczasowa = liczbaTrojkami.get(i);
            System.out.print(trojkaSlownie(tymczasowa));
            int jednosci;
            if(tymczasowa.length == 1) {
                jednosci = tymczasowa[0];
                if(jednosci == 1)
                    jednosci = -1;
            }
            else if(tymczasowa.length == 2) {
                jednosci = tymczasowa[1];
                if(tymczasowa[0] == 1)
                    jednosci = 5;
                else if(tymczasowa[1] == 0 && tymczasowa[0] > 0)
                    jednosci = 5;
            }
            else {
                jednosci = tymczasowa[2];
                if(tymczasowa[1] == 1)
                    jednosci = 5;
                else if(tymczasowa[2] == 0 && (tymczasowa[1] > 0 || tymczasowa[0] > 0))
                    jednosci = 5;
            }
            System.out.print(licznikiPotegTysiaca(j, jednosci));
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
        String liczba;
        switch(setki) {
            case 1:
                liczba = "sto";
                break;
            case 2:
                liczba = "dwieście";
                break;
            case 3:
                liczba = "trzysta";
                break;
            case 4:
                liczba = "czterysta";
                break;
            case 5:
                liczba = "pięćset";
                break;
            case 6:
                liczba = "sześćset";
                break;
            case 7:
                liczba = "siedemset";
                break;
            case 8:
                liczba = "osiemset";
                break;
            case 9:
                liczba = "dziewięćset";
                break;
            default:
                return dwucyfrowaSlownie(dziesiatki, jednosci);
        }
        liczba += (((dziesiatki != 0 || jednosci != 0) ? " " : "") + dwucyfrowaSlownie(dziesiatki, jednosci));
        return liczba;
    }

    private static String dwucyfrowaSlownie(int dziesiatki, int jednosci) {
        String liczba;
        switch(dziesiatki) {
            case 1:
                return nascieSlownie(jednosci);
            case 2:
                liczba = "dwadzieścia";
                break;
            case 3:
                liczba = "trzydzieści";
                break;
            case 4:
                liczba = "czterdzieści";
                break;
            case 5:
                liczba = "pięćdziesiąt";
                break;
            case 6:
                liczba = "sześćdziesiąt";
                break;
            case 7:
                liczba = "siedemdziesiąt";
                break;
            case 8:
                liczba = "osiemdziesiąt";
                break;
            case 9:
                liczba = "dziewięćdziesiąt";
                break;
            default:
                return jednosciSlownie(jednosci);
        }
        liczba += ((jednosci != 0 ? " " : "") + jednosciSlownie(jednosci));
        return liczba;
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

    private static String licznikiPotegTysiaca(int ktora, int jednosci) {
        if(ktora == 0) {
            return "";
        }
        else if(ktora == 1) {
            if(jednosci == -1)
                return " tysiąc ";
            if(jednosci == 0)
                return "";
            else if(jednosci < 5 && jednosci > 1)
                return " tysiące ";
            else
                return " tysięcy ";
        }
        else {
            String licznik = "";
            switch(ktora / 2) {
                case 1:
                    licznik += " mi";
                    break;
                case 2:
                    licznik += " bi";
                    break;
                case 3:
                    licznik += " try";
                    break;
                case 4:
                    licznik += " kwadry";
                    break;
                case 5:
                    licznik += " kwinty";
                    break;
                case 6:
                    licznik += " seksty";
                    break;
                case 7:
                    licznik += " septy";
                    break;
                case 8:
                    licznik += " okty";
                    break;
                case 9:
                    licznik += " nony";
                    break;
                case 10:
                    licznik += " decy";
                    break;
                case 11:
                    licznik += " undecy";
                    break;
                case 12:
                    licznik += " duodecy";
                    break;
                case 13:
                    licznik += " trycy";
                    break;
                case 14:
                    licznik += " kwadragi";
                    break;
                case 15:
                    licznik += " oktogi";
                    break;
                case 16:
                    licznik += " centy";
                    break;
                default:
                    return " nieznanej potęgi ";
            }
            if((ktora % 2) == 0) {
                if(jednosci == -1)
                    licznik += "lion ";
                else if(jednosci == 0)
                    return " ";
                else if(jednosci < 5 && jednosci > 1)
                    licznik += "liony ";
                else
                    licznik += "lionów ";
            }
            else {
                if(jednosci == -1)
                    licznik += "liard ";
                else if(jednosci == 0)
                    return " ";
                else if(jednosci < 5 && jednosci > 1)
                    licznik += "liardy ";
                else
                    licznik += "liardów ";
            }
            return licznik;
        }
    }

}