/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common.util;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;


public class STOPLOC 
{
    int height=0,width=0;
    int Xmin,Xmax,Ymin,Ymax,Xmid,Ymid;
    ReadStopesFromFile readstop;
    VectorStops r1,r2,r3,r4;
    int stopLoc[][];
    ArrayList<VectorStops> list;
    
    void initialize()
    {
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

       readstop=new ReadStopesFromFile();
       try{
             stopLoc=readstop.getStops();
        }catch(Exception e)
       {
            System.out.println(" the error "+e.getMessage());
        }
    }
    
    
    
   public ArrayList<VectorStops> getRoute()
    {
       this.initialize();
       
       Xmin=0;
       Ymin=0;
       Xmax=width;
       Ymax=height;

        int Xmid=(Xmax-Xmin)/2  + Xmin;
        int Ymid=(Ymax-Ymin)/2  + Ymin;
       
        
        
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
        
        return list;
    }
    
}
