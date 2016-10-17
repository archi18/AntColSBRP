/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ant.algo;

import java.io.*;
import java.util.Arrays;

public class TestCode extends Distance
{
    public int K;                                   //No of #Ants
    public int I;                                   //No of #Cycles
    public int Stops;                               //No of #Stops
    public double q;                                //Selection Parameter #q
    public int currentStop;
    public int nextStop;
    public int N=getNo_of_stopes();
    public int SmallestRoute[];
    public double totalRouteDistance=0.0;
    public double totalSmallRouteDistance=0.0;
    public double deltaPheromon=0.0;
    public double smallestrouteDistanceForCvsFile=0.0;
    

    void getAntColonyOptimization()throws Exception
    {
        
                   
        N=getNo_of_stopes();
        SmallestRoute=new int[N+1];
        System.out.println("this method has been called "+N);
        int  routeLocation;                              // to track the route counter it always points to #currentStop
       

        for(I=1; I<ICycles; I++)
        {
            
            for(K=1; K<KAnts; K++)
            {

            routeLocation=0;
            Route[routeLocation++]=0;                        //Locate Ant at the School
            Visited[0]=1;                                 //Marked Visited as 1
            Stops=1;                                      //Marked Stop Count to 1
            currentStop=0;

                while(Stops<(N)){
                    q=(float)Math.random();

                    if(q<q0)
                    {      // this is diversification
                       nextStop=getDiversification(currentStop);     // find next stop by diversificaion

                   //     System.out.print("\n value of routeLocation "+routeLocation);
                       Route[routeLocation++]=nextStop;              // store nextstop in #Route
                   //    System.out.print("\n Current route \n");
                   //    display(Route);                             // dispaly content of #Route after every new stope is added to route


                  //     System.out.print("\n CurrentStop"+currentStop+ "\b nextStop="+nextStop);
                       X[currentStop][nextStop]=1;                   // marke age of visite node from #currentStop to #nextStop
                       Visited[nextStop]=1;
                       Stops++;
                       currentStop=nextStop;

                    }
                    else
                    {
                       //this is intensification
                        nextStop=getIntensification(currentStop);  // find next stop by intensification

                   //     System.out.print("\n value of routeLocation "+routeLocation);
                       Route[routeLocation++]=nextStop;
                    //   System.out.print("\n Current route \n");
                    //   display(Route);

                       X[currentStop][nextStop]=1;
                       Visited[nextStop]=1;
                       Stops++;
                       currentStop=nextStop;

                    }

              //    System.out.print("\n (Stope)="+Stops+"\b N="+N);
                }

 //                System.out.println("\nRoute for Iteration No="+I+"\b\b Ant No="+K+"\n ");
                Route[Route.length-1]=0;
 //               display(Route);
//                 System.out.println("\nContent of Visited before calling setVisiteg metrix");
//                 display(Visited);

               
                getSmallestRoute();
                
                totalRouteDistance=0.0;
                totalSmallRouteDistance=0.0;
                getLocalUpdatePheromon();
               

                setVisited_Metrix();

//                 System.out.println("\nContent of Visited after calling setVisiteg metrix");
//                 display(Visited);

                setX_Matrix();


            } // end of one ants

            newgetGlobleUpdatePheromon();
            

        }// end of one iterations

        for(int i=0; i<Route.length; i++) 
        {
             Route[i] = SmallestRoute[i];
        }

        double totalDist= 0.0;
        for(int i=0; i<Route.length-1; i++)
        {
          
          totalDist = totalDist + distance[Route[i]][Route[i+1]];
        }
        smallestrouteDistanceForCvsFile=totalDist;
   //     System.out.println("\n Smallest Distance covered "+totalDist);

   //     display(Route);
       
       

    }


    public void getLocalUpdatePheromon()
    {
        double deltaPheromon = getDeltaPheromon(Route);
        for(int i=0; i<Route.length-1; i++)
        {
          // Tau[Route[i]][Route[i+1]]  = ( (1 - p) * Tau[Route[i]][Route[i+1]] ) + (p * t0) ;
            Tau[Route[i]][Route[i+1]] =  Tau[Route[i]][Route[i+1]] + deltaPheromon; ;

        }
    }

    public void getGlobleUpdatePheromon()
    {
        for(int i=0; i<Route.length-1; i++)
        {
            //Tau[Route[i]][Route[i+1]]  = ( (1 - p) * Tau[Route[i]][Route[i+1]] ) + (p * Math.pow(totalSmallRouteDistance, -1)) ;
            Tau[Route[i]][Route[i+1]]  = ( (1 - p) * Tau[Route[i]][Route[i+1]] ) + (p * t0) ;
        }
    }

    public void newgetGlobleUpdatePheromon()
    {
        double deltaPheromon = getDeltaPheromon(SmallestRoute);
        for(int i=0; i<SmallestRoute.length-1; i++)
        {
            Tau[Route[i]][Route[i+1]]  = ( (1 - p) * Tau[Route[i]][Route[i+1]] ) + (p * deltaPheromon) ;
          //  Tau[Route[i]][Route[i+1]]  = ( (1 - p) * Tau[Route[i]][Route[i+1]] ) + (p * Math.pow(totalSmallRouteDistance, -1)) ;
           // Tau[SmallestRoute[i]][SmallestRoute[i+1]]  = ( (1 - p) * Tau[SmallestRoute[i]][SmallestRoute[i+1]] ) + (p * t0) ;
        }
    }

    public double getDeltaPheromon(int[] tempRoute){
        deltaPheromon=0;
        for(int i=0; i<tempRoute.length-2; i++)
        {
            deltaPheromon = deltaPheromon+ Neta[i][i+1] ;
        }
    //    System.out.println("\n delta pheromon "+deltaPheromon);
        return deltaPheromon;
    }


    public void getSmallestRoute()  // to find smallest rote between the Current #Route obtained and #Smallest Route till found
    {
                if(I==1 && K==1)   // executed only for one for Initialy deciding #smallest Route
                {
                    for(int i=0; i<Route.length; i++)
                    {
                       SmallestRoute[i] = Route[i];
                    }
                }

                for(int i=0; i<Route.length-1; i++)   // deciding #totaldistance of Current #Route
                {
                    totalRouteDistance = totalRouteDistance + distance[Route[i]][Route[i+1]];
                }

     //           System.out.println("\n TotalRouteDistance= "+totalRouteDistance);

                for(int i=0; i<SmallestRoute.length-1; i++) // deciding #totaldistance of  #SmallestRoute till found
                {
                    totalSmallRouteDistance = totalSmallRouteDistance + distance[SmallestRoute[i]][SmallestRoute[i+1]];
                }

    //            System.out.println("\n TotalSmallRouteDistance= "+totalSmallRouteDistance);

                if(totalRouteDistance < totalSmallRouteDistance)
                {
                    for(int i=0; i<Route.length; i++)
                    {
                       SmallestRoute[i] = Route[i];
                    }
                }
    }
     

    public int getDiversification(int i)        // i= current stop
    {
        int S=-1;
        double arg_max=0;

        for(int j=1; j<N; j++)
        {
            if(Visited[j]!=1)
            {
                double  temp= (float) ( Tau[i][j]  *  Math.pow(Neta[i][j], Beta) );    //T[i][j]*(N[i][j])^B
             //   System.out.println("\n the temp for "+i+"to"+j+" = "+temp);
                if(temp>arg_max)
                {
                    arg_max=temp;
                    S=j;
                }
            }
        }

      return S;
    }


     public int getIntensification(int i)         // i is current stop
    {
        int Z[]=new int[N-Stops];
        double Pk[][]=new double[2][N-Stops];
        int S=-1;

        for(int k=0; k<Pk[0].length; k++)
            Pk[0][k]=Pk[1][k]=-1;


        double totalProbabilty=0;

         for(int j=1; j<N; j++)      // Find total #sample space
        {
            if(Visited[j]!=1)
            {
              totalProbabilty += Tau[i][j]  *  Math.pow(Neta[i][j], Beta);
            }
        }


        for(int j=1,k=0; j<N; j++)      // find indivisual probability from #Current stop(i)* to all #Unvisited stop(j)*
        {
            if(Visited[j]!=1)
            {
                Pk[1][k]=(float) (( Tau[i][j]  *  Math.pow(Neta[i][j], Beta)  )  /  ( totalProbabilty ));  // Probabilty from going #i to #j
                Pk[0][k]=j;                                                                       // Storing the relative stop with its prob
                k++;

            }
        }

     //   System.out.println("Before Sorting\n");
      //  display(Pk);


        for(int k=0; k<Pk[0].length-1; k++)             // Sort the elements in Ascnding order with its #Stop
         {
             for(int j=0; j<Pk[0].length-1; j++)
             {
                 if(Pk[1][j]>Pk[1][j+1])
                 {
                     double temp=Pk[1][j];
                     Pk[1][j]=Pk[1][j+1];
                     Pk[1][j+1]=temp;

                     temp=Pk[0][j];
                     Pk[0][j]=Pk[0][j+1];
                     Pk[0][j+1]=temp;

                 }
             }
        }



     //   System.out.println("After Sorting\n");

        double tempRandom=Math.random();
    //    display(Pk);

   //    System.out.println("Random No "+tempRandom);
      S=(int)(Pk[0][0]);

         for(int j=0; tempRandom > Pk[1][j]; j++)    //  finding the stop by finding #tempRandom near to #Probability
        {
            S=(int)Pk[0][j];
         //   System.out.println("\n Inside for loop Value of S"+S);
            if(j==Pk[0].length-1)
                break;
        }

    //   System.out.println("outside for loop Value of S"+S);

        return S;

    }


    


      public void display(double x[][])
     {
         for(int i=0; i<x.length; i++)
         {
             for(int j=0; j<x[0].length; j++)
             {
                 System.out.print("\b\b "+(float)x[i][j]);
             }
             System.out.print("\n");
         }
     }


      public void display(int x[])
     {
          System.out.println();
         for(int i=0; i<x.length; i++)
         {
                 System.out.print("\b\b "+x[i]);
         }
     }



}
