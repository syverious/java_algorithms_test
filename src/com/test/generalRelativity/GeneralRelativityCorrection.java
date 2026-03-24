package com.test.generalRelativity;

import static com.test.defination.Constant.*;

/**
 * Correction of General Relativity
 * 2026-3-24
 * @author LoftyingYoung
 */
public class GeneralRelativityCorrection {

    public static double LorentzFactor(double v){    //  γ = c/√(c²-v²)
        double γ = 1/Math.sqrt(1-v*v/(c*c));
        System.out.println("γ: " + γ);
        return γ;
    }

    /**
       Hypothesis:
       From the view of static observers, a particle with Reduced Compton wavelength λ would collapse into a black hole
       when its length is contracted to Planck length lₚ.
     */

    // Maximum velocity of a fundamental particle in General relativity.
    public static double relativityMaximumVelocity(double m, String tag){
        double l = h/(c*m*2*π);
        double β = Math.sqrt(1-lp*lp/(l*l));
        double v = c * β;
        System.out.println("Max velocity : " + v);
        System.out.println("ratio: " + β);
        return v;
    }

    // Energy upper limit of a fundamental particle
    public static double relativityEnergyUpperLimit(double m, double γ){
        double l = h/(c*m*2*π);
        /* l/lₚ = γ = c/√(c²-v²)   = ℏ/(m₀clₚ)

           E = γm₀c² = m₀c²l/lₚ ,  m₀ = ℏ/cl

           总能量 E = ℏc/lₚ (上限)
           静能量 E₀ = m₀c²
           动能   K = E - E₀ = ℏc/lₚ - m₀c²

         */
        double E = γ*ℏ*c/l;   // 1 <= γ <= l/lₚ
        return E;
    }


}
