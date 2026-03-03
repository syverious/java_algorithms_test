package com.test.waveAndFrequencies;

import static com.test.defination.Constant.*;

/*
 * 磁矩-角动量-质量 : 康普顿波长
 * 2026-1-26
 */
public class SpinMomentum {

    private static double r = 5.29177 * Math.pow(10, -11);

    private static double λe = 2.42633 * Math.pow(10, -12);

    private static double λp = 1.32142 * Math.pow(10, -15);

    /*
     * |S| = (s(s+1))^0.5ℏ = (3/4)^0.5ℏ
     */
    public static double quantum(double s){
        //return Math.pow(s * (s + 1), 0.5) * ℏ;    NOT right !
        return 0.5 * ℏ;
    }

    /*
     * L = 2/5 MR² ω      M = ℏ/cλ
     *   = 2/5 ℏ/cλ  λ²ω
     *   = 2/5 ℏλω/c      ω = v/r = 0.5 * c/λ  (disk)
     *   = 2/5 ℏ
     */
    public static double classicSphere(){
        return ℏ * 2/5;
    }

    // L = mR²ω    莫比乌斯环展开 2R 0.5ω -> 2mR²ω
    public static double classicToroid(){
        return ℏ;
    }

    // L = 0.5mR²ω
    public static double classicDisk(){
        return ℏ/2;
    }


    public static void main(String[] args) {
        System.out.println("q: " + quantum(0.5));
      //System.out.println("cS: " + classicSphere());
        System.out.println("cD: " + classicDisk());    // checked

        System.out.println("me: " + magneticMoment(0.5));
        System.out.println("me_: " + magneticMomentClassic());
      //finalTest();

        System.out.println("---------------");
        //double a = 2 * effectiveCharge(λe)/π;
        //double b = EfieldStrength(λe);
        double a = 2 * effectiveCharge(λe)/π;
        double b = EfieldStrength(λe);    // /(2*π)
        System.out.println("photon toroid: " + a);   // average of abs
        System.out.println("point charge: " + b);
        System.out.println(a/b);
        //System.out.println(λe/λp);    // 1836.1535318
        //System.out.println(r/λe);    // 21.80977
    }

    // μ = g(e/2m)S      S = (3/4)^0.5ℏ
    public static double magneticMoment(double s){
        double λ = ℏ/(2 * c * 9.1093837015 * Math.pow(10, -31));
        //return (2 * e / (ℏ/2 * c*λ) ) * Math.pow(s * (s + 1), 0.5) * ℏ;
        //return (2 * e /  (2 * 9.1093837015 * Math.pow(10, -31))) * Math.pow(s * (s + 1), 0.5) * ℏ;    NOT right !
        return (2 * e /  (2 * 9.1093837015 * Math.pow(10, -31))) * 0.5 * ℏ;         // checked
    }

    // μ = 0.5 * qvr
    // 2πr = 2λ        2.4263301321770935 *10^-12    // Lp = 1.6 * 10^-35
    // hypothesis: v = c
    public static double magneticMomentClassic(){   // 是否是复合粒子
        double m = 9.1093837015 * Math.pow(10, -31);
        double e = 1.602176634 * Math.pow(10, -19);   //-C
        double l = h/(c*m*2*π);
        double u = 0.5 * e * c * l;
        // System.out.println("μ: " + u);
        //System.out.println("μ: " + u + "\n");   // 玻尔磁子   -9.274010078362165E-24
        return u;                                // 核磁子    -5.0507838176326076E-27
    }

    public static void finalTest(){
        double m = 9.1093837015 * Math.pow(10, -31);
        double l = h/(c*m*2*π);                       // 约化康普顿波长 3.861624341087962E-13
        System.out.println("λ: " + l * 2*π);          // 康普顿波长 2.4263301321770935E-12
        System.out.println(m*l*l*c/l);
        l = l * 0.5;
        System.out.println(m*l*l*c/l);
    }

    /*
     结论:
          电子质量   m = ℏ/cλ
              磁矩  μ = 0.5 * qcλ
            角动量  S = 1/2 * mR²ω = 0.5 * ℏ     disk shape?    true vacuum
        莫比乌斯环逆展开 0.5   ?

     */

}
