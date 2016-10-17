/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Archi
 */
public class NewApplet extends Applet {

    Graphics2D gd;
    ArrayList<VectorStops> list;
    VectorStops stop;
    
    int height, width;
    int Xmin,Xmax,Ymin,Ymax,Xmid,Ymid;
    static int c=1;
    
    
     NewApplet(ArrayList<VectorStops> list) {
       this.list=list;
    }
  

     public void paint(Graphics g){
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
       Xmid=(Xmax-Xmin)/2  + Xmin;
       Ymid=(Ymax-Ymin)/2  + Ymin;        
//       gd.drawLine(Xmid, Ymin,Xmid , Ymax);
//       gd.drawLine(Xmin, Ymid,Xmax , Ymid);  
       Iterator<VectorStops> it=list.iterator();
       while(it.hasNext()){
           stop=it.next();
           newPaint(stop);
       }
    }
     
     void newPaint(VectorStops stop){
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
         gd.setPaintMode();
         int[][] location=stop.getStopLocation();
         for(int i=0; i<location[0].length; i++){
             gd.fillOval(location[0][i], location[1][i],7,7);
         }
//        for(int i=0; i<location[0].length-1; i++){
//             gd.drawLine(location[0][i], location[1][i], location[0][i+1], location[1][i+1]);
//        }
     }
   
}
