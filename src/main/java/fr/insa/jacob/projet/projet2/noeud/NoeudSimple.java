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

    public NoeudSimple(int idNoeud) {
        super(idNoeud);
    }

    /*    public Point getNoeudS() {
    return noeudS;
    }*/

    @Override
    public double getAbsNoeud() {
        return this.ne.getPx();
    }

    @Override
    public double getOrdNoeud() {
        return this.ne.getPy();
    }
    
    public static void test(){
        
    }
}
