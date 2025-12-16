package loterija;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Loterija {
    
    static boolean izlozeVeikta = false;
    
    private static final int PAUZES_SEKUNDES = 60;
    private static final int PAUZES_MILISEKUNDES = PAUZES_SEKUNDES * 1000;
    
    private static String pedejaIzlozeDatums = "";
    
    public static void main(String[] args) {
    	
        Stack<Integer> laimigieSkaitli = new Stack<>();
        Stack<Integer> bumbinuSteks = new Stack<>();
        
        String izvele;
        String[] darbibas = {"Veikt jaunu izlozi", "Apskatīt laimīgos skaitļus un datumu", "Apturēt"};
        
        do {
        	 izvele = (String)JOptionPane.showInputDialog(null, "Loterijas izloze (3 laimīgie skaitļi)", "Darbību saraksts", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[0]);
             
             if (izvele == null)
                 izvele = "Apturēt";
             
             switch(izvele) {
                 case "Veikt jaunu izlozi":
                     
                     if (izlozeVeikta) {
                         try {
                             JOptionPane.showMessageDialog(null, 
                                 "Vēl jāgaida " + PAUZES_SEKUNDES + " sekundes.(Piezīme: Programma gaidīs 60 sekundes, un tikai TAD veiks izlozi.\n", "Gaidīšana", JOptionPane.WARNING_MESSAGE);
                             Thread.sleep(PAUZES_MILISEKUNDES);
                         } catch (InterruptedException e) {
                             Thread.currentThread().interrupt();
                             JOptionPane.showMessageDialog(null, "Gaidīšanas process pārtraukts.", "Kļūda", JOptionPane.ERROR_MESSAGE);
                             break;
                         }
                     }
             }
        } while (!izvele.equals("Apturēt"));
    }
}