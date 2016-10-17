/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadStopesFromFile
{

        int StopLocation[][];
        VectorStops tempStop;
        ReadStops read=new ReadStops();  

     int[][] getStops()throws IOException
     {
         

           StopLocation=read.readStops();
           display(StopLocation);

         return StopLocation;
     }


     static void  display(int loc[][])
     {
         for(int i=0; i<loc[0].length; i++)
         {
             System.out.println("X= "+loc[0][i]+"\t Y= "+loc[1][i]);
         }
         
     }
}
