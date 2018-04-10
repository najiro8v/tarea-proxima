/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package V173;

import static java.lang.Math.random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author casa
 */
public class BancoSincronizar {
    public static void main(String [] args)
    {
       Banco b =new Banco();
       for(int i=0;i<100;i++)
       {
           ejecucionTranferencias hilo=new ejecucionTranferencias(b, i,2000);
           Thread t=new Thread (hilo);
           t.start();
       }
    }
}
class Banco{
    public Banco(){
        cuentas=new double[100];
        for (int i = 0; i < cuentas.length; i++) {
            cuentas[i]=2000;
        }
    }
    public void tranferencia(int cuentaOrigen,int cuentaDestino,double cantidad)
    {cierreBanco.lock();
    try{
        if(cuentas[cuentaOrigen]<cantidad){
            return;
        }
        System.out.println(Thread.currentThread());
        cuentas[cuentaOrigen]-=cantidad;
        System.out.printf("%10f de %d para %d",cantidad,cuentaOrigen,cuentaDestino);
        cuentas[cuentaDestino]+=cantidad;
        System.out.printf("saldo Total: %10.2f%n ",getStotal());
        }finally{
            cierreBanco.unlock();}
    }
    public double getStotal(){
    double suma_Cu=0;
    for(double a: cuentas){
        suma_Cu+=a;
        }
    return suma_Cu;
    }
private final double[] cuentas;
private Lock cierreBanco= new ReentrantLock();
}
class ejecucionTranferencias implements Runnable
{   
    private Banco banquito;
    private int deLaCuenta;
    private double maximus;

    @Override
    public void run() {
   try {
        while(true){
        int paraLa=(int)(100*random());
        double cantidad=maximus*random();
        banquito.tranferencia( deLaCuenta,paraLa, cantidad);
        
            Thread.sleep((int)(10*random()));
        
    }
   } catch (InterruptedException ex) {
            Logger.getLogger(ejecucionTranferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ejecucionTranferencias(Banco b,int Corigen,double max){
    this.banquito=b;
    this.deLaCuenta=Corigen;
    this.maximus=max;
    }
    
}