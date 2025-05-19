package com.test.defination;

import static com.test.defination.Constant.*;

public class DimensionAnalysis {


    public static void main(String[] args) {
        calculationTest();
    }

    public static void calculationTest(){

        double Lp = Math.sqrt(G*h/(Math.pow(c, 3)*2*π));
        double Mp = Math.sqrt(h*c/(G*2*π));
        System.out.println("plank length : " + Lp);
        System.out.println("plank mass : " + Mp);

        double Bp = 2*c*c*Lp/(2*G);    // rₛ = 2GM/c²    M = rₛc²/2G
        System.out.println("smallest blackhole mass: " + Bp);
        System.out.println("ratio: " + Bp/Mp);
        System.out.println();

        System.out.println("hc: " +  2*π * h * c);
        // 1.98642957x10^-25
        // G = 6.67430×10^−11 m³ / kg s²
        System.out.println("G : " + h*c/(h*c/G));
        // ℏ
        // G 量纲 ∝ ℏ*c / m²

        // hypothesis :  m = ℏ/cl      meaning of l, the wavelength of a particle ?

        // c²l² ℏ*c /ℏ²   =  c³l²/ℏ      G ∝ c³/ Λℏ



        // blackhole, a celestial body which we know little about,
        // has stronger gravity towards its spinning side.
        // that explains why the galaxy spins in spiral form and most of the star systems would spin in the same direction.

        System.out.println("wavelength using Plank mass: " + h/(2*π *c*Mp));
        /*
         hypothesis :  m = ℏ/cnλ,  (λ = lₚ ;  n = 1, 2, 3, 4, ...)   nλ may not exceeds the boundary of the universe.
         mass can be considered the effect related to the wavelength of "basic particles".

         */

    }

}
