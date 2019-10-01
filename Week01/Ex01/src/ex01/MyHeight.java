/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex01;

/**
 *
 * @author ehughes2
 */
public class MyHeight {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                double cm = 188;
        double feet;
        double inches;
        
        inches = cm / 2.54;
        feet = inches / 12; 
        inches %= 12;
       
        System.out.println(cm + " " + inches + " " + feet);
        System.exit(0);
    }
    
}
