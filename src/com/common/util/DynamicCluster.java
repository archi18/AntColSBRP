/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common.util;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class DynamicCluster {
    static int [][]stopLoc;          //stores #stop location
    double [][]distance;             //#distance between all stopes
    int []cluster;                   //stores stop locaton of different cluster points
    static  int []visited;           //check whether #stops #visited or not
    int eachBusCapacity[];
    int height=0;
    int width=0;
    int Xmin;
    int Xmax;
    int Ymin;
    int Ymax;
    int Xmid;
    int Ymid;
    int diagonal;
    int busCapacity;
    VectorStops clustStop;           // to store #position of stop in respecive cluster
    ArrayList stopList;
    AlgorithmParameter para;

    public DynamicCluster(AlgorithmParameter para) {
        this.para = para;
    }

    public DynamicCluster() {
    }
    
    public static void main(String args[])throws IOException{
        DynamicCluster clust=new DynamicCluster();
        ArrayList list=new ArrayList<VectorStops>();
        clust.getScreenSize();
        clust.getStopLoc();
        clust.calDistance(stopLoc);

        int bus=stopLoc.length / 50;
        if(bus==0)
            bus=1;
        System.out.println("\nno of sops "+stopLoc.length+" No of Buses "+bus);
        for(int i=0; i<bus; i++){
            int pivot=0;
            for(int stp=0; stp<visited.length; stp++)
            {
                if(visited[stp]!=1){
                    pivot=stp;
                    break;
                }
            }
            visited[pivot]=1;
            clust.createCluster(pivot,50,list);
        }
        clust.showResult(list);
    }

    void getStopLoc()throws IOException{
         ReadStops read=new ReadStops();
         this.stopLoc=read.readStops();
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
       this.Ymax=height;
       this.Xmid=(Xmax-Xmin)/2 ;
       this.Ymid=(Ymax-Ymin)/2 ;
    }

   
    void calDistance(int location[][]){
        for(int i=0; i<distance.length; i++)
        {
           for(int j=i ;j<distance.length; j++)
           {
             if(i==j)
                 distance[i][j]=0;
             else
             {
                 double distX=Math.pow(  (location[0][i] - location[0][j])  ,  2 );   //(x2-x2)^2
                 double distY=Math.pow(  (location[1][i] - location[1][j])  ,  2 );   //(y2-y1)^2
                 distance[i][j]=distance[j][i]=Math.sqrt((distX+distY));
             }
           }
       }
       // this.displayDistance(distance);
    }
    
    void intializeMetrix(){
        int noofstop=stopLoc[0].length;
        distance=new double[noofstop][noofstop];  //initialize size of #distance metrix
        visited=new int[noofstop];                //initialize size of #visited metrix
    }

    void createCluster(int pivPoint,int busCapacity,ArrayList stopList){
        clustStop=new VectorStops();
        int pivotePoint=pivPoint;
        int count=0;
        for(int i=1; i<diagonal; i++)
        {
            for(int stop=0; stop<stopLoc[0].length; stop++){
                if((pivotePoint != stop) && (visited[stop] !=1) && (count!=busCapacity-1) && (distance[stop][pivotePoint] <= i)){
                    clustStop.storeStopeXYCordinate(stopLoc[0][stop],stopLoc[1][stop]);
                    visited[stop]=1;
                    count++;
                }
            }
        }
        clustStop.storeStopeXYCordinate(stopLoc[0][pivPoint], stopLoc[1][pivPoint]);
        clustStop.populateStopArray();
        displayStops(clustStop.getStopLocation());
        stopList.add(clustStop);
    }

     void displayStops(int [][]x){
         System.out.println("the legth of stops"+x[0].length);
         for(int i=0; i<x[0].length; i++)
             System.out.println(x[0][i]+" \t"+ x[1][i]);
    }
     
    void displayDistance(double [][]distance){
       for(int i=0; i<distance.length; i++)
       {
           for(int j=0; j<distance.length; j++)
           {
               System.out.print("\b"+(float)distance[i][j]);
           }
          System.out.print("\n");
       }
    }

    void dispalyVisited(){
        for(int i=0; i<visited.length;i++)
            System.out.println("Visisited["+i+"]= "+visited[i]);
    }
    
    void showResult(ArrayList<VectorStops> list){
        NewApplet applet=new NewApplet(list);
        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setVisible(true);
    }

    public synchronized  ArrayList<VectorStops> getGroupStops()throws IOException{
        DynamicCluster clust=new DynamicCluster();
        stopList=new ArrayList<VectorStops>();
        clust.getScreenSize();
        clust.getStopLoc();
        clust.calDistance(stopLoc);
        eachBusCapacity= getNoOfBuses();
        System.out.println("\nno of sops "+stopLoc.length+" No of Buses "+eachBusCapacity.length);
        for(int i=0; i<eachBusCapacity.length; i++){
            int pivot=0;
            for(int stp=0; stp<visited.length; stp++)
            {
                if(visited[stp]!=1){
                    pivot=stp;
                    break;
                }
            }
            visited[pivot]=1;
            clust.createCluster(pivot,eachBusCapacity[i],stopList);
        }
        return stopList;
    }

    int[] getNoOfBuses(){
        int eachBusCapacity[];
        busCapacity = para.getBusCapacity();
        int noOfBus=stopLoc[0].length / busCapacity;
        int remaining = stopLoc[0].length % busCapacity;
        if((remaining >= busCapacity/2))
            noOfBus++;

        System.out.println("No of Buses "+noOfBus);
        eachBusCapacity=new int[noOfBus];
        int busNo=0;
        for(int i=0; i<stopLoc[0].length; i++){
            eachBusCapacity[busNo++]++;
            if(busNo==noOfBus)
                busNo=0;
        }
        return eachBusCapacity;
    }

    void tempRoute(){
        for(int i=0; i<10; i++){
            int pivot=0;
            for(int stp=0; stp<visited.length; stp++)
            {
                if(visited[stp]!=1){
                    pivot=stp;
                    break;
                }
            }
            visited[pivot]=1;
        }
    }

    void sharmaClustering(int Xmin, int Ymin, int Xmax, int Ymax){
        int Xmid = ((Xmax - Xmin) / 2)+ Xmin;
        int Ymid = ((Ymax - Ymin) / 2)+ Ymin;
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

}
