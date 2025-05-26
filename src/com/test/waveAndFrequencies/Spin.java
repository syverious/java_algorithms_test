package com.test.waveAndFrequencies;

import static com.test.defination.Constant.*;
/**
  计算电子波长 磁矩
  2025-5-26
 */
public class Spin {

    /**
     * 电子质量 -> 电子波长 -> 电子自旋磁矩
     */
    // 波长计算 hypothesis :  m = ℏ/cnλ,  (λ = lₚ ;  n = 1, 2, 3, 4, ...)
    public static double waveLengthFromMass(double m){
        double waveLength = h/(c*m);
        System.out.println("λ: " + waveLength);
        return waveLength;
    }

    // 磁矩计算
    // μ = 0.5 * qvr
    // 2πr = 2λ        2.4263301321770935 *10^-12    // Lp = 1.6 * 10^-35
    // hypothesis: v = c
    public static void magneticMoment(double m){
        double e = -1.602176634 * Math.pow(10, -19);   //C
        double l = waveLengthFromMass(m);
        double u = 0.5 * e * c * l/π;
        System.out.println("μ: " + u);
        System.out.println("1/2: " + u/2 + "\n");   // 波尔磁子   -9.274010078362165E-24
    }
    // 电子 自旋磁矩 μₑ = -gₑμᵦS

    //

    public static void main(String[] args) {
        double mp = 1.6726219 * Math.pow(10, -27);    //kg
        double me = 9.1093837015 * Math.pow(10, -31);    //kg
        //electronMass();
        magneticMoment(me);    // 电子自旋磁矩
        magneticMoment(mp);    // 质子自旋磁矩    -5.0507838176326076E-27
    }

}
