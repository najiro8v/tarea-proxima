/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package V171;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author casa
 */
public class sincronizandoHilos {
    public static void main (String []args)
    {
        hilosVarios nuevo=new hilosVarios()
                ,nuevo2=new hilosVarios();
        
        nuevo.start();
        try {
            nuevo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(sincronizandoHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevo2.start();
         try {
            nuevo2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(sincronizandoHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Terminadas las tareas");
    }
}
class hilosVarios extends Thread{
    public void run(){
        for (int i = 0; i < 15; i++) {
            System.out.println("ejecutando hilos :::: "+getName()+" :::: "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(hilosVarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}