/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;
import java.util.Iterator;
import java.util.Vector;

public class VectorStops
{

    Vector Xcord=new Vector();
    Vector Ycord=new Vector();
    int stopLocation[][];

   public void storeStopeXYCordinate(int x, int y)
   {
       Xcord.add(x);
       Ycord.add(y);
   }

   void displayStops()
   {
       System.out.println(" No of Stopes "+Xcord.size());
       Iterator X =Xcord.iterator();
       Iterator Y =Ycord.iterator();

        while(X.hasNext() && Y.hasNext())
        {
            Integer x=(Integer) X.next();
            int Xcor=Integer.valueOf(x);

            Integer y=(Integer) Y.next();
            int Ycor=Integer.valueOf(y);

            System.out.println("X= "+x+"   Y= "+y);
        }
   }


   public void populateStopArray()
   {
       stopLocation=new int[2][Xcord.size()];
       Iterator X =Xcord.iterator();
       Iterator Y =Ycord.iterator();

       for(int i=0; X.hasNext() && Y.hasNext(); i++)
       {
           Integer x=(Integer) X.next();
            int Xcor=Integer.valueOf(x);

            stopLocation[0][i]=Xcor;

            Integer y=(Integer) Y.next();
            int Ycor=Integer.valueOf(y);

            stopLocation[1][i]=Ycor;
       }

   }

    public int[][] getStopLocation()
    {
        return stopLocation;
    }
    public int getStopLengh()
    {
        return stopLocation[0].length;
    }
      
}
