package V170;



import java.awt.geom.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsoThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame marco=new MarcoRebote();
		
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		marco.setVisible(true);

	}

}


class Pelotahilo implements Runnable{
public Pelotahilo(Pelota unapelota,Component uncomponent )
{
    pelota=unapelota;
    componente=uncomponent;
    
}   
public void run()
{
    System.out.println("inicio "+Thread.currentThread().isInterrupted());
    //for (int i=1; i<=3000; i++){
	//while(Thread.isInterrupted()){
        while(!Thread.currentThread().isInterrupted()){	
				pelota.mueve_pelota(componente.getBounds());
				
				componente.paint(componente.getGraphics());
                            try {
                                Thread.sleep(2);
                            } catch (InterruptedException ex) {
                               Thread.currentThread().interrupt();
                                System.out.println("Imposile interrupcion");
                                //Logger.getLogger(MarcoRebote.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
				
			}
        System.out.println("final "+Thread.currentThread().isInterrupted());
}
private Pelota pelota;
private Component componente;
}

//Movimiento de la pelota-----------------------------------------------------------------------------------------

class Pelota{
	
	// Mueve la pelota invirtiendo posici�n si choca con l�mites
	
	public void mueve_pelota(Rectangle2D limites){
		
		x+=dx;
		
		y+=dy;
		
		if(x<limites.getMinX()){
			
			x=limites.getMinX();
			
			dx=-dx;
		}
		
		if(x + TAMX>=limites.getMaxX()){
			
			x=limites.getMaxX() - TAMX;
			
			dx=-dx;
		}
		
		if(y<limites.getMinY()){
			
			y=limites.getMinY();
			
			dy=-dy;
		}
		
		if(y + TAMY>=limites.getMaxY()){
			
			y=limites.getMaxY()-TAMY;
			
			dy=-dy;
			
		}
		
	}
	
	//Forma de la pelota en su posici�n inicial
	
	public Ellipse2D getShape(){
		
		return new Ellipse2D.Double(x,y,TAMX,TAMY);
		
	}	
	
	private static final int TAMX=15;
	
	private static final int TAMY=15;
	
	private double x=0;
	
	private double y=0;
	
	private double dx=1;
	
	private double dy=1;
	
	
}

// L�mina que dibuja las pelotas----------------------------------------------------------------------


class LaminaPelota extends JPanel{
	
	//A�adimos pelota a la l�mina
	
	public void add(Pelota b){
		
		pelotas.add(b);
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		for(Pelota b: pelotas){
			
			g2.fill(b.getShape());
		}
		
	}
	
	private ArrayList<Pelota> pelotas=new ArrayList<Pelota>();
}


//Marco con l�mina y botones------------------------------------------------------------------------------

class MarcoRebote extends JFrame{
	
	public MarcoRebote(){
		
		setBounds(600,300,400,350);
		
		setTitle ("Rebotes");
		
		lamina=new LaminaPelota();
		
		add(lamina, BorderLayout.CENTER);
		
		JPanel laminaBotones=new JPanel();
		arrancar1=new JButton("hilo1");
                arrancar1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        comienza_el_juego(e);
                    }
                });
                laminaBotones.add(arrancar1);
		/***********/
                
		arrancar2=new JButton("hilo2");
                arrancar2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        comienza_el_juego(e);
                    }
                });
                laminaBotones.add(arrancar2);
                /***********************/
                
		arrancar3=new JButton("hilo3");
                arrancar3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        comienza_el_juego(e);
                    }
                });
                laminaBotones.add(arrancar3);
                /***********************/
                
		detener1=new JButton("detener1");
                detener1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        detener(e);
                    }
                });
                laminaBotones.add(detener1);
                /**************************/
                
		detener2=new JButton("detener2");
                detener2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        detener(e);
                    }
                });
                laminaBotones.add(detener2);
                /**************************/
                
		detener3=new JButton("detener3");
                detener3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        detener(e);
                    }
                });
                laminaBotones.add(detener3);
                /**************************/
		add(laminaBotones, BorderLayout.SOUTH);
                
	}
	
	
	//Ponemos botones
	
	public void ponerBoton(Container c, String titulo, ActionListener oyente){
		
		JButton boton=new JButton(titulo);
		
		c.add(boton);
		
		boton.addActionListener(oyente);
		
	}
	
	//Añade pelota y la bota 1000 veces
	
	public void comienza_el_juego (ActionEvent e){
		
			
			Pelota pelota=new Pelota();
			
			lamina.add(pelota);
                        Runnable nuevo=new Pelotahilo(pelota,lamina);
                        if(e.getSource().equals(arrancar1)){
                            t1= new Thread(nuevo);
                            t1.start();
                        }
                        else if(e.getSource().equals(arrancar2)){
                            t2= new Thread(nuevo);
                            t2.start();
                        }
                        else if(e.getSource().equals(arrancar3)){
                            t3= new Thread(nuevo);
                            t3.start();
                        }
			
			
		
		
	}
	public void detener(ActionEvent e){
       // n.stop();
       if(e.getSource().equals(detener1)){
       t1.interrupt();
       }
       else if(e.getSource().equals(detener2)){
       t2.interrupt();
       }
       else if(e.getSource().equals(detener3)){
       t3.interrupt();
       }
        }
        private Thread t1,t2,t3;
        JButton arrancar1,arrancar2,arrancar3,
                detener1,detener2,detener3;
	private LaminaPelota lamina;
        Thread n;
	
	
}
