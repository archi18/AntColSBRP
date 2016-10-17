/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Greedy.algo;


public class Parameter
{
    public  int KAnts;                //no of ants
    public  int ICycles;             //no if iteration
    public  int Beta;                // beta parameter
    public  double q0 ;              // algorithm parameter
    public  double p;                //evapration coefficient
    public  double t0;               //initial pheromon level


    public  int location[][]=new int[2][];      // X & y cordinates of screen

    public double Neta[][];
    public double Tau[][];
    public double distance[][];
    public int X[][];                         // visited route from i to j


    public int[] Visited;                     // Stopes Visited or Not **If Visited then value will be 1 else 0
    public int[] Route;                       // #Sequence in which #Route is selected or #Stopes are visited
    public int no_of_stopes;
    public double Lmc;                          // best route

    public int getBeta() {
        return Beta;
    }

    public int getICycles() {
        return ICycles;
    }

    public int getKAnts() {
        return KAnts;
    }

    public double getLmc() {
        return Lmc;
    }

    public int getNo_of_stopes() {
        return no_of_stopes;
    }

    public double getP() {
        return p;
    }

    public double getQ0() {
        return q0;
    }

    public double getT0() {
        return t0;
    }

    public void setBeta(int Beta) {
        this.Beta = Beta;
    }

    public void setICycles(int ICycles) {
        this.ICycles = ICycles;
    }

    public void setKAnts(int KAnts) {
        this.KAnts = KAnts;
    }

    public void setLmc(double Lmc) {
        this.Lmc = Lmc;
    }

    public void setNo_of_stopes(int no_of_stopes) {
        this.no_of_stopes = no_of_stopes;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setQ0(double q0) {
        this.q0 = q0;
    }

    public void setT0(double t0) {
        this.t0 = t0;
    }

    public int[] getRoute() {
        return Route;
    }


}
