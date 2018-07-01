package Liczby;

import java.util.ArrayList;
import java.util.List;

public class Liczby {

    private String liczba;
    private List<Integer> cyfry;
    private List<Integer[]> liczbaTrojkami;
    private String liczbaSlownie;

    public Liczby(String liczba) throws Exception {
        this.liczba = liczba;
        stringNaCyfry();
        podzielNaTrojki();
        liczbaNaSlowa();
    }

    public String liczbaSlownie() {
        return liczbaSlownie;
    }

    private void stringNaCyfry() throws Exception {
        cyfry = new ArrayList<>();
        for(int i = 0;i < liczba.length();i++) {
            char c = liczba.charAt(i);
            if(!Character.isDigit(c))
                throw new Exception();
            cyfry.add(Character.getNumericValue(c));
        }
    }

    private void podzielNaTrojki() {
        liczbaTrojkami = new ArrayList<>();
        Integer[] trojka = null;
        for(int i = (cyfry.size() - 1), j = 1;i >= 0;i--, j++) {
            if((j % 3) == 1) {
                if (j == (cyfry.size())) {
                    trojka = new Integer[1];
                }
                else if((j + 1) == (cyfry.size())) {
                    trojka = new Integer[2];
                }
                else {
                    trojka = new Integer[3];
                }
            }
            if(trojka.length == 3) {
                if((j % 3) == 1) {
                    trojka[2] = cyfry.get(i);
                }
                else if((j % 3) == 2) {
                    trojka[1] = cyfry.get(i);
                }
                else {
                    trojka[0] = cyfry.get(i);
                }
            }
            else if(trojka.length == 2) {
                if((j % 3) == 1) {
                    trojka[1] = cyfry.get(i);
                }
                else {
                    trojka[0] = cyfry.get(i);
                }
            }
            else {
                trojka[0] = cyfry.get(i);
            }
            if((j % 3) == 0 || j == cyfry.size()) {
                liczbaTrojkami.add(trojka);
            }
        }
        List<Integer[]> tymczasowy = new ArrayList(liczbaTrojkami);
        liczbaTrojkami = new ArrayList<>();
        for(int i = (tymczasowy.size() - 1);i >= 0;i--) {
            liczbaTrojkami.add(tymczasowy.get(i));
        }
    }

    private void liczbaNaSlowa() {
        liczbaSlownie = "";
        for(int i = 0,j = (liczbaTrojkami.size() - 1);i < liczbaTrojkami.size();i++, j--) {
            Integer[] tymczasowa = liczbaTrojkami.get(i);
            liczbaSlownie += trojkaSlownie(tymczasowa);
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
            liczbaSlownie += licznikiPotegTysiaca(j, jednosci);
        }
    }

    private String trojkaSlownie(Integer[] trojka) {
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

    private String trzyCyfrowaSlownie(int setki, int dziesiatki, int jednosci) {
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

    private String dwucyfrowaSlownie(int dziesiatki, int jednosci) {
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

    private String nascieSlownie(int cyfra) {
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

    private String jednosciSlownie(int cyfra) {
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

    private String licznikiPotegTysiaca(int ktora, int jednosci) {
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