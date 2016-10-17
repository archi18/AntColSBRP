 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ant.algo;

import com.common.util.AlgorithmParameter;
import com.common.util.DynamicCluster;
import com.common.util.ReadStops;
import com.common.util.STOPLOC;
import com.common.util.VectorStops;
import com.common.util.VerticalCluster;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Archi
 */
public class Main 
{
   TestCode d;
   static ArrayList<TestCode> list=new ArrayList<TestCode>();
   int height=0,width=0;
   static int Xmin,Xmax,Ymin,Ymax,Xmid,Ymid;
   File file=null;
   double totalDistTrav=0.0;
   int totalSttudent=0;

   public static int ANT=50;
   public static int BETA=5;
   public static int CYCLE=50;
   public static double INITDIV=0.9;
   public static double PHEREVAP=0.0001;
   public static int BUS_CAPACITY=150;

    
    public static void main(String[] args)throws IOException, Exception
    {
        ReadStops readStops=new ReadStops();
        int stopscount= readStops.getStopCount();
        BUS_CAPACITY=stopscount;
        System.out.println("Bus Count AntCol------> "+BUS_CAPACITY);
        AlgorithmParameter ap=new AlgorithmParameter();
        ap.setAnts(ANT);
        ap.setBeta(BETA);
        ap.setCycles(CYCLE);
        ap.setIntdiv(INITDIV);
        ap.setPherevp(PHEREVAP);
        ap.setBusCapacity(BUS_CAPACITY);
        testPara(ANT,CYCLE,BETA, INITDIV, PHEREVAP);
        new Main().getRouteByACO(ap);
    }

    public void getAntColonyOPtimization(AlgorithmParameter ap) throws Exception{
        new Main().getRouteByACO(ap);
    }

    public static void testPara(int ant, int cycle, int beta, double intdiv, double pherEvap){
     System.out.println("ants : "+ant+"cycle : "+cycle+" beta :"+beta+" intdiver : "+intdiv+" pher evep : "+pherEvap);
    }
    
    void displtRut(int x[][])
    {
        System.out.println("content of route " );
        for(int i=0; i<x[0].length; i++)
        {
           
            System.out.println("X= "+x[0][i]+" Y= "+x[1][i]);
        }
    }
    
    public void getHeightWidth()
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

        Xmid=(Xmax-Xmin)/2  + Xmin;
        Ymid=(Ymax-Ymin)/2  + Ymin;
        
        
    }
    
    TestCode initializingParameter(AlgorithmParameter para,int stops,int location[][])throws Exception
    {
        
       d=new TestCode();

        d.setNo_of_stopes(stops);                                      // set no of #stopes
        d.setNoOfAntsK(para.getAnts());                                          // Set no of #Ants
        d.setNoOfCycles(para.getCycles());                                        // set no of #Cycles
        d.setIntensDiversQ0(para.getIntdiv());                                    // Set #Q0 value 0.9
        d.setBetaValue(para.getBeta());                                           // Set #Beta Value 5
        d.setPheromonEvaporationCoefficient(para.getPherevp());                  // Set #p value Pheromon Evaporation Factor 0.5
        d.setInitalPheromonT0(0.0001);                               // set #T0 value Inial pheromon Level
        d.intializeSizeOfMatrix();                                   // initailize matrix size


        d.set_Location_Matrix(location);
        System.out.println("location matrix is as follows \n");
        d.displayLocation();

        d.calDistance();               // Calculate distance based on Location
        System.out.println("distance matrix is as follows \n");
        d.displayDistance();

         System.out.println("hueristic metrix matrix is as follows \n");
         d.displayNeta();


         d.setTau_Metrix();                                          // Initalize Initialize Pheromon Level
         d.setX_Matrix();                                            // Initialize X metrix
         d.setRoute_Metrix();

         
         d.displayAnts();
         d.displayBeta();
         d.displayCycles();
         d.displayQ0();
         d.displayPheromonEvaporation();
         d.displayTau();
         
         

         //********************************************************//

//         System.out.println("the next stop to be visited "+t.getIntensification(5));

         AntColonyAlgorithm ant=new AntColonyAlgorithm();
         d.getAntColonyOptimization();
         
    //     list.add(d);
        

         //********************************************************//

     //   showResult(list);     // display points in java applet
       
        return d;
    }


    void showResult(ArrayList<TestCode> list)
    {
        NewApplet applet=new NewApplet(list);
        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setVisible(true);
    }

    public void runx(ArrayList<TestCode> list) {
                NewApplet applet=new NewApplet(list);
                JFrame frame = new JFrame();
                JScrollPane scrollPane = new JScrollPane(applet);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                frame.add(scrollPane);
                frame.getContentPane().add(applet);
                frame.setVisible(true);
                System.out.println("exiting");
            }

     public synchronized  void getRouteByACO(AlgorithmParameter para)throws Exception{
         int busNO=1;
         Date date =new Date();
         file=new File("E:/AntColAlgoOutput/AntColAlgo_"+date.getTime()+".csv");
         if(file.exists())
             System.out.println("file exsist"+file.getPath());
         BufferedWriter writeToFile=new BufferedWriter(new FileWriter(file,true));
         
         TestCode t;
         Main call=new Main();
        STOPLOC sl=new STOPLOC();
        DynamicCluster dc=new DynamicCluster(para);
         //VerticalCluster dc=new VerticalCluster(para);
         call.getHeightWidth();

         System.out.println("\n XMID= "+Xmid +" YMID= "+Ymid);

      VectorStops stop;
      ArrayList<VectorStops> ls;
      ls=dc.getGroupStops();
      Iterator<VectorStops> it=ls.iterator();
      writeToFile.write("No Of Cycle ,"+para.getCycles()+", No of Ants ,"+para.getAnts()+", Beta ,"+para.getBeta()+",Algorithm Parameter,"+para.getIntdiv()+",Pheromon Evaporation,"+para.getPherevp());
      writeToFile.write(",");
      writeToFile.newLine();
      writeToFile.write(",");
      writeToFile.newLine();
      writeToFile.write("Bus, Distance Travel , Stops Visited");
      writeToFile.newLine();
      while(it.hasNext())
      {
          stop=it.next();
          System.out.println("New Route");
          int [][]loc=stop.getStopLocation();
          loc[0][0]=Xmid;
          loc[1][0]=Ymid;

          System.out.println(" The length of "+loc.length+" second "+loc[0].length);

          call.displtRut(loc);

          t=call.initializingParameter(para,stop.getStopLengh(),loc);
          //writeToFile.write(t.smallestrouteDistanceForCvsFile+",");
          totalDistTrav = totalDistTrav+ t.smallestrouteDistanceForCvsFile;
          totalSttudent = totalSttudent + t.getNo_of_stopes();
          writeToFile.append("Bus No "+new Integer(busNO).toString()+","+new Double(t.smallestrouteDistanceForCvsFile).toString()+","+new Integer(t.getNo_of_stopes()).toString());
          writeToFile.newLine();
          list.add(t);
          busNO++;
         
      }

      it=null;
           writeToFile.append("Total,"+new Double(totalDistTrav).toString()+","+new Integer(totalSttudent).toString());
           writeToFile.newLine();
           writeToFile.newLine();
           writeToFile.newLine();
           writeToFile.newLine();
           writeToFile.close();
           call.runx(list);

    }


    
}
