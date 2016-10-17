/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DrawPoints extends Applet implements MouseListener {
int x=0,y=0;
int Xmin,Xmax,Ymin,Ymax,Xmid,Ymid;
Graphics2D g2d;
int iter=4;
ReadStopesFromFile readstop;
VectorStops r1,r2,r3,r4;
int stopLoc[][];
 ArrayList<VectorStops> list;

    public void init()
    {
       GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
       Rectangle bounds = env.getMaximumWindowBounds();

       System.out.println(bounds.getMaxX()+" "+bounds.getMaxY());

       setBounds(bounds);
       setMaximumSize(bounds.getSize());

       readstop=new ReadStopesFromFile();
       try{
             stopLoc=readstop.getStops();
        }catch(Exception e)
       {
            System.out.println(" the error "+e.getMessage());
        }
        
    }

   public void paint(Graphics g)
   {
       g2d=(Graphics2D)g;
       g2d.setColor(Color.GREEN);
       Dimension maximumSize = getMaximumSize();
       int height= maximumSize.height;
       int width=maximumSize.width;

       System.out.println("Height= "+height+" width= "+width);

       Xmin=0;
       Ymin=0;
       Xmax=width;
       Ymax=height;

        int Xmid=(Xmax-Xmin)/2  + Xmin;
        int Ymid=(Ymax-Ymin)/2  + Ymin;
        
        g2d.drawLine(Xmid, Ymin,Xmid , Ymax);
        g2d.drawLine(Xmin, Ymid,Xmax , Ymid);
        
        
        
         r1=new VectorStops();
         r2=new VectorStops();
         r3=new VectorStops();
         r4=new VectorStops();
         
          for(int i=0; i<stopLoc[0].length; i++)
        {
            int Xloc = stopLoc[0][i];  // x cordinate of ith stop
            int Yloc = stopLoc[1][i];  // y cordinate of ith stop

            if( (Xloc >= Xmin) && (Yloc >= Ymin) && (Xloc < Xmid) && (Yloc < Ymid) )
            {
                r1.storeStopeXYCordinate(Xloc, Yloc);
            }
            else if((Xloc >= Xmid) && (Yloc >= Ymin) && (Xloc < Xmax) && (Yloc < Ymid))
            {
                r2.storeStopeXYCordinate(Xloc, Yloc);
            }
            else if((Xloc >= Xmin) && (Yloc >= Ymid) && (Xloc < Xmid) && (Yloc < Ymax))
            {
                r3.storeStopeXYCordinate(Xloc, Yloc);
            }
            else
            {
                r4.storeStopeXYCordinate(Xloc, Yloc);
            }
                
        }
          
        r1.populateStopArray(); 
        r2.populateStopArray();
        r3.populateStopArray(); 
        r4.populateStopArray();
        
          
        list=new ArrayList();
        
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        
        
        
        displayRoute(list);

  //    g2d.drawLine(0,0,getWidth(),getHeight());
      g2d.drawString("Xcordinates  "+x+"Y cordinate "+y, 100, 100);
      this.addMouseListener(this);
   }

    public void mouseClicked(MouseEvent e) {
       x=e.getX();
       y=e.getY();
       repaint();
       System.out.println(x+" "+y);
    }


    void drawPintsOnscreen(int stopLoc[][])
    {
        for(int i=0; i<stopLoc[0].length; i++)
       {
          g2d.fillOval(stopLoc[0][i], stopLoc[1][i], 7, 7);
       }
    }
    
    
    void displayRoute(ArrayList<VectorStops> list)
    {
       Iterator<VectorStops> it=list.iterator();
       VectorStops vs;
       int i=0;
       while(it.hasNext())
       {
           i++;
           System.out.println(it.toString());
           vs=it.next();
           display(vs.getStopLocation());  
           if(i==0)
               g2d.setColor(Color.red);
           if(i==2)
               g2d.setColor(Color.BLUE);
           if(i==3)
               g2d.setColor(Color.ORANGE);
           if(i==4)
               g2d.setColor(Color.green);
           
           
           drawPintsOnscreen(vs.getStopLocation());
       }
    }
    
    void display(int x[][])
    {
        System.out.println("Length of route  "+this.toString()+" ="+x[0].length);
//        for(int i=0; i<x[0].length; i++)
//        {
//            System.out.println("X= "+x[0][i]+"\t  Y= "+x[1][i]);
//        }
    }

    public void mousePressed(MouseEvent e) {
       
    }

    public void mouseReleased(MouseEvent e) {
       
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }
}
