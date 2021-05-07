/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.barre;

import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.treillis.FigureSimple;
import fr.insa.jacob.projet.projet2.treillis.Identificateur;
import fr.insa.jacob.projet.projet2.treillis.Treillis;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
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
    
    public TypeBarre(int idType, double cout, double lmin, double lmax, double rmaxt, double rmaxc){
        
        this.idType = idType;
        this.lmin = lmin;
        this.cout = cout;
        this.lmax = lmax;
        this.rmaxt = rmaxt;
        this.rmaxc = rmaxc;
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

    @Override
    public String toString() {
        return "TypeBarre{" + "idType=" + idType + ", cout=" + cout + ", lmin=" + lmin + ", lmax=" + lmax + ", rmaxt=" + rmaxt + ", rmaxc=" + rmaxc + '}';
    }
    
    /*    public void save(Writer w, Identificateur<Treillis> num) throws IOException{
    if(! num.objExist(this)){
    int id = num.creeID(this);
    w.append("TypeBarre;"+id+";"+this.cout+";" +this.lmin+";"+this.lmax+";"+ this.rmaxt +";"+ this.rmaxc+"\n");
    }
    }*/

}
