/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od2;

/**
 *
 * @author k4le0
 */
public class Test {
    
    public static void test1(){
                long duzeP = 173;
                long duzeQ = 157;
                
                long duzeE = 179;
               
                long fiOdN = (duzeP-1)*(duzeQ-1);
                long duzeD;
                
                
                long temp = 0;
                int k =0;
                               
                temp = (fiOdN+1)/duzeE;
                                
                 for(k=2; (duzeE*temp)%fiOdN!=1; k++){
                    temp = (k*fiOdN+1)/duzeE;
                    System.out.println(k+"   temp:"+temp+"  e * d = 1 mod fi: "+duzeE*temp+"=?="+1%fiOdN+"   e * d mod fi = 1   "+(duzeE*temp)%fiOdN);
                    } 
                duzeD = temp;   
                
                System.out.println("DuzeD: "+duzeD);
    }           
public static void main(String[] args ) {
        test1();
}
}
