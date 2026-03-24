package com.test.waveAndFrequencies;

import static com.test.defination.Constant.*;

/**
  精细结构常数 α
  2025-5-26
 */
public class α {

    // α=e²/(4πε0cħ)
    public static double value(){
        // double α = e * e/(4 * π * ε0 * c * h/(2*π));
        double α = e * e/(2 * ε0 * c * h);
        // e * e/(2 * ε0 * h);      orbital speed
        return α;
    }
    // √α = e/√(2 * ε0 * h);    比α更重要的是√α

    public static void main(String[] args) {
        System.out.println("α: " + value());
        System.out.println((e*e/(h * 2 * π * c)));          // e 偶数           1
        System.out.println("e * e/(2 * ε0 * h): " +  137.032 * e * e/(2 * ε0 * h * c));
        System.out.println("e * e/(2 * ε0 * h): " +  137.032 * e * e * μ0 * c/(2 * h));
        double ratio = 137.032 * e * e * μ0 * c/(2 * h);
        double ratio1 = 137.032 * e * e * Math.sqrt(μ0)/(2 * h * Math.sqrt(ε0));
        double ratio2 = 137.032 * e * e * π * Math.sqrt(μ)/(h * Math.sqrt(ε0));
        System.out.println(ratio2 + "\n");

        System.out.println("e: " + e/h);
        System.out.println("ε0: " + ε0/h);
        System.out.println("μ0: " + μ0/h);
        System.out.println("μ': " + μ/h);
        System.out.println("c: " + c/h + "\n");

        System.out.println("ε0: " + ε0/e);
        System.out.println("μ0: " + μ0/e);
        System.out.println("μ': " + μ/e);
        System.out.println("c: " + c/e + "\n");

        System.out.println("单圈图0(α): " + e * e/(4 * π));

        System.out.println("18769 * π: " + (18769 * π));
        System.out.println("!!!: " + (2 * π * e *e *e *e *137)/(h * h));
        System.out.println("!!!: " + h/(4 * π * e * e * c));
    }

    // 电子轨道降低 速度增加

    // hypothesis: 电子与质子合并成为中子时, 电子速度达到接近光速  137倍?

    // 精细结构常数α 的另一种推导方式, 电磁作用于万有引力作用的强度比, 详情参考 GravityEqualsToElectroForce

}
