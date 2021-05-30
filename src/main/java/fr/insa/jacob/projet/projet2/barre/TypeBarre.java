/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.barre;

import javafx.scene.paint.Color;

/**
 *
 * @author PC
 */

public class TypeBarre{
    
    private int idType;
    private double cout;
    private double lmin;
    private double lmax;
    private double rmaxt; //résistance max à la tension
    private double rmaxc; //résistance max à la compression
    private Color col;
    
    public TypeBarre(int idType, double cout, double lmin, double lmax, double rmaxt, double rmaxc, Color col){

        this.idType = idType;
        this.lmin = lmin;
        this.cout = cout;
        this.lmax = lmax;
        this.rmaxt = rmaxt;
        this.rmaxc = rmaxc;
        this.col = col;
    }
    
    public int getIdType(){
        return this.idType;
    }
    
    public double getCout(){
        return this.cout;
    }
        
    public double getLmin(){
        return this.lmin;
    }
            
    public double getLmax(){
        return this.lmax;
    }
        
    public double getRmaxt(){
        return this.rmaxt;
    }
            
    public double getRmaxc(){
        return this.rmaxc;
    }

    public Color getCol() {
        return col;
    }
    
    @Override
    public String toString() {
        return "TypeBarre{" + "idType=" + idType + ", cout=" + cout + ", lmin=" + lmin + ", lmax=" + lmax + ", rmaxt=" + rmaxt + ", rmaxc=" + rmaxc + '}';
    }
   
}
