/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od2;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author k4le0
 */
public class TestFermata {
    private Scanner sc = new Scanner(System.in);
    int liczbaWylosowana;
    int iloscProb;
    int gornaGranica;
    
    public static int losowanie (int max){
        Random rand = new Random();
        //int value = rand.nextInt(50);
        int min = 0;
        //int output = rand.nextInt((max - min )+1)+min;
        int wynik = rand.nextInt((max)/2-1) *2+1;
        return wynik;   
    }
    
    public static int losujIsprawdz (int max, int k ){
            boolean sprawdz = false;
            int wynik = 1;
            while (sprawdz != true) {
                wynik = losowanie(max);
                sprawdz = samTest(wynik, k);
            }
        return wynik;   
    }
    
        public static void drukujSpacje(){
            
        for(int i=0;i<10;i++)
        System.out.println("");
    }
        
    public static void drukujPauze(){
        System.out.println("===================================");
    }
    
    public void drukujWartosciDefaultowe(){
        //this.liczbaWylosowana=liczbaWylosowana;
        drukujPauze();
        System.out.println("Liczba wylosowana: "+liczbaWylosowana);
        System.out.println("Ilość prób w teście Fermata: "+iloscProb);
        System.out.println("Górna granica losowanej liczby: "+gornaGranica);
        drukujPauze();
    }
    
    public void interfejs(){
        //this.liczbaWylosowana = liczbaWylosowana;
        String wejscieStr;
        int wejscieInt;
        drukujWartosciDefaultowe();    
        System.out.println("Wybierz opcje: \n\n 1. Zmiana danych \n 2. Losowanie \n 3. Wykonanie Testu Fermata \n 4. Koniec"); 
        System.out.print(":");
        int opcja = sc.nextInt();
        switch(opcja){
            case 1:
                drukujPauze();
                System.out.println("Opcja 1: Podmieniamy dane. ");
                drukujPauze();
                System.out.println("Podaj ilość prób w teście Fermata:");
                System.out.print("  liczba:");
                iloscProb = sc.nextInt();
                System.out.println("\nPodaj górną granice lowowanej liczby" );
                System.out.print("  liczba:");
                gornaGranica = sc.nextInt();
                interfejs();
            case 2:
                drukujPauze();
                System.out.println("Opcja 2: Ponowne losowanie liczby.");
                drukujPauze();
                liczbaWylosowana = losowanie(gornaGranica);
                interfejs();
            case 3:
                drukujPauze();
                System.out.println("Opcja 3: Test Fermata.");
                drukujPauze();
                if(samTest(liczbaWylosowana, iloscProb)){
                    System.out.println("Liczba "+liczbaWylosowana+" jest prawdopodobnie pierwsza.");
                } else {
                    System.out.println("Liczba "+liczbaWylosowana+" jest złożona.");
                };
                interfejs();
            case 4:
                drukujPauze();
                System.out.println("Opcja numbero quatro. Do widzenia !");
                drukujPauze();
                break;
            case 5:
                int test = losujIsprawdz(gornaGranica,iloscProb);
                drukujPauze();
                System.out.println("Ukryta opcja 5: Test funkcj:.");
                drukujPauze();
                System.out.println("Test funkcji losujIsprawdz: " + test );
                interfejs();
            default:
                System.out.println("Spróbuj jeszcze raz.");
            interfejs();
        }
    }
    
    private static boolean samTest(int n, int k){
                int a, i;
                //n - liczba do sprawdzenia 
                //k - dokladność 
                Random rand = new Random();
                if (n<4){
                    return true;
                }
                for (i=0; i<k; i++){
                    a = rand.nextInt(n-2) + 2;
                    if(algorytmSzybkiegoPotegowania(a, n-1, n) != 1)
                    {
                        return false;
                    }
                }
                return true;
           }
        
    private static int algorytmSzybkiegoPotegowania(int a, int b, int m)
    {
       int i;
       int wynik = 1;
       long x = a%m;

       for (i=1; i<=b; i<<=1)
       {
          x %= m;
          if ((b&i) != 0)
          {
             wynik *= x;
             wynik %= m;
          }
          x *= x;
       }

       return wynik%m;
    }
    
    public static void main(String args[]){
        TestFermata program = new TestFermata();
        program.gornaGranica = 1000;
        program.iloscProb = 5;
        program.liczbaWylosowana = losowanie(program.gornaGranica);
        program.interfejs();
        
    }
}
