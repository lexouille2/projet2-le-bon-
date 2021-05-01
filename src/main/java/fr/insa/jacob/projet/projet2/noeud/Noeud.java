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
public abstract class Noeud{
    
    private int idNoeud;
    Point ne;

    public Noeud(int idNoeud) {
        this.idNoeud = idNoeud;
    }
    
    public abstract double getAbsNoeud();
    
    public abstract double getOrdNoeud();

}

