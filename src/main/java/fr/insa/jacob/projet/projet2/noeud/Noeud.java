/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;
    
import fr.insa.jacob.projet.projet2.terrain.GroupeTT;
import fr.insa.jacob.projet.projet2.terrain.Terrain;

    /**
 *
 * @author PC
 */
public class Noeud{ //abstract
    
    private int idNoeud;
    protected Point ne;

    public Noeud(int idNoeud, Point ne) {
        this.idNoeud = idNoeud;
        this.ne = ne;
    }
    
    //public abstract double getAbsNoeud();
    
    //public abstract double getOrdNoeud();
    
    public double getAbsNoeud(){
        return this.ne.getPx();
    }
    
    public double getOrdNoeud(){
        return this.ne.getPy();
    }

}

