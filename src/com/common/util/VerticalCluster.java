/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;


public class VerticalCluster{
    static int [][]stopLoc;          //stores #stop location
    double [][]distance;             //#distance between all stopes
    int []cluster;                   //stores stop locaton of different cluster points
    static  int []visited;           //check whether #stops #visited or not
    int height=0;
    int width=0;
    int Xmin;
    int Xmax;
    int Ymin;
    int Ymax;
    int Xmid;
    int Ymid;
    int diagonal;
    VectorStops clustStop;           // to store #position of stop in respecive cluster
    static ArrayList list;
    AlgorithmParameter para;
    static int busCapacity;

    public VerticalCluster(AlgorithmParameter para) {
        this.para = para;
    }

    public VerticalCluster() {
    }
    
     
    public static void main(String args[])throws IOException{
        VerticalCluster clust=new VerticalCluster();
        busCapacity=50;
        list=new ArrayList<VectorStops>();
        clust.getScreenSize();
        clust.getStopLoc();
        clust.createClust();
        clust.showResult(list);
    }

    public ArrayList<VectorStops> getGroupStops()throws IOException{
        VerticalCluster clust=new VerticalCluster();
        list=new ArrayList<VectorStops>();
        clust.getScreenSize();
        clust.getStopLoc();
        busCapacity=para.getBusCapacity();
        clust.createClust();
        return list;
    }


    void createClust(){
       this.sharmaClustering(Xmin, Ymin, Xmax, Ymax);
    }

     void getStopLoc()throws IOException{
         ReadStops read=new ReadStops();
         this.stopLoc=read.readStops();
         System.out.println("length of stops "+stopLoc[0].length);
         this.intializeMetrix();
         System.out.println(" The stopes are as follows "+stopLoc[0].length);
        // this.displayStops(stopLoc);
    }


    void getScreenSize(){
       GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
       Rectangle bounds = env.getMaximumWindowBounds();
       System.out.println(bounds.getMaxX()+" "+bounds.getMaxY());
       this.height=(int) bounds.getMaxY();
       this.width=(int)bounds.getMaxX();
       this.diagonal=(int) Math.sqrt(((height*height)+(width*width)));
       System.out.println("Height= "+height+" width= "+width+" Diagonal = "+diagonal);
       this.Xmin=0;
       this.Ymin=0;
       this.Xmax=width;
       this.Ymax=1000;
    }

    void intializeMetrix(){
        int noofstop=stopLoc[0].length;
        distance=new double[noofstop][noofstop];  //initialize size of #distance metrix
        visited=new int[noofstop];                //initialize size of #visited metrix
    }

    void sharmaClustering(int Xmin, int Ymin, int Xmax, int Ymax){
        int count= getStopCount(Xmin, Ymin, Xmax, Ymax);
        if(count > (busCapacity+10)){
            int Xmid = ((Xmax - Xmin) / 2)+ Xmin;
          //  int Ymid = ((Ymax - Ymin) / 2)+ this.Ymin;
            System.out.println("Xmin= "+Xmin+" Ymin= "+Ymin+" Xmax= "+Xmax+" Ymax= "+Ymax+" Xmid= "+Xmid);
            sharmaClustering(Xmin, Ymin, Xmid, Ymax);
            sharmaClustering(Xmid, Ymin, Xmax, Ymax);
        }
        else{
            System.out.println("Xmin= "+Xmin+" Ymin= "+Ymin+" Xmax= "+Xmax+" Ymax= "+Ymax);
            createCluster(Xmin, Ymin, Xmax, Ymax);
        }
        
    }

    int getStopCount(int Xmin, int Ymin, int Xmax, int Ymax){
        int count=0;
        for(int i=0; i<stopLoc[0].length; i++){
            int x=stopLoc[0][i];
            int y=stopLoc[1][i];
            if((x >= Xmin) && (y >= Ymin) && (x < Xmax) && (y < Ymax))
                count++;
        }
        return count;
    }

    void createCluster(int Xmin, int Ymin, int Xmax, int Ymax){
        clustStop=new VectorStops();
        for(int stop=0; stop<stopLoc[0].length; stop++){
            int x=stopLoc[0][stop];
            int y=stopLoc[1][stop];
            if((x >= Xmin) && (y >= Ymin) && (x < Xmax) && (y < Ymax))
                clustStop.storeStopeXYCordinate(stopLoc[0][stop],stopLoc[1][stop]);
        }
        
        clustStop.populateStopArray();
        displayStops(clustStop.getStopLocation());
        if(clustStop.getStopLengh() !=0 )
        list.add(clustStop);
    }

     void displayStops(int [][]x){
         System.out.println("the legth of stops"+x[0].length);
         for(int i=0; i<x[0].length; i++)
             System.out.println(x[0][i]+" \t"+ x[1][i]);
    }

    void showResult(ArrayList<VectorStops> list){
        NewApplet applet=new NewApplet(list);
        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setVisible(true);
    }

}
