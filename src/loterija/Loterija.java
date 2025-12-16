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
                             JOptionPane.showMessageDialog(null, 
                                 "Gaidīšana nestrādā :(", "Gaidīšana", JOptionPane.WARNING_MESSAGE);
                             break;
                     }
                     
                     Random rand = new Random();
                     laimigieSkaitli.clear();
                     bumbinuSteks.clear();
                     
                     String laimigoSkaitluString = "";
                     
                     for (int i = 0; i < 30; i++) {
                         bumbinuSteks.push(rand.nextInt(10));
                     }
                     
                     for (int piegajiens = 1; piegajiens <= 3; piegajiens++) {
                         int vilksanasSkaits = rand.nextInt(10); 
                         
                         for (int k = 0; k < vilksanasSkaits; k++) {
                             if (!bumbinuSteks.isEmpty()) {
                                 bumbinuSteks.pop();
                             }
                         }
                         
                         if (!bumbinuSteks.isEmpty()) {
                             int laimigais = bumbinuSteks.pop();
                             laimigieSkaitli.push(laimigais);
                             laimigoSkaitluString += laimigais;
                         } else {
                        	 JOptionPane.showMessageDialog(null, "Nav iespējams veikt izlozi! Bumbiņu steks iztukšojās.");
                             break;
                         }
                     }
                     
                     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     pedejaIzlozeDatums = dateFormat.format(new Date()); 
                     izlozeVeikta = true;

                     JOptionPane.showMessageDialog(null, "Skaitļi izlozēti veiksmīgi!\n", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                     break;
                     
                 case "Apskatīt laimīgos skaitļus un datumu":
                     if (!izlozeVeikta) {
                         JOptionPane.showMessageDialog(null, "Izloze vēl nav veikta.", "Paziņojums", JOptionPane.WARNING_MESSAGE);
                         break;
                     }
                     
                     String rezultati = "";
                     
                     rezultati = rezultati + "Datums un laiks: " + pedejaIzlozeDatums + "\n\n";
                     rezultati = rezultati + "Laimīgie skaitļi:\n";
                     
                     String skaitluVirkne = laimigieSkaitli.toString();
                     
                     rezultati = rezultati + skaitluVirkne;
                     
                     JOptionPane.showMessageDialog(null, rezultati, "Laimīgie Skaitļi", JOptionPane.INFORMATION_MESSAGE);
                     break;
                 case "Apturēt":
                     JOptionPane.showMessageDialog(null, "Programma apturēta.");
                     break;
             }
        } while (!izvele.equals("Apturēt"));
    }
}