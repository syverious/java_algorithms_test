package com.test.defination.conceptAndTheory;

import com.test.waveAndFrequencies.α;

import static com.test.defination.Constant.*;

/**
 * considering the scenario of annihilation of an electron and a positron
 * Fₚ  =  Fₑ
 */
public class GravityEqualsToElectroForce {

    // Fₚ = Gmₚ²/lₚ² = G ℏ²/c²lₚ⁴
    //                G ℏ² ε₀μ₀ / lₚ⁴

    // G = lₚ²/ℏε₀μ₀ √ε₀μ₀
    /** F =  ℏ / n²λ₁λ₂ √ε₀μ₀ */
    // Fₚ = ℏ/ lₚ² √ε₀μ₀            Fₚ : lₚ   ==  Fₚ : nlₚ   F max

    // Fₑ = kq²/r²   k = 1/4πε₀
    // q²/4πε₀n²lₚ² = ℏ/ lₚ² √ε₀μ₀
    /*
     * q²/4πε₀ = ℏ/√ε₀μ₀
     * q² = n² 4π ℏ √ε₀/√μ₀         Fₑ : r -> nlₚ   n = √α ~ 0.00855
     */                                             /** 耦合强度 */
    // α 直接决定了电磁相互作用的概率幅。例如，一个电子发射一个光子的概率振幅正比于 √α 。
    public static void checkElectronCharge(){
        double m = 9.1093837015 * Math.pow(10, -31);
        double l = h/(c*m*2*π);

        System.out.println("q²/4πε₀lₚ²: " + e*e/(4*π*ε0*lp*lp));
        //System.out.println("q/4πε₀λₑ²: " + e/(4*π*ℏ*ε0*l*l));
        System.out.println("q²/n²4πε₀lₚ²: " + e*e/(Math.pow(0.0855,2)*4*π*ε0*lp*lp) + "  Fₑ");
        System.out.println("ℏ/lₚ²√ε₀μ₀: " + ℏ/(lp*lp*Math.sqrt(ε0*μ0)) + "  Fₚ");
        System.out.println("q: " + e);
        //System.out.println("q: " + e*0.91);
        System.out.println(": " + Math.sqrt(4*π*ℏ*Math.sqrt(ε0/μ0)));
        System.out.println("theoretical result: " + Math.sqrt(Math.pow(0.0855,2)*4*π*ℏ*Math.sqrt(ε0/μ0)));    // n ~ 0.0855
        //System.out.println("calculated: " + (l/lp) * 4*π*ℏ*Math.sqrt(ε0/μ0));
        //System.out.println("calculated: " + ((l*l)/(lp*lp)) * 4*π*ℏ*Math.sqrt(ε0/μ0));    // (l*l/lp*lp) *
        //System.out.println((l*l/lp*lp));
        System.out.println("classic result: " + Math.sqrt(α.value()*4*π*ℏ*Math.sqrt(ε0/μ0)));
    }

    public static void main(String[] args) {
        checkElectronCharge();
    }

    /* 正负电子湮灭 情况下
     * 束缚在康普顿波长内的光子的约束力达到极限-普朗克力 Fₚ = ℏ/ lₚ² √ε₀μ₀      Eₚ = ℏ/ lₚ √ε₀μ₀
     * 正电场光子与负电场光子间吸引力  Fₑ = q²/4πε₀n²lₚ²
     * 当二者相等得到  q²/4πε₀n²lₚ² = ℏ/ lₚ² √ε₀μ₀         (n² ~ 精细结构常数α)
     * 可自然而然得出 α 与 lₚ 的关系，即正负电子距离为 √αlₚ 时发生湮灭，光子不再被束缚。 -- Lofting Young
     */

    // 引力量子化
    /* F = lₚ² (ℏ²c²/λ₁λ₂) / (nlₚ)² ℏε₀μ₀ √ε₀μ₀   (n>=1)
       lₚ² (ℏ/λ₁λ₂) / (nlₚ)² √ε₀μ₀
       (ℏ/λ₁λ₂) / n² √ε₀μ₀           λ₁ = n₁lₚ
       ℏ / λ₁λ₂n² √ε₀μ₀              λ₂ = n₂lₚ   (n₁, n₂>=1)
       ℏ / n₁n₂n² lₚ² √ε₀μ₀
     */

    // mass of a blackhole  m = ℏ/cλ  ->  ℏ/cr    4ℏr

    /* Mₕ = c²rₛ/2G   G = lₚ²/ℏε₀μ₀ √ε₀μ₀
           ℏε₀μ₀ √ε₀μ₀ c²rₛ / lₚ²
         = ℏ √ε₀μ₀ rₛ / lₚ²
         = ℏ √ε₀μ₀ n / lₚ   (n>=1)

       m = ℏ/cλ  -> ℏ √ε₀μ₀ / λ
                    ℏ √ε₀μ₀ / nlₚ   (n>=1)
     */


}
