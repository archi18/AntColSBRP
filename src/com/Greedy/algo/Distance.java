/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Greedy.algo;




public class Distance extends Parameter
{

    // set #no_of_stopes to be visited

    void set_noOfStops(int no_of_stopes)
    {
       setNo_of_stopes(no_of_stopes+1);
    }



     // set #no_of_ants *K(k_ant)

    void setNoOfAntsK(int Ants)
    {
        setKAnts(Ants);
    }


    //set no of #Interation Cycles(I_cycles)

    void setNoOfCycles(int Cycles)
    {
        setICycles(Cycles);
    }


    //set #Algorihm parameter(q0) for selection of diversification or intensification

    void setIntensDiversQ0(double Q0)
    {
        setQ0(Q0);
    }


    // set Algorithm parameter #Beta value

    void setBetaValue(int Beta)
    {
       setBeta(Beta);
    }


    //set #pheromon evaporatrion coefficient *p

    void setPheromonEvaporationCoefficient(double PherEvopFact)
    {
        setP(PherEvopFact);
    }


    // set inital #pheromon level

    void setInitalPheromonT0(double Tau)
    {
        setT0(Tau);
    }



    // initialze the #size of all metrix to #no_of_stopes to be visited

    void intializeSizeOfMatrix()
    {
        int N=getNo_of_stopes();
        distance=new double[N][N];
        Neta=new double[N][N];
        Tau=new double[N][N];
        X=new int[N][N];
        location=new int[2][N];
        Visited=new int[N];
        Route=new int[N+1];
    }
    
   // set #location matrix with random value **later will be taken dynmicaly from user

   void set_Location_Matrix(int loc[][])
   {
         this.location=loc;
       
//       for(int i=0; i<location.length; i++)
//       {
//           for(int j=0 ;j<location[0].length; j++)
//           {
//                location[i][j]=(int)(Math.random()*1000);
//           }
//       }
   }


   // calculate #distance based on the location and also set #Hueristic matrix(neta _nij) values

   void calDistance()
   {
       for(int i=0; i<distance.length; i++)
       {
           for(int j=i ;j<distance.length; j++)
           {
             
             if(i==j)
                 distance[i][j]=0;
             else
             {
                 double distX=Math.pow(  (location[0][i] - location[0][j])  ,  2 );
                 double distY=Math.pow(  (location[1][i] - location[1][j])  ,  2 );
                 distance[i][j]=distance[j][i]=Math.sqrt((distX+distY));

                 Neta[i][j]=Neta[j][i]=1/distance[i][j];     //            setting hueristic value
             }


           }
       }
   }


   // set #Xij matrix

    void setX_Matrix()
     {
         for(int i=0; i<X.length; i++)
       {
           for(int j=0; j<X.length; j++)
           {
               X[i][j]=0;
           }

       }
      }


   // set Tau metrix with intial pheromon level

    void setTau_Metrix()
    {
        for(int i=0; i<Tau.length; i++)
           for(int j=0; j<Tau.length; j++)
               Tau[i][j]=t0;
    }


    // initailize the #Visited stopes metrix

    void setVisited_Metrix()
    {
        for(int i=0; i<Visited.length; i++)
           Visited[i]=0;
           
    }


    void setRoute_Metrix()
    {
        for(int i=0; i<Route.length; i++)
           Route[i]=-1;

    }


   // Display #location matrix

   void displayLocation()
   {
       for(int i=0; i<location.length; i++)
       {
           for(int j=0; j<location[0].length; j++)
           {
               System.out.print("\b"+location[i][j]);
           }

           System.out.print("\n");
       }
    }



    // Display #Distance matrix

    void displayDistance()
    {
       for(int i=0; i<distance.length; i++)
       {
           for(int j=0; j<distance.length; j++)
           {
               System.out.print("\b"+(float)distance[i][j]);
           }

          System.out.print("\n");
       }

        
     }


    // Display #Huieristic metrix

    void displayNeta()
    {
        for(int i=0; i<Neta.length; i++)
       {
           for(int j=0; j<Neta.length; j++)
           {
               System.out.print("\b"+(float)Neta[i][j]);
           }

          System.out.print("\n");
       }


     }


    // Display #Tau metrix

    void displayTauMetrix()
    {
        for(int i=0; i<Tau.length; i++)
       {
           for(int j=0; j<Tau.length; j++)
           {
               System.out.print("\b"+(float)Tau[i][j]);
           }

          System.out.print("\n");
       }


     }

    // Display No #Ants

    void displayAnts()
    {
        System.out.println("No of #ANTS "+getKAnts());
    }

    // Display No #Cycles

    void displayCycles()
    {
        System.out.println("No of #Cycles "+getICycles());
    }

    // Display  #Q0

    void displayQ0()
    {
        System.out.println("Value of #Q0 "+getQ0());
    }

    // Display #BETA value

    void displayBeta()
    {
        System.out.println("#BETA value is "+getBeta());
    }

    // Display #PheromonEvaporationCoefficient value

    void displayPheromonEvaporation()
    {
        System.out.println("#PHEROMON EVAPORATION COEFFICIENT value "+getP());
    }


    // Display Initial #Pheromon level

    void displayTau()
    {
        System.out.println("Intial #PHEROMON level "+getT0());
    }

}
