/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import static od2.TestFermata.drukujPauze;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import static od2.TestFermata.drukujSpacje;
/**
 *
 * @author k4le0
 */
public class SzyfrujRSA {
    private static String sciezkaDoPlikuPUBLICKEY = "Documents/public.key";
    private static String sciezkaDoPlikuPRIVATEKEY = "Documents/private.key";  
    private static String sciezkaDoPlikuTEKSTJAWNY= "Documents/text.txt";
    private static String sciezkaDoPlikuSZYFROGRAM = "Documents/text.cry"; 
    private String tekstJawny1, tekstJawny2, szyfrogram1, szyfrogram2;
    private int n,e,d;
    
    private int[] utworzTabliceZnakow(String text1){
        String szescioznak = "";
        //int szescioznakInt = 0;
        int a = 0;
        int b = 0;
        char charA, charB, tempB;
        int[] tablicaZnakow;
        //int[] tablicaZnakow = new int[text1.length()];

        if(text1.length()%2!=0){
            tablicaZnakow = new int[(text1.length()/2)+1];
            System.out.println("TESKT JAWNY - ilosc znakow nieparzysta, tablicaZnakow ma dlugosc: "+tablicaZnakow.length);

        } else{
            tablicaZnakow = new int[(text1.length()/2)];
            System.out.println("TESKT JAWNY - ilosc znakow parzysta, tablicaZnakow ma dlugosc: "+tablicaZnakow.length);
        }
                
        int licznik = 0;
    
    for(int i = 0; i < text1.length(); i=i+2) {
            charA = text1.charAt(i);
            a = charA;
            tempB = 0;
            if (a<100){
                szescioznak += "0"+a;
            } else {
                szescioznak += a;
            }
            if((i+1)<text1.length()){
            charB = text1.charAt(i+1);
            b = charB;
            if (b<100){
                szescioznak += "0"+b;
                
            } else {
                szescioznak += b;
            } 
            tempB = charB;
            } else {
                szescioznak += "000";
            }
            tablicaZnakow[licznik] = Integer.parseInt(szescioznak);
            System.out.println("Para znaków | "+Character.toString(charA)+" "+Character.toString(tempB)+" | zostala zapisana jako string | "+szescioznak+" | dalej jako liczba: "+tablicaZnakow[licznik]);
            licznik++;
            szescioznak = ""; 
    }
    
    System.out.println("Utworzona tablica znakow: " +Arrays.toString(tablicaZnakow));
    return tablicaZnakow;
    }
    
    private BigInteger[] szyfruj(int[] tablicaZnakow, boolean ED){
        int X;
        if(ED){
            X = e;
        }
        else {
            X = d; 
        }
        BigInteger N = BigInteger.valueOf(n);
        
        //System.out.println("N: "+N);
        
        //int wynik = 0;
        BigInteger[] kryptogram;
        BigInteger M;
        kryptogram = new BigInteger[tablicaZnakow.length];
    
        for(int i = 0; i < (tablicaZnakow.length); i++){
        
        M = BigInteger.valueOf(tablicaZnakow[i]);
        kryptogram[i] = (M.pow(X)).mod(N);
        
        }

    //System.out.println("Kryptogram: "+ Arrays.toString(kryptogram));
    
    return kryptogram;
    }
    
    
    public static String wczytajZPliku(String nazwapliku){
    String output = "";
    try {
           File homedir = new File(System.getProperty("user.home"));       
           File pelnaSciezka = new File(homedir, nazwapliku);
           
           FileReader fr = new FileReader(pelnaSciezka);
           BufferedReader br  = new BufferedReader(fr);
           String line;
           while ((line = br.readLine())!=null) {
               //System.out.println(line);
               output += line;
           }
       } catch (FileNotFoundException e) {
           System.out.println("File is not available for Read");
           e.printStackTrace();
       }       catch (IOException e) {
           System.out.println("there is a error while reading the file");
           e.printStackTrace();
       }
        return output;
   }
    
    public void wydzielWartosciZPubkey(String linia){
        String[] rozdzielone = linia.split("\\s+");
        
        e = Integer.parseInt(rozdzielone[0]);  
        n = Integer.parseInt(rozdzielone[1]);
    }
    
        public void wydzielWartosciZPrivkey(String linia){
        String[] rozdzielone = linia.split("\\s+");
        int temp_n = Integer.parseInt(rozdzielone[1]);
        
        d = Integer.parseInt(rozdzielone[0]);
        if(n!=0){
            if(n!=temp_n){System.out.print("Cos nie tak - wczytujesz klucz nie z tej pary!");   
            }
        }
        n=temp_n;
        //System.out.print("Wczytano wartosci z klucza prywatnego");
        
    }
        public int[] wydzielWartosciZPlikuCry(String szyfrogram){
            
            String [] items = szyfrogram.split(",");
            int[] wyjscie =new int[items.length];
           for(int i =0;i<items.length;i++){
               wyjscie[i]=Integer.parseInt(items[i]);
           }
            
            return wyjscie;
        }
        
    private static String bigInt2String(BigInteger[] theAray) {
        StringBuilder sb = new StringBuilder();
        String item;
        //sb.append("[");
        for (int i = 0; i < theAray.length; i++) {
         if (i > 0) {
            sb.append(",");
         }
         item = String.valueOf(theAray[i]);
         //BigInteger.valueOf(tablicaZnakow[i])
         sb.append(item);
      }
      //sb.append("]");
      return sb.toString();
    }
   
    
    public static void zapiszDoPliku(String nazwapliku, String tresc) {
         
            try {
                File homedir = new File(System.getProperty("user.home")); 
                File pelnaSciezka = new File(homedir, nazwapliku);
                
                pelnaSciezka.createNewFile();
 
                FileOutputStream wyjscie = new FileOutputStream(pelnaSciezka);
                
		wyjscie.write(tresc.getBytes());
		wyjscie.close();
                        
		System.out.println("Zapisano do pliku.");
                
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
   public void interfejsZmianyWartosciDomyslnych(){
        boolean Przelacznik2 = true;
        while(Przelacznik2){
            drukujWartosciDefaultowe();
        }
        
   }

   public boolean sprawdzDlugoscKlucza(int tablicaZnakow[]){
       
       for(int i=0;i<tablicaZnakow.length;i++){
       if (n<=tablicaZnakow[i]) return false;
       }
       return true;
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

    public void drukujWartosciDefaultowe(){

        drukujPauze();
        System.out.println("Ścieżka do pliku private.key: "+sciezkaDoPlikuPRIVATEKEY);
        System.out.println("Ścieżka do pliku public.key: "+sciezkaDoPlikuPUBLICKEY);
        System.out.println("Ścieżka do pliku text.txt: "+sciezkaDoPlikuTEKSTJAWNY);
        System.out.println("Ścieżka do pliku text.cry: "+sciezkaDoPlikuSZYFROGRAM);
        drukujPauze();
    }
    
    public void interfejs(){
        Scanner sc = new Scanner(System.in);
        //CLS();
        String kluczpub, kluczpriv;
        
        boolean Przelacznik = true;
        
        while(Przelacznik){
            drukujPauze();
            System.out.println("Jeżeli chcesz zaszyfrować tekst: wpierw wczytaj tekst jawny i klucz publiczny(1), następnie zaszyfruj (2). ");
            System.out.println("Jeżeli chcesz odszyfrować tekst: wpierw wczytaj szyfrogram jawny i klucz prywatny(3), następnie zaszyfruj (4). ");
            System.out.println("Wybierz opcje: \n\n 0. Instrukcja \n 1. Wczytaj tekst jawny i klucz publiczny \n 2. Szyfruj tekst jawny   \n 3. Wczytaj szyfrogram i klucz prywatny \n 4. Rozszyfruj szyfrogram   \n 5. Podaj/Zmień ścierzki do plików \n 6. Koniec"); 
            System.out.print(":");
            int opcja = sc.nextInt();
            
            switch(opcja){
            case 0:
                drukujSpacje();
                clearConsole();
                drukujPauze();
                System.out.println("Instrukcja: ");
                System.out.println("Jeżeli chcesz zaszyfrować tekst: wpierw wczytaj tekst jawny i klucz publiczny(1), następnie zaszyfruj (2). ");
                System.out.println("Jeżeli chcesz odszyfrować tekst: wpierw wczytaj szyfrogram jawny i klucz prywatny(3), następnie zaszyfruj (4). ");
                System.out.println("Aby zmienić lokalizacje plików text.txt, text.cry, public.key, private.key wybierz (5).  ");
                drukujPauze();
                break;
            case 1:
                clearConsole();
                drukujSpacje();
                drukujPauze();
                System.out.println("Opcja 1: Wczytaj tekst jawny i klucz publiczny ");
                drukujPauze();
                tekstJawny1 = wczytajZPliku(sciezkaDoPlikuTEKSTJAWNY);
                System.out.println("Z pliku: \n "+sciezkaDoPlikuTEKSTJAWNY+"\nWczytano następujący, tekst jawny: \n"+tekstJawny1);
                drukujPauze();
                kluczpub = wczytajZPliku(sciezkaDoPlikuPUBLICKEY);
                System.out.println("Z pliku: \n "+sciezkaDoPlikuPUBLICKEY+"\nWczytano klucz publiczny: \n"+kluczpub);
                wydzielWartosciZPubkey(kluczpub);
                
                
                break;
            case 2:
                clearConsole();
                drukujSpacje();
                drukujPauze();
                System.out.println("Opcja 2: Szyfruj wczytany tekst jawany za pomoca klucza publicznego");
                drukujPauze();
                int[] tablicaZnakow = utworzTabliceZnakow(tekstJawny1);
                drukujPauze();
                if(sprawdzDlugoscKlucza(tablicaZnakow)) {
                    System.out.println("Klucz ma odpowidnia dlugość, przechodzę do szyfrowania");
                } else {
                    System.out.println("Klucz ma nieodpowiednią długość, stwórz lub wybierz dłuższy klucz");
                    break;
                } 
                drukujPauze();
                String szyfrogram1;
                szyfrogram1 = bigInt2String(szyfruj(tablicaZnakow, true));
                System.out.println("Szyfrogram: "+szyfrogram1);
                zapiszDoPliku(sciezkaDoPlikuSZYFROGRAM,szyfrogram1);
                break;
            case 3:
                clearConsole();
                drukujSpacje();
                drukujPauze();
                System.out.println("Opcja 3: Wczytaj szyfrogram i klucz prywatny");
                drukujPauze();
                szyfrogram2 = wczytajZPliku(sciezkaDoPlikuSZYFROGRAM);
                System.out.println("Z pliku: \n "+sciezkaDoPlikuSZYFROGRAM+"\nWczytano następujący, szyfrogram: \n"+szyfrogram2);
                drukujPauze();
                kluczpriv = wczytajZPliku(sciezkaDoPlikuPRIVATEKEY);
                System.out.println("Z pliku: \n "+sciezkaDoPlikuPRIVATEKEY+"\nWczytano klucz prywatny: \n"+kluczpriv);
                wydzielWartosciZPrivkey(kluczpriv);
                break;
            case 4:
                //BigInteger[] tekstJawny2Big;
                clearConsole();
                drukujSpacje();
                drukujPauze();
                System.out.println("Opcja 4: Rozszyfruj wczytany szyfrogram za pomoca klucza prywatnego");
                drukujPauze();
                //System.out.println(Arrays.toString(wydzielWartosciZPlikuCry(szyfrogram2)));
                //tekstJawny2Big[] = new BigInteger szyfruj(wydzielWartosciZPlikuCry(szyfrogram2),false);
                tekstJawny2 = bigInt2String(szyfruj(wydzielWartosciZPlikuCry(szyfrogram2), false));
                System.out.println("Rozszyfrowany tekst: \n"+tekstJawny2);
                break;
            case 5:
                drukujPauze();
                System.out.println("Zmiana wartości domyślnych");
                drukujPauze();
                interfejsZmianyWartosciDomyslnych();
                clearConsole();
                return;
            case 6:
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
