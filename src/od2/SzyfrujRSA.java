/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od2;

import static od2.TestFermata.drukujPauze;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author k4le0
 */
public class SzyfrujRSA {
   
    
    private void wczytajTekstJawny(){
        
    }
    
    private void wczytajTekstZaszyfrowany(){
        
    }
    
    private void wczytajKlucz(){
        
    }
    
   

public void CLS() {
    try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
			e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

public final static void clearConsole()
{
    try
    {
        final String os = System.getProperty("os.name");

        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
    }
    catch (final Exception e)
    {
        //  Handle any exceptions.
    }
}
    
    public void interfejs(){
        Scanner sc = new Scanner(System.in);
        //CLS();
        
        
        boolean Przelacznik = true;
        
        while(Przelacznik){
            drukujPauze();
            System.out.println("Wybierz opcje: \n\n 1. Wczytaj klucze \n 2. Wczytaj tekst jawny   \n 3. Rozszyfruj szyfrogram \n 4.   \n 5. Koniec"); 
            System.out.print(":");
            int opcja = sc.nextInt();
            
            switch(opcja){
            case 0:
                System.out.println("Ukryta opcja 0: Sprwadzam dla wartości domyślnych ");
                clearConsole();
                break;
            case 1:
                
                drukujPauze();
                System.out.println("Opcja 1: Szyfrowanie tekstu jawnego i zapisanie go do pliku ");
                drukujPauze();
                clearConsole();
                break;
            case 2:
                drukujPauze();
                System.out.println("Opcja 2: ");
                drukujPauze();
                clearConsole();
                break;
            case 3:
                drukujPauze();
                System.out.println("Opcja 3:  ");
                drukujPauze();
                clearConsole();
                break;
            case 4:
                drukujPauze();
                System.out.println("Opcja 4:  ");
                drukujPauze();
                clearConsole();
                break;
            case 5:
                drukujPauze();
                System.out.println("Do widzenia !");
                drukujPauze();
                Przelacznik = false;
                clearConsole();
                return;
            default:
                System.out.println("Nierozpoznano opcji. Spróbuj jeszcze raz.");
                break;
        }
                   
                    
        }
    }
    
    public static void main(String[] args){
        SzyfrujRSA program = new SzyfrujRSA();
        program.interfejs();
        System.out.println("Program się skonczył");
    }
}
