/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package V172;
import V171.*;
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
        hilosVarios nuevo=new hilosVarios();
        hilosVarios2 nuevo2=new hilosVarios2(nuevo),
                     nuevo3=new hilosVarios2(nuevo2);
               
    /*    nuevo.start();
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
       */ 
    nuevo.start();
    nuevo2.start();
    nuevo3.start();
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
class hilosVarios2 extends Thread{
    public hilosVarios2(Thread hilo)
    {
        this.Hilo=hilo;
    }
    public void run(){
        try {
            Hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(hilosVarios2.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 15; i++) {
            System.out.println("ejecutando hilos :::: "+getName()+" :::: "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(hilosVarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private Thread Hilo;
}