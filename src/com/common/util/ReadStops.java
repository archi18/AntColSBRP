 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;

import java.io.*;


public class ReadStops
{
    VectorStops storeStops;
    File file=null;
    int tempStop[][];
    
    public int[][] readStops()throws IOException
    {
         storeStops = new VectorStops();
         file=new File("E:/Stopes.txt");
         if(file.exists())
             System.out.println("file exsist"+file.getPath());
          BufferedReader input=new BufferedReader(new FileReader(file));
          int readcount=0;
          while(file.canRead())
          {
            String s=input.readLine();
            System.out.println(" Count "+(++readcount)+" string "+s );
            s=s.trim();
            if(s.length()==0 || s==null)
                break;

            if(s.charAt(0)>='a')
                continue;

//          System.out.println("Strinh"+s);

            int firstIndex =s.indexOf(" ");
            int lastIndex =s.lastIndexOf(" ");

  //        System.out.println("\n First index "+firstIndex+"Last index "+lastIndex);

            int X=getNumber(s.substring(firstIndex,lastIndex));
            int Y=getNumber(s.substring(lastIndex, s.length()));


         //   System.out.println("X = "+X+" Y = "+Y);
             storeStops.storeStopeXYCordinate(X, Y);


          }
              input.close();
             
        //      storeStops.displayStops();
            storeStops.populateStopArray();
            tempStop=storeStops.getStopLocation();

        return tempStop;
    }

    public int getStopCount()throws IOException
    {
         storeStops = new VectorStops();
         file=new File("E:/Stopes.txt");
         if(file.exists())
             System.out.println("Counting stop -------->file exsist"+file.getPath());
          BufferedReader input=new BufferedReader(new FileReader(file));
          int readcount=0;
          while(file.canRead())
          {
            String s=input.readLine();
            System.out.println("Counting stop --------> Count "+(++readcount)+" string "+s );
            s=s.trim();
            if(s.length()==0 || s==null)
                break;

            if(s.charAt(0)>='a')
                continue;

          }
              input.close();

        return readcount;
    }


    static int getNumber(String s)
    {
        if(s==null)
            return 0;
        s=s.trim();
        int num=Integer.parseInt(s);
        return num;
    }
}
