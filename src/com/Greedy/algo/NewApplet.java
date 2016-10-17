/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Greedy.algo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Archi
 */
public class NewApplet extends Applet {

    Graphics2D gd;
    ArrayList<CoreGreedyAlgorithm> list;
    CoreGreedyAlgorithm d;
    
    int height, width;
    int Xmin,Xmax,Ymin,Ymax,Xmid,Ymid;
    Scrollbar scrollbar;
    static int c=1;
    
    
    NewApplet(ArrayList<CoreGreedyAlgorithm> list) {
       this.list=list;
    }
  
    
    public void init(){
        scrollbar = new Scrollbar(Scrollbar.HORIZONTAL, 50, 0, 0, 100);
	add(scrollbar);
        scrollbar.addAdjustmentListener((AdjustmentListener) this);
    }

     public void paint(Graphics g)
    {
       gd=(Graphics2D) g;
       gd.setColor(Color.ORANGE);
       GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
       Rectangle bounds = env.getMaximumWindowBounds();

       System.out.println(bounds.getMaxX()+" "+bounds.getMaxY());

       height=(int) bounds.getMaxY();
       width=(int)bounds.getMaxX();

       System.out.println("Height= "+height+" width= "+width);

       Xmin=0;
       Ymin=0;
       Xmax=width;
       Ymax=height;
       
       System.out.println("Height = "+height+" Width = "+width);

         Xmid=(Xmax-Xmin)/2  + Xmin;
        Ymid=(Ymax-Ymin)/2  + Ymin;
        
        gd.drawLine(Xmid, Ymin,Xmid , Ymax);
        gd.drawLine(Xmin, Ymid,Xmax , Ymid);
        
       // gd.drawString("this has been called ", Xmid, Ymid);
        

          Iterator<CoreGreedyAlgorithm> it=list.iterator();
          while(it.hasNext())
          {
              d=it.next();
              newPaint(d);
          }
    }
     
     void newPaint(CoreGreedyAlgorithm d)
     {
         gd.setColor(Color.green);
         gd.setPaintMode();
      
       for(int i=0; i<d.location[0].length; i++)
       {
           if(i==0)
               gd.setColor(Color.red);
           else{
               if(c%8==1)
            gd.setColor(Color.GREEN);
          else if(c%8==2)
              gd.setColor(Color.RED);
           else if(c%8==3)
                gd.setColor(Color.BLUE);
             else if(c%8==4)
              gd.setColor(Color.MAGENTA);
                else if(c%8==5)
                gd.setColor(Color.YELLOW);
                 else if(c%8==6)
                     gd.setColor(Color.DARK_GRAY);
                   else if(c%8==7)
                       gd.setColor(Color.CYAN);
                    else
                        gd.setColor(Color.ORANGE);

           }
             // gd.setColor(Color.GREEN);
          gd.fillOval(d.location[0][i], d.location[1][i], 7, 7);
       }
         // gd.setColor(Color.BLUE);
          if(c%8==1)
            gd.setColor(Color.GREEN);
          else if(c%8==2)
              gd.setColor(Color.RED);
           else if(c%8==3)
                gd.setColor(Color.BLUE);
             else if(c%8==4)
              gd.setColor(Color.MAGENTA);
                else if(c%8==5)
                gd.setColor(Color.YELLOW);
                 else if(c%8==6)
                     gd.setColor(Color.DARK_GRAY);
                   else if(c%8==7)
                       gd.setColor(Color.CYAN);
                    else
                        gd.setColor(Color.ORANGE);
         c++;
          for(int i=0; i<d.location[0].length; i++)
       {
              if(i==d.location[0].length-1)
                  gd.setColor(Color.GREEN);
               gd.drawLine(d.location[0][d.Route[i]], d.location[1][d.Route[i]], d.location[0][d.Route[i+1]], d.location[1][d.Route[i+1]]);
       }
     }
   
}
