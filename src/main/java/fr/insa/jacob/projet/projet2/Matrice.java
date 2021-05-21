/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.jacob.projet.projet2;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author yohann
 */
public class Matrice {
    
    private int nbrLig;
    private int nbrCol;
    public double[][] coeffs;
    private static final double EPSILON_PIVOT = 1E-8;


    public Matrice(int nbrLig, int nbrCol) {
        this.nbrLig = nbrLig;
        this.nbrCol = nbrCol;
        this.coeffs = new double[nbrLig][nbrCol];
    }
    
    public int getNbrLig() {
        return this.nbrLig;
    }

    public int getNbrCol() {
        return this.nbrCol;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public void set(int lig, int col, double val) {
        this.coeffs[lig][col] = val;
    }

    public String toString() {
        // oui, il serait plus efficace d'utiliser un {@link java.lang.StringBuilder}
        // mais ils n'ont pas été vu
        String res = "";
        for (int i = 0; i < nbrLig; i++) {
            res = res + "[";
            for (int j = 0; j < nbrCol; j++) {
                res = res + formatDouble(this.get(i, j));
                if (j < nbrCol - 1) {
                    res = res + " ";
                }
            }
            res = res + "]\n";
        }
        return res;
    }
    
    public static Matrice identite(int taille) {
        Matrice res = new Matrice(taille, taille);
        for (int i = 0; i < taille; i++) {
            res.coeffs[i][i] = 1;
        }
        return res;
    }

    public static Matrice creeVecteur(double[] composantes) {
        Matrice res = new Matrice(composantes.length, 1);
        for (int i = 0; i < composantes.length; i++) {
            res.coeffs[i][0] = composantes[i];
        }
        return res;
    }

    public static String formatDouble(double x) {
//        return formatDouble2Digits(x);
//        return formatDoubleMax2Decimales(x);
        return formatDoubleFixe(x);
    }

    public static String formatDoubleMax2Decimales(double x) {
        DecimalFormat f = new DecimalFormat("#.##");
        return f.format(x);
    }

    public static String formatDouble2Digits(double x) {
        return String.format("%+3.1f", x);
    }

    public static String formatDoubleFixe(double x) {
        return String.format("%+4.2E", x);
    }

    //--------------- partie 2.1
    public Matrice concatLig(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrCol()) {
            throw new Error("les matrices doivent avoir même nombre de colonnes");
        }
        Matrice res = new Matrice(this.getNbrLig() + m2.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                if (i < this.getNbrLig()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i - this.getNbrLig(), j));
                }
            }
        }
        return res;
    }

    public Matrice concatCol(Matrice m2) {
        if (this.getNbrLig() != m2.getNbrLig()) {
            throw new Error("les matrices doivent avoir même nombre de lignes");
        }
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol() + m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                if (j < this.getNbrCol()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i, j - this.getNbrCol()));
                }
            }
        }
        return res;
    }

    public Matrice subLignes(int ligMin, int ligMax) {
        if (ligMin < 0 || ligMax < ligMin || ligMax >= this.getNbrLig()) {
            throw new Error("indices lignes invalides");
        }
        Matrice res = new Matrice(ligMax - ligMin + 1, this.getNbrCol());
        for (int lig = 0; lig < res.getNbrLig(); lig++) {
            for (int col = 0; col < res.getNbrCol(); col++) {
                res.set(lig, col, this.get(ligMin + lig, col));
            }
        }
        return res;
    }

    public Matrice subCols(int colMin, int colMax) {
        if (colMin < 0 || colMax < colMin || colMax >= this.getNbrCol()) {
            throw new Error("indices colonnes invalides");
        }
        Matrice res = new Matrice(this.getNbrLig(), colMax - colMin + 1);
        for (int lig = 0; lig < res.getNbrLig(); lig++) {
            for (int col = 0; col < res.getNbrCol(); col++) {
                res.set(lig, col, this.get(lig, colMin + col));
            }
        }
        return res;
    }

    public Matrice copie() {
        return this.subLignes(0, this.getNbrLig() - 1);
    }

    public Matrice transposee() {
        Matrice res = new Matrice(this.getNbrCol(), this.getNbrLig());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.set(i, j, this.get(j, i));
            }
        }
        return res;
    }

    public Matrice metAuCarre() {
        return this.concatCol(Matrice.identite(this.getNbrLig())).concatLig(
                Matrice.identite(this.getNbrCol()).concatCol(this.transposee()));
    }

    public Matrice add(Matrice m2) {
        if (this.getNbrLig() != m2.getNbrLig() || this.getNbrCol() != m2.getNbrCol()) {
            throw new Error("tailles incompatibles pour add");
        }
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j) + m2.get(i, j));
            }
        }
        return res;
    }

    public Matrice opp() {
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.set(i, j, -this.get(i, j));
            }
        }
        return res;
    }

    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }

    public Matrice mult(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrLig()) {
            throw new Error("tailles incompatibles pour mult");
        }
        Matrice res = new Matrice(this.getNbrLig(), m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                double cur = 0;
                for (int k = 0; k < this.getNbrCol(); k++) {
                    cur = cur + this.get(i, k) * m2.get(k, j);
                }
                res.set(i, j, cur);
            }
        }
        return res;
    }

    public int permuteLigne(int lig1, int lig2) {
        double[] tempLigne = this.coeffs[lig1];
        this.coeffs[lig1] = this.coeffs[lig2];
        this.coeffs[lig2] = tempLigne;
        if (lig1 == lig2) {
            return 1;
        } else {
            return -1;
        }
    }

    public int permuteLigneV2(int lig1, int lig2) {
        for (int col = 0; col < this.getNbrCol(); col++) {
            double temp = this.get(lig1, col);
            this.set(lig1, col, this.get(lig2, col));
            this.set(lig2, col, temp);
        }
        if (Math.abs(lig2 - lig2) % 2 == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void transvection(int lig1, int lig2) {
        if (lig1 >= this.getNbrCol()) {
            throw new Error("lig1 doit être inférieur au nombre de colonnes de la matrice");
        }
        if (this.get(lig1, lig1) == 0) {
            throw new Error("pivot nul : ligne " + lig1 + " Mat :\n" + this);
        }
        double p = this.get(lig2, lig1) / this.get(lig1, lig1);
        for (int col = 0; col < this.getNbrCol(); col++) {
            if (col == lig1) {
                this.set(lig2, col, 0);
            } else {
                this.set(lig2, col, this.get(lig2, col) - p * this.get(lig1, col));
            }
        }
    }

    public int lignePlusGrandPivot(int e) {
        if (e >= this.getNbrLig() || e >= this.getNbrCol()) {
            throw new Error("mauvais indice de pivot : M_e,e doit exister");
        }
        double curMax = Math.abs(this.get(e, e));
        int imax = e;
        for (int i = e + 1; i < this.getNbrLig(); i++) {
            if (Math.abs(this.get(i, e)) > curMax) {
                curMax = Math.abs(this.get(i, e));
                imax = i;
            }
        }
        if (curMax <= EPSILON_PIVOT) {
            return -1;
        } else {
            return imax;
        }
    }

    public static class ResGauss {

        public int rang;
        public int signaturePermutation;

        public ResGauss(int rang, int signaturePermutation) {
            this.rang = rang;
            this.signaturePermutation = signaturePermutation;
        }

        @Override
        public String toString() {
            return "{ResGauss : rang = " + this.rang + " ; sigPerm = "
                    + this.signaturePermutation + "}";
        }
    }

    public ResGauss descenteGauss() {
        int e = 0;
        int imax;
        int signature = 1;
        while (e < this.getNbrLig() && e < this.getNbrCol()
                && (imax = this.lignePlusGrandPivot(e)) != -1) {
            signature = signature * this.permuteLigne(e, imax);
            for (int lig = e + 1; lig < this.getNbrLig(); lig++) {
                this.transvection(e, lig);
            }
            e++;
        }
        return new ResGauss(e, signature);
    }
     
    public void pivotsUnitaires(int rang) {
        for (int lig = 0; lig < rang; lig++) {
            double div = this.get(lig, lig);
            if (div == 0) {
                throw new Error("pivot nul : ligne " + lig + " Mat :\n" + this);
            }
            for (int col = 0; col < this.getNbrCol(); col++) {
                this.set(lig, col, this.get(lig, col) / div);
            }
        }
    }

    public void remonteeGauss(int rang) {
        for (int e = rang - 1; e >= 0; e--) {
            for (int lig = e - 1; lig >= 0; lig--) {
                this.transvection(e, lig);
            }
        }
    }

    public static class ResSysLin {

        public boolean solUnique;

        public Matrice sol;

        private ResSysLin() {
            this.solUnique = false;
        }

        private ResSysLin(Matrice sol) {
            this.solUnique = true;
            this.sol = sol;
        }

        public static ResSysLin pasDeSolUnique() {
            return new ResSysLin();
        }

        public static ResSysLin sol(Matrice sol) {
            return new ResSysLin(sol);
        }

        public String toString() {
            if (this.solUnique) {
                return this.sol.toString();
            } else {
                return "{pas de sol unique}";
            }
        }

    }

    public Matrice getColonne(int col) {
        Matrice res = new Matrice(this.getNbrLig(), 1);
        for (int lig = 0; lig < this.getNbrLig(); lig++) {
            res.set(lig, 0, this.get(lig, col));
        }
        return res;
    }

    public ResSysLin resoudSysLin(Matrice secondMembre) {
        if (this.getNbrLig() != this.getNbrCol()) {
            throw new Error("La matrice n'est pas carrée");
        }
        if (secondMembre.getNbrCol() != 1) {
            throw new Error("Le second membre n'est pas un vecteur");
        }
        Matrice toGauss = this.concatCol(secondMembre);
        ResGauss res = toGauss.descenteGauss();
        if (res.rang == this.getNbrLig()) {
            toGauss.pivotsUnitaires(res.rang);
            toGauss.remonteeGauss(res.rang);
            return ResSysLin.sol(toGauss.getColonne(res.rang));
        } else {
            return ResSysLin.pasDeSolUnique();
        }
    }

    public Optional<Matrice> inverse() {
        if (this.getNbrLig() != this.getNbrCol()) {
            throw new Error("inverse seulement pour les matrices carrées");
        }
        Matrice toGauss = this.concatCol(Matrice.identite(this.getNbrLig()));
        ResGauss triSup = toGauss.descenteGauss();
        if (triSup.rang == this.getNbrLig()) {
            toGauss.pivotsUnitaires(triSup.rang);
            toGauss.remonteeGauss(triSup.rang);
            Matrice inverse = toGauss.subCols(this.getNbrCol(), 2 * this.getNbrCol() - 1);
            return Optional.of(inverse);
        } else {
            return Optional.ofNullable(null);
        }
    }

    public double normeSup() {
        double max = 0;
        for (int lig = 0; lig < this.getNbrLig(); lig++) {
            for (int col = 0; col < this.getNbrCol(); col++) {
                double cur = Math.abs(this.get(lig, col));
                if (cur > max) {
                    max = cur;
                }
            }
        }
        return max;
    }

    public double distSup(Matrice m2) {
        return this.moins(m2).normeSup();
    }
    
    // exemple d'inverse pour une matrice
    public static void testInverse(int nbrTest, int taille, double epsilonDistAcceptable,
            boolean printErreurs) {
        System.out.println("test distance(M . M-1,Identite) < epsilon");
        System.out.println("nbr Tests : " + nbrTest);
        System.out.println("taille des matrices : " + taille);
        System.out.println("Matrices contenant 0,1, ou 2 avec probas ~ 1/3;1/3;1/3");
        System.out.println("epsilon : " + epsilonDistAcceptable);
        int nbrNonInversible = 0;
        int nbrErreur = 0;
        for (int i = 0; i < nbrTest; i++) {
            Matrice m = new Matrice(6,6);//remplacer 6 par ni
            Matrice id = Matrice.identite(taille);
            Matrice mc = m.copie().concatCol(id);
            ResGauss rg = mc.descenteGauss();
            if (rg.rang != taille) {
                nbrNonInversible++;
            } else {
                mc.pivotsUnitaires(taille);
                mc.remonteeGauss(taille);
                Matrice inverse = mc.subCols(taille, 2 * taille - 1);
                Matrice idCalc = m.mult(inverse);
                double dist = id.distSup(idCalc);
                if (dist > epsilonDistAcceptable) {
                    nbrErreur++;
                    if (printErreurs) {
                        System.out.println("--------- problème ------------");
                        System.out.println("M =");
                        System.out.println(m);
                        System.out.println("M-1 = ");
                        System.out.println(inverse);
                        System.out.println("M . M-1 =");
                        System.out.println(idCalc);
                        System.out.println("distance sup = " + dist);
                    }
                }
            }
        }
        System.out.println("non inversible : " + nbrNonInversible + "/" + nbrTest
                + " ~ " + (nbrNonInversible * 100.0 / nbrTest) + " %");
        System.out.println("nombre d'inverses avec dist > " + epsilonDistAcceptable + " : "
                + nbrErreur + " (devrait être 0)");
    }
    
    // exemple sujet du projet
    public static void resoudExempleTreillisPortique() {
        System.out.println("----- treillis portique du sujet de projet");
        Matrice mat = new Matrice(6, 6);
        double a = Math.sqrt(2) / 2;
        int i = 0;
        mat.coeffs[i][0] = a;
        mat.coeffs[i][3] = 1;

        i++;
        mat.coeffs[i][0] = -a;
        mat.coeffs[i][2] = -1;
        mat.coeffs[i][4] = 1;

        i++;
        mat.coeffs[i][1] = a;
        mat.coeffs[i][5] = 1;

        i++;
        mat.coeffs[i][1] = a;
        mat.coeffs[i][2] = 1;

        i++;
        mat.coeffs[i][0] = -a;
        mat.coeffs[i][1] = -a;

        i++;
        mat.coeffs[i][0] = a;
        mat.coeffs[i][1] = -a;
        System.out.println("mat : \n" + mat);

        Matrice sm = new Matrice(6, 1);
        sm.coeffs[5][0] = 1000;
        System.out.println("second membre : \n" + sm);

        ResSysLin res = mat.resoudSysLin(sm);
        if (res.solUnique) {
            System.out.println("sol : \n" + res.sol);
        } else {
            System.out.println("pas de sol");
        }

    }

}
