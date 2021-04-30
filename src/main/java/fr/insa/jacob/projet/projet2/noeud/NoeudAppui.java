/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;

import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;

/**
 *
 * @author PC
 */
public class NoeudAppui extends Noeud {
    
    private static TriangleTerrain TT;
    private int num;
    private Point debutSeg;
    private Point finSeg;
    private Point pos;
    //private double alpha;

    public NoeudAppui(int idNoeud, TriangleTerrain TT, int num, Point pos) {
        super(idNoeud);
        this.TT = TT;
        this.num = num;
        this.pos = pos;
    }
    
    public Point calcDebutSeg(){
        if (this.num == 0){
            this.debutSeg = this.TT.getP1();
        }
        else if (this.num == 1){
           this.debutSeg = this.TT.getP2(); 
        }
        else if (this.num == 2){
            this.debutSeg = this.TT.getP3();
        }
        else{
            throw new Error("Un triangle n'a que trois sommets : entrer 0, 1 ou 2.");
        }
        return this.debutSeg;
    }
    
    public Point calcFinSeg(){
        int num2;
        if ((this.num >= 0) && (this.num <= 2)){
            num2 = (this.num + 1)%3;
        }
        else{
            throw new Error("Un triangle n'a que trois sommets : entrer 0, 1 ou 2.");
        }
        if (num2 == 0){
            this.finSeg = this.TT.getP1();
        }
        else if (num2 == 1){
            this.finSeg = this.TT.getP2(); 
        }
        else if (num2 == 2){
            this.finSeg = this.TT.getP3();
        }
        return this.finSeg;
    } 
    
    //public Point calcPos(double alpha, Point debutSeg, Point finSeg){
        //this.pos.getPx() = (1 - alpha) * this.finSeg.getPx() + this.debutSeg.getPx() * alpha;
        //pos.getPx() = ((1 - alpha) * finSeg.getPx()) + (alpha * debutSeg.getPx()));
        //return pos;
    //}
    
    public static void test(){
        Point p1, p2, p3;
        p1 = new Point (0,0);
        p2 = new Point (0,1);
        p3 = new Point (1,0);
        TriangleTerrain TT = new TriangleTerrain (1, p1, p2, p3);
        Point pos = new Point ();
        NoeudAppui n1 = new NoeudAppui(2, TT, 1, pos);
        double alpha = 0.2;
        n1.calcDebutSeg();
        n1.calcFinSeg();
        System.out.println(n1.debutSeg);
        System.out.println(n1.finSeg);
        //n1.pos.getPx() = (1 - alpha) * n1.finSeg.getPx() + n1.debutSeg.getPx() * alpha;
        //pos.getPy() = ((1 - alpha) * finSeg.getPy()) + (alpha * debutSeg.getPy()));
    }

    
    
    public static void main(String[] args) {
        test();
    }
    
}