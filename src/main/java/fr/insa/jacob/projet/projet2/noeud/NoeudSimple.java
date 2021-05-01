/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;

import fr.insa.jacob.projet.projet2.terrain.GroupeTT;

/**
 *
 * @author PC
 */
public class NoeudSimple extends Noeud{

    public NoeudSimple(int idNoeud, Point ne) {
        super(idNoeud, ne);
    }

    /*    public Point getNoeudS() {
    return noeudS;
    }*/

    /*@Override
    public double getAbsNoeud() {
    return this.ne.getPx();
    }
    
    @Override
    public double getOrdNoeud() {
    return this.ne.getPy();
    }*/
    
    public static void test(){
        Point pouain = new Point(1,0);
        NoeudSimple neu = new NoeudSimple(3, pouain);
        System.out.println(neu.getAbsNoeud());
        System.out.println(neu.getOrdNoeud());
    }
    
    public static void main(String[] args) {
        test();
    }
    
}
