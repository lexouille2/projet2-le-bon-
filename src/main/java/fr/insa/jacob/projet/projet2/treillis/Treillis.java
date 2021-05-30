/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2.treillis;

import fr.insa.jacob.projet.projet2.Matrice;
import fr.insa.jacob.projet.projet2.barre.Barre;
import fr.insa.jacob.projet.projet2.barre.BarreAcier;
import fr.insa.jacob.projet.projet2.barre.BarreBois;
import fr.insa.jacob.projet.projet2.barre.TypeBarre;
import fr.insa.jacob.projet.projet2.noeud.AppuiDouble;
import fr.insa.jacob.projet.projet2.noeud.AppuiSimple;
import fr.insa.jacob.projet.projet2.noeud.Noeud;
import fr.insa.jacob.projet.projet2.noeud.NoeudAppui;
import fr.insa.jacob.projet.projet2.noeud.NoeudSimple;
import fr.insa.jacob.projet.projet2.noeud.Point;
import fr.insa.jacob.projet.projet2.terrain.Terrain;
import fr.insa.jacob.projet.projet2.terrain.TriangleTerrain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author PC
 */

public abstract class Treillis {

    public static Color COULEUR_SELECTION = Color.RED;
    
    private Groupe grp;
    
    public Groupe getGrp() {
        return grp;
    }

    void setGrp(Groupe grp) {
        this.grp = grp;
    }
    
    public abstract void dessine(GraphicsContext context);  
    
    public abstract double distance(Point p);
    
    public abstract void dessineSelection(GraphicsContext context);
    
    public abstract void changeCouleur(Color value);
    
    public abstract void save(Writer w, Identificateur<Treillis> num) throws IOException;
        
    public void sauvegarde(File fout) throws IOException {
        Identificateur<Treillis> num = new Identificateur<Treillis>();
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))) {
            this.save(bout, num);
        }
    }

    public static Treillis lecture(File fin) throws IOException {
        Identificateur<Treillis> num = new Identificateur<Treillis>();
        Treillis derniere = null;//on crée la variable qui donnera la dernière figure qu'on a lu
        try (BufferedReader bin = new BufferedReader(new FileReader(fin))) {
            String line;
            while ((line = bin.readLine()) != null && line.length() != 0) {
                String[] bouts = line.split(";");
                if (bouts[0].equals("Point")) {
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4], bouts[5], bouts[6]);
                    Point np = new Point(px, py, col);
                    num.associe(id, np);//id associé au point
                    derniere = np;
                }else if (bouts[0].equals("Noeud")) {
                    int id = Integer.parseInt(bouts[1]);
                   // int idP = Integer.parseInt(bouts[2]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4], bouts[5], bouts[6]);
                    Point point = new Point(px,py);
                    Noeud nn = new Noeud(id,point,col);
                    num.associe(id, nn);//id associé au noeud
                    derniere = nn;
                } else if (bouts[0].equals("Barre")) {
                    int id = Integer.parseInt(bouts[1]);
                    int idN1 = Integer.parseInt(bouts[2]);
                    int idN2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4], bouts[5], bouts[6]);
                    Noeud n1 = (Noeud) num.getObj(idN1);
                    Noeud n2 = (Noeud) num.getObj(idN2);
                    TypeBarre teap = new BarreBois();
                    Barre nb = new Barre(id, n1, n2, teap);//on crée la barre
                    num.associe(id, nb);//id associé à la barre
                    derniere = nb;
                } else if (bouts[0].equals("Triangle Terrain")){
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    int idP3 = Integer.parseInt(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5], bouts[6], bouts[7]);
                    Point p1 = (Point) num.getObj(idP1);
                    Point p2 = (Point) num.getObj(idP2);
                    Point p3 = (Point) num.getObj(idP3);
                    TriangleTerrain nTT = new TriangleTerrain(id, p1, p2, p3, col);
                    num.associe(id, nTT);
                    derniere = nTT;
                } else if (bouts[0].equals("Groupe")) {//bouts est la position dans le fichier texte
                    int id = Integer.parseInt(bouts[1]);
                    Groupe ng = new Groupe();
                    num.associe(id, ng);//id associé au groupe
                    for (int i = 2; i < bouts.length; i++) {
                        int idSous = Integer.parseInt(bouts[i]);
                        Treillis tre = num.getObj(idSous);
                        ng.add(tre);
                    }
                    derniere = ng;
                }
            }

        }
        return derniere;
    }
    
    public static Treillis testTreillis(){
        Groupe grp = new Groupe();
        ArrayList<Noeud> an = new ArrayList<Noeud>();
        ArrayList<Noeud> anad = new ArrayList<Noeud>();
        ArrayList<Noeud> anas = new ArrayList<Noeud>();
        ArrayList<Barre> aba = new ArrayList<Barre>();
        ArrayList<TriangleTerrain> att = new ArrayList<TriangleTerrain>();
        Point p1 = new Point(100,100);
        Point p2 = new Point(100,200);
        Point p3 = new Point(50,150);
        Point p4 = new Point(150,150);
        TriangleTerrain TT = new TriangleTerrain(1, p1, p2, p3, Color.GREEN);
        Noeud n1 = new Noeud(1, p4);
        Noeud nad1 = new AppuiDouble(1, p1, p2, 2, new Point(100,125), Color.BLUE);
        Noeud nas1 = new AppuiSimple(1, p1, p2, 3, new Point(100,175), Color.BLUE);
        Barre ba1 = new Barre(1, nad1, nas1, new BarreAcier());
        Barre ba2 = new Barre(2, nad1, n1, new BarreAcier());
        Barre ba3 = new Barre(3, nas1, n1, new BarreAcier());
        
        grp.add(TT);
        att.add(TT);
        grp.add(nad1);
        anad.add(nad1);
        grp.add(nas1);
        anas.add(nas1);
        grp.add(ba1);
        aba.add(ba1);
        grp.add(n1);
        an.add(n1);
        grp.add(ba2);
        aba.add(ba2);
        grp.add(ba3);
        aba.add(ba3);
        
        int nbb = aba.size(); //nb de barres
        int nbas = anas.size(); //nb d'appui simple
        int nbad = anad.size(); //nb d'appui double
       
        int ni = nbb + nbas + 2*nbad; //nb d'inconnues
        
        System.out.println("Nombre d'inconnues : " + ni); // ici 6
        Matrice mat = new Matrice(ni,ni);
        double a = Math.sqrt(2)/2;
        int i = 0; // ligne 
        mat.coeffs[i][1] = -a;
        mat.coeffs[i][2] = -a;

        i++;
        mat.coeffs[i][1] = a;
        mat.coeffs[i][2] = -a;

        i++;
        mat.coeffs[i][2] = a;
        mat.coeffs[i][5] = 1;

        i++;
        mat.coeffs[i][0] = 1;
        mat.coeffs[i][2] = a;

        i++;
        mat.coeffs[i][1] = a;
        mat.coeffs[i][3] = 1;

        i++;
        mat.coeffs[i][0] = -1;
        mat.coeffs[i][1] = -a;
        mat.coeffs[i][4] = 1;
        System.out.println("mat : \n" + mat);

        Matrice sm = new Matrice(ni, 1);
        sm.coeffs[1][0] = 1000;
        System.out.println("second membre : \n" + sm);

        Matrice.ResSysLin res = mat.resoudSysLin(sm);
        if (res.solUnique) {
            System.out.println("sol : \n" + res.sol);
        } else {
            System.out.println("pas de sol");
        }
        
        return grp;
    }
    
    public static void main(String[] args) {
        testTreillis();
    }
    
}

