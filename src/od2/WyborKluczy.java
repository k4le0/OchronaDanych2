/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import static od2.TestFermata.*;


/**
 *
 * @author k4le0
 */




public class WyborKluczy {
    int duzeP;
    int duzeQ;
    int duzeE;
    int duzeD;
    int duzeN;
    int fiOdN;
    private final Scanner sc = new Scanner(System.in);
    int gornaGranica;   
    int iloscProb;
    private static String sciezkaDoPlikuPUBLICKEY = "Documents/public.key";
    private static String sciezkaDoPlikuPRIVATEKEY = "Documents/private.key";
    
    public static int Euklides(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
    
    public static int rozszerzonyEuklides(int p, int q) {
        while (q != 0) {
            int temp = q;
            System.out.println("p: "+p);
            q = p % q;
            System.out.println("q: "+q);
            p = temp;
        }
        return p;
    }
    public void losujWartosci(){
            this.duzeP = duzeP;
            this.duzeQ = duzeQ;
            
            
            duzeP = losujIsprawdz(gornaGranica, iloscProb);
            duzeQ = losujIsprawdz(gornaGranica, iloscProb);
            fiOdN = ((duzeP-1)*(duzeQ-1));
            duzeE = 0;
            duzeD = 0;
            // System.out.println("Wylosowane P: "+duzeP);
        }
    public void drukujWartosci(){

        drukujPauze();
        System.out.println("Wylosowane P: "+duzeP);
        System.out.println("Wylosowane Q: "+duzeQ);
        System.out.println("Wylosowane E: "+duzeE);
        if(duzeD!=0){
            System.out.println("Obliczone D: "+duzeD);
        }
        System.out.println("Górna granica losowanej liczby: "+gornaGranica);
        System.out.println("Czułość testu Fermata przy sprawdzaniu czy p i q są pierwsze: "+iloscProb);
        drukujPauze();
    }
    
        public void drukujKlucze(){
 
        drukujPauze();
        System.out.println("KPub =  "+duzeE +", "+duzeN);
        System.out.println("KPriv = "+duzeD +", "+duzeN);
        drukujPauze();
    }
    
        public static void zapiszKlucze() {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String s = "String builder content";
            StringBuilder sB = new StringBuilder(s);
            try {
		String content = "This is the content to write into file\n";
		fw = new FileWriter(sciezkaDoPlikuPUBLICKEY);
		//bw = new BufferedWriter(fw);
		bw.write(content);
                
                //bw.write(sB.toString());
                
		System.out.println("Zapisano kluczce do pliku.");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
           
        private void zapiszKlucze2(){
            
            	try {   
                    
                        File homedir = new File(System.getProperty("user.home"));
                        File plikPub = new File(homedir, sciezkaDoPlikuPUBLICKEY);
                        File plikPriv = new File(homedir, sciezkaDoPlikuPRIVATEKEY);
                        
                        plikPub.createNewFile(); 
                        plikPriv.createNewFile();
                        
                        String pubKey = duzeE +" "+duzeN;
			FileOutputStream wyjsciePub = new FileOutputStream(plikPub);
			wyjsciePub.write(pubKey.getBytes());
			wyjsciePub.close();
                        
                        String privKey = duzeD +" "+duzeN;
			FileOutputStream wyjsciePriv = new FileOutputStream(plikPriv);
			wyjsciePriv.write(privKey.getBytes());
			wyjsciePriv.close();
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        }
        
        
    public void losujE(){
        this.duzeE = duzeE;
        Random rand = new Random();
        //int iloczynOdwrotnosciPQ = 0;
        duzeE = rand.nextInt(gornaGranica);
        //duzeE = losujIsprawdz(1000, iloscProb); 
        //iloczynOdwrotnosciPQ = ((duzeP-1)* (duzeQ-1));
        
        while (Euklides(fiOdN,duzeE)!=1){
            //duzeE = losujIsprawdz(1000, iloscProb);
            duzeE = rand.nextInt(gornaGranica);
            System.out.println("Wylosowane e: "+duzeE);
            System.out.println("Euklides: "+Euklides(fiOdN,duzeE));
        }
        
    }
    public void znajdzD(){
        this.duzeD = duzeD;
        int temp = 0;
        int k=1;                     
        temp = (fiOdN+1)/duzeE;                  
        for(k=2; (duzeE*temp)%fiOdN!=1; k++){
            temp = (k*fiOdN+1)/duzeE;
            System.out.println(k+"   temp:"+temp+"  e * d mod fi = 1   "+(duzeE*temp)%fiOdN);
                    } 
        duzeD = temp;   
        System.out.println("Znalezione d: " + duzeD);
    }
    
    public void obliczN(){
        this.duzeN = duzeN;
        duzeN = duzeP * duzeQ;
    }
        
    public void interfejs(){
        
        String wejscieStr;
        int wejscieInt;
        
        drukujPauze();
        //losujWartosci();
        drukujWartosci();
        drukujPauze();
        System.out.println("Wybierz opcje: \n\n 1. Generuj nowy zestaw p,q i e. \n 2. Generowanie kluczy \n 3. Zapisz do pliku \n 4. Zmień wrażliwość testu Fermata oraz górną granice obliczeń \n 5. Koniec"); 
        System.out.print(":");
        int opcja = sc.nextInt();
        switch(opcja){
            case 0:
                System.out.println("Ukryta opcja 0: Sprwadzam dla wartości z ćwiczenia. ");
                duzeP = 173;
                duzeQ = 157;
                duzeE = 179;
                fiOdN = (duzeP-1)*(duzeQ-1);
                break;
            case 1:
                drukujPauze();
                System.out.println("Opcja 1: Generowanie nowego zestawu liczb p, q oraz e. ");
                drukujPauze();
                losujWartosci();
                losujE();
                break;
            case 2:
                drukujPauze();
                System.out.println("Opcja 2: Generowanie kluczy.");
                drukujPauze();
                znajdzD();
                obliczN();
                drukujKlucze();
                break;
            case 3:
                drukujPauze();
                System.out.println("Opcja 3: Zapis do pliku. ");
                drukujPauze();
                zapiszKlucze2();
                break;
            case 4:
                drukujPauze();
                System.out.println("Opcja 1: Podmieniamy dane. ");
                drukujPauze();
                System.out.println("Podaj ilość dokladność proby Femata:");
                System.out.print("  liczba:");
                iloscProb = sc.nextInt();
                System.out.println("\nPodaj górną granice lowowanych liczb" );
                System.out.print("  liczba:");
                gornaGranica = sc.nextInt();
                break;
            case 5:
                drukujPauze();
                System.out.println("Do widzenia !");
                drukujPauze();
                return;
            default:
                System.out.println("Spróbuj jeszcze raz.");
                break;
        }
        interfejs();
    }   
    public static void main(String[] args){
        WyborKluczy program = new WyborKluczy();
        program.gornaGranica = 1000;
        program.iloscProb = 5;
        program.losujWartosci();
        program.losujE();
        program.interfejs();
        System.out.println("Program sie konczy");
    }
}
