/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.barre;

import fr.insa.jacob.projet.projet2.noeud.Noeud;

/**
 *
 * @author PC
 */
public class Barre {
    
    private int idBarre;
    private Noeud n1, n2;
    private TypeBarre type;
    
    public Barre(int idBarre, Noeud n1, Noeud n2, TypeBarre type){
        
        this.idBarre = idBarre;
        this.n1 = n1;
        this.n2 = n2;
        this.type = type;
    }
    
    
}
