package od2;

import static java.lang.Math.pow;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Simple RSA public key encryption algorithm implementation.
 * <P>
 * Taken from "Paj's" website:
 * <TT>http://pajhome.org.uk/crypt/rsa/implementation.html</TT>
 * <P>
 * Adapted by David Brodrick
 */

public class Test2 {
  private BigInteger n, d, e;

  private int bitlen = 1024;

  /** Create an instance that can encrypt using someone elses public key. */
  public Test2(BigInteger newn, BigInteger newe) {
    n = newn;
    e = newe;
  }

  /** Create an instance that can both encrypt and decrypt. */
  public Test2(int bits) {
    bitlen = bits;
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }

  /** Encrypt the given plaintext message. */
  public synchronized String encrypt(String message) {
    return (new BigInteger(message.getBytes())).modPow(e, n).toString();
  }

  /** Encrypt the given plaintext message. */
  public synchronized BigInteger encrypt(BigInteger message) {
    return message.modPow(e, n);
  }

  /** Decrypt the given ciphertext message. */
  public synchronized String decrypt(String message) {
    return new String((new BigInteger(message)).modPow(d, n).toByteArray());
  }

  /** Decrypt the given ciphertext message. */
  public synchronized BigInteger decrypt(BigInteger message) {
    return message.modPow(d, n);
  }

  /** Generate a new public and private key set. */
  public synchronized void generateKeys() {
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }

  /** Return the modulus. */
  public synchronized BigInteger getN() {
    return n;
  }

  /** Return the public key. */
  public synchronized BigInteger getE() {
    return e;
  }

  

  
  /** Trivial test program. */
  public static void main(String[] args) {
    Test2 rsa = new Test2(1024);
    //int parzysta = 0;
    String text1 = "KOKOK cholera go wie jak to ma byc dlugie ";
    //              1234567890123456789012345678901234567890123
    System.out.println("Plaintext: " + text1);
    System.out.println("getBytes: "+ Arrays.toString(text1.getBytes()));
    
    
    //int[] output = new int[text1.length()];
    //    for(int i = 0; i < text1.length(); i++) {
    BigInteger plaintext = new BigInteger(text1.getBytes());
    //    }
        
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //byte[] tablicaBitow = text1.getBytes();
    //int check = 0;
    /*
    System.out.println("Ilosc krokow");
    for(int k=0;k<text1.length();k=k+2){  
        //System.out.println("k: "+k+"");
        //if((k+1)<text1.length()) System.out.println("k: "+k+" k+1: "+(k+1));
        
        System.out.print(tablicaBitow[k]); 
        if((k+1)<text1.length()) System.out.print(tablicaBitow[k+1]); 
        check = tablicaBitow[k];
        System.out.println();
        System.out.println("Check: "+check);
        
    }
    System.out.println("Koniec liczenia krokow");
    
    for (byte b : tablicaBitow) {
    System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
    }
    */
    String szescioznak = "";
    int szescioznakInt = 0;
    int a = 0;
    int b = 0;
    
    int[] tablicaZnakow = new int[((text1.length()/2)+1)];

    int licznik = 0;
    
    for(int i = 0; i < text1.length(); i=i+2) {
            a = text1.charAt(i);
            if (a<100){
                szescioznak += "0"+a;
            } else {
                szescioznak += a;
            }
            if((i+1)<text1.length()){
            b = text1.charAt(i+1);
            if (b<100){
                szescioznak += "0"+b;
            } else {
                szescioznak += b;
            }            
            } else {
                szescioznak += "000";
            }
            tablicaZnakow[licznik] = Integer.parseInt(szescioznak);
            System.out.println("String: "+szescioznak+" sting to int "+tablicaZnakow[licznik]);
            licznik++;
            szescioznak = ""; 
    }
    
    System.out.println("Utworzona tablica znakow: " +Arrays.toString(tablicaZnakow));
    
    //*********************************************************************************SZYFROWANIE
    

    int E = 183;
    int D = 142983;
    BigInteger N = BigInteger.valueOf(690247);
    System.out.println("N: "+N);
    int wynik = 0;
    BigInteger[] kryptogram = new BigInteger[tablicaZnakow.length];
    BigInteger M;
    
    for(int i = 0; i < tablicaZnakow.length; i++){
        
        M = BigInteger.valueOf(tablicaZnakow[i]);
        kryptogram[i] = (M.pow(E)).mod(N);
        
    }
    
    System.out.println("BigInteger kryptogram: "+ Arrays.toString(kryptogram));
    
    //*********************************************************************************ROZSZYFROWANIE
    BigInteger[] rozszyfrowany = new BigInteger[kryptogram.length];
    int[] rozszyfrowanyInt = new int[kryptogram.length];
    
    for(int i = 0; i < kryptogram.length; i++){
        
            //M = BigInteger.valueOf(tablicaZnakow[i]);
        //rozszyfrowany[i] = (kryptogram[i].pow(D)).mod(N);
        rozszyfrowanyInt[i] =  ((kryptogram[i].pow(D)).mod(N)).intValue();
    } 
    System.out.println("BigInteger rozszyfrowany: "+ Arrays.toString(rozszyfrowanyInt));
     //*********************************************************************************ROZSZYFROWANY DO STRINGa
    String rozszyfrowanyTekstJawny = "";
    String n1;
    String n2;
    String s;
   
    for(int i = 0; i < rozszyfrowanyInt.length-1; i++){ 
        s = String.valueOf(rozszyfrowanyInt[i]);
        System.out.println(s.length());
        
        if(s.length()>5){
            n1 = s.substring(0,3);
            n2 = s.substring(3);
            System.out.println("6: n1: "+n1+" n2: "+n2);
            

        }else {
            n1 = s.substring(0,2);
            n2 = s.substring(2);
            System.out.println("5: n1: "+n1+" n2: "+n2);
        }
        
        rozszyfrowanyTekstJawny += (char) Integer.parseInt(n1);
        rozszyfrowanyTekstJawny += (char) Integer.parseInt(n2);
        
    }
    int ostatni = rozszyfrowanyInt[rozszyfrowanyInt.length-1];
    
    if(ostatni!=0){
        s = String.valueOf(ostatni);
                if(s.length()>5){
            n1 = s.substring(0,3);
            
            System.out.println("6: n1: "+n1);
        }else {
            n1 = s.substring(0,2);
            
            System.out.println("5: n1: "+n1);
        }
        rozszyfrowanyTekstJawny += (char) Integer.parseInt(n1);
                
        }
    System.out.println("rozszyfrowanyTekstJawny: "+ rozszyfrowanyTekstJawny);
    /* 
    System.out.println("BigInteger palintext: "+ plaintext);
    
    BigInteger ciphertext = rsa.encrypt(plaintext);
    System.out.println("Ciphertext: " + ciphertext);
    plaintext = rsa.decrypt(ciphertext);

    String text2 = new String(plaintext.toByteArray());
    System.out.println("Plaintext: " + text2);
    //output[i] = o;
    */
    
  }
}