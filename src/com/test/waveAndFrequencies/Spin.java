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
    // 波长计算 hypothesis :  m = ℏ/cnλ꜀,  (λ꜀ = lₚ ;  n = 1, 2, 3, 4, ...)         E = mc² = hc/λ꜀ = hv
    // 约化康普顿波长!    (它自然地出现在量子力学的不确定性关系和相对论性方程中)  ℏ=h/2π
    public static double waveLengthFromMass(double m, String name){
        double waveLength = h/(c*m*2*π);
        System.out.println(name + "λᵣ: " + waveLength);    // 以康普顿波长为周长的半径
        ComptonWavelength(m, name);
        return waveLength;
    }
    // 康普顿波长   (难道是直径? 根据磁矩应该是周长)
    public static double ComptonWavelength(double m, String name){
        double waveLength = h/(c*m);
        System.out.println(name + "λ꜀: " + waveLength);
        return waveLength;
    }

    // 磁矩计算
    // μ = 0.5 * qvr
    // 2πr = 2λ        2.4263301321770935 *10^-12    // Lp = 1.6 * 10^-35
    // hypothesis: v = c
    public static double magneticMoment(double m){   // 是否是复合粒子
        double e = 1.602176634 * Math.pow(10, -19);   //-C
        double l = waveLengthFromMass(m, "");
        double u = 0.5 * e * c * l;
        // System.out.println("μ: " + u);
        System.out.println("μ: " + u + "\n");   // 玻尔磁子   -9.274010078362165E-24
        return u;                                // 核磁子    -5.0507838176326076E-27
    }                                            //  中子     -1.913
    public static double magneticMomentCompton(double m){   // 是否是复合粒子
        double e = 1.602176634 * Math.pow(10, -19);
        double l = waveLengthFromMass(m, "");
        double u = 0.5 * e * c * l/π;
        // System.out.println("μ: " + u);
        System.out.println("1/2: " + u/2 + "\n");
        return u/2;
    }

    /**
     * proton model: 3D electron in x y z direction.
     */
    public static void main(String[] args) {
        double mn = 1.6748 * Math.pow(10, -27);    //kg
        double mp = 1.6726219 * Math.pow(10, -27);    //kg
        double me = 9.1093837015 * Math.pow(10, -31);    //kg

        //electronMass();      // 核磁子 μN 比玻尔磁子 μB 小约 1836倍    1836/3 = 612
        double ue = magneticMoment(me);    // 电子自旋磁矩    比值 1/658.2
        double uN = magneticMoment(mp);    // 质子自旋磁矩
        double res = magneticMoment(mn);
        System.out.println("e: " + ue);
        System.out.println("p: " + 2.793 * uN);
        //System.out.println("p: " + uN/mp);
        System.out.println("n: " + -1.913 * uN);    // -4.706       -1.68492660      200/200 + 137/200     ?
        //System.out.println("n: " + uN/mn);

//        System.out.println("lp: " + lp);
//        System.out.println(2.4263301 * Math.pow(10, -12) / lp);
        System.out.println("m ratio: " + mn/(me));

        //electronSize();
        DWaveLength(0.99999987*c);
    }

    // 电子落入原子核 磁矩变化   v  r
    // μ = 0.5 * qvr

    // 质子动量越大，越接近光速，夸克强相互作用越弱（随着能量增加）      不能再将质子视为一个由三个价夸克（两个上夸克、一个下夸克）和胶子海构成的、紧密结合的整体。相反，它表现得更像一袋几乎自由的、点状的粒子
    /*
      你是一名理论物理学家、核物理学家，正在研究一种解释质子内部结构的物理理论。已知质子的自旋为1/2，而根据量子色动力学，质子由夸克和胶子组成。如何建立一个模型，使质子的自旋磁矩能够被精确描述？
     */

     /*
      电子 自旋磁矩 μₑ = -gₑμᵦS
      狄拉克的理论可以计算费米子的磁矩： μ = g(e/2m)s      m = ℏ/cλ꜀
      其中e是电荷，m是粒子质量，s是粒子自旋，g是一个待定系数，通常被称为g因子。
      狄拉克的理论预言：TODO 没有内部结构的费米子的g因子应该是g=2，严格等于2。
      后来，狄拉克的理论被发展为了量子场论，在量子场论的计算中费米子的g因子被赋予了一个很小的修正（大约千分之一），
      有g=2.0023...，这就是对无内部结构费米子的磁矩的最终理论预言。
     */

     /*
      电子反常磁矩 g = 2(1 + a),  a = α/2π
      */

     // TODO 惯性质量、引力质量是由于转动产生的？
     // m = ω²r   (v = ωr)
     // m ∝ a = v²/r  (c²/r)

     // ℏ/cλ = c²/λ
     //    ℏ = c³


    // 正弦函数在一个周期内的图像长度
    public static double waveRatio(){
        return 2*π/7.6404;
    }


    /* Electron pair */
    /*
       自旋平行（总自旋S=1，自旋三重态）：
         电子和正电子自旋方向相同（都向上或都向下）
         电子磁矩方向与自旋相反（向下）
         正电子磁矩方向与自旋相同（向上）
         结果：电子磁矩向下，正电子磁矩向上 → 磁矩方向相反

       自旋反平行（总自旋S=0，自旋单态）：
         电子和正电子自旋方向相反（一个向上，一个向下）
         电子磁矩方向与自旋相反（如果电子自旋向上，磁矩向下）
         正电子磁矩方向与自旋相同（如果正电子自旋向下，磁矩向下）
         结果：电子磁矩向下，正电子磁矩向下 → 磁矩方向相同
     */


    public static void electronSize() {
        double length = Math.sqrt(Math.pow(1-0.9999999964,2));    //  1.3901847491981416E-21
        //length = Math.sqrt(Math.pow(1-0.99999987,2));          //  5.020111642915618E-20
        double me = 9.1093837015 * Math.pow(10, -31);    //kg
        double waveLength = h/(c*me*2*π);
        System.out.println(length * waveLength);
    }

    // 德布罗意波长 λ = h/p
    public static double DWaveLength(double speed){
        double me = 9.1093837015 * Math.pow(10, -31);    //kg
        //p = mv
        double result = h/(me * speed);
        System.out.println(result);
        //p = vℏ/cλ꜀
        //double p = speed * ℏ/(cλ꜀)
        return result;
    }

    /** superposition */
    public static double[] spin(double x, double y){
        double[] spin = {0.0D, 0.0D};
        return spin;
    }

    /* definition of Entanglement: x1 = x2 & y1 = y2 (↑↑  ↑↓  ↓↓)
                                   key: φ1 = φ2
     */


}
