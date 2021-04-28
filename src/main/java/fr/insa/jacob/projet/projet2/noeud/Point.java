/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.noeud;





/**
 *
 * @author PC
 */
public class Point{
    
    private double px;
    private double py;
    
    public Point (double px, double py){
        this.px = px;
        this.py = py;
    }
    
    public double getPx(){
        return this.px;
    }
    
    public double getPy(){
        return this.py;
    }
    
    public void setPx (double px){
        this.px = px;
    }
    
    public void setPy (double py){
        this.py = py;
    }

    public String toString() {
        return "Point{" + "px=" + px + ", py=" + py + '}';
    }
    
    
    public double distance(Point p2) {
        double dx = this.px - p2.px;
        double dy = this.py - p2.py;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
