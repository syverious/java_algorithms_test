package com.test.defination;

public class Constant {

    public static final double π = Math.PI;

    // 事件传播速度
    public static final double c = 2.9979 * Math.pow(10, 8);  // m/s
    // 普朗克常数
    public static final double h = 6.62607015 * Math.pow(10, -34);  // J·s  (kg m^2 /s)
    public static final double ℏ = 6.62607015 * Math.pow(10, -34)/(2 * Math.PI);
    // 最小电荷
    public static final double e = 1.6021766 * Math.pow(10, -19);  // C
    // 真空介电常数
    public static final double ε0 = 8.854187817 * Math.pow(10, -12);  // F/m
    // 真空磁导率
    public static final double μ0 = 4 * Math.PI * Math.pow(10, -7);  // H/m

    public static final double μ = Math.pow(10, -7);  // H/m

    // 静电力常量 库伦常数 k
    public static final double k = c * c * Math.pow(10, -7);  // H/m

    // ε0 = 1/μ0c²

    // c = 1/√ε0μ0


    // G = 6.67430×10^−11 m³ / kg s²
    public static final double G = 6.67259 * Math.pow(10, -11);
    // kg =  J·s^2 / m^2

    public static final double lp = Math.sqrt(h * G / (Math.pow(c, 3) * 2 * Math.PI));

    /**

     S=4πrₛ²   M/rₛ = c²/2G

     M = c²rₛ/2G       rₛ/2Gε₀μ₀

     ρ = M/s = c²/8Gπrₛ */   // m / kg s²   面密度     ρ = ℏ√ε₀μ₀/8πrₛ lₚ²

    // G = lₚ²/ℏε₀μ₀ √ε₀μ₀     M = rₛℏ√ε₀μ₀ /2lₚ²   =  n ℏ√ε₀μ₀ /2lₚ

     /*
     V=4πrₛ³/3

     ρ = M/s = 2c²/3Gπrₛ² */

     /* m = ℏ/cnλ

        c²rₛ/2G = ℏ/cnλ

        nλ = 2Gℏ/c³rₛ

      */

     /*
      G : m³ / kg s²   =  m⁵ / J·s⁴    =    m c⁴ / J   (c⁴ / N)   c⁴ / F
          L³ / m t²

      kg = J·s / m²/s  = J·s² / m²


      F = Gm²/r²

      F = kq²/r²

      */

    /**
     * 普朗克力    Fₚ = Gmₚ²/lₚ²
     */
    /*
      F = c⁴/G       m⁴  / s⁴   =   J/m
                    J·s⁴ / m⁵
     */
    /** 真空能量密度  ρ = c⁴/ G·lₚ² */
    /*  Fₚ/lₚ²

     */

    /** vip 普朗克质量 mₚ² = ℏc / G */
    /*
      c = 1/√ε₀μ₀
     */

    /** Fₚ·lₚ = Eₚ */  // F = Gm²/r² = G ℏ²/c²λ⁴    G ℏ²/c²lₚ³ = Eₚ   G ℏ²ε₀μ₀/lₚ³ = Eₚ
                   // ℏfₚ */
    // G ℏ²ε₀μ₀/lₚ³ = hfₚ/2π    G 2πℏε₀μ₀ = fₚlₚ³   lₚ=c/fₚ   fₚ=c/lₚ
    // G ℏε₀μ₀ = c³/fₚ²
    // G = c³/ℏε₀μ₀fₚ²    G = lₚ²/ℏε₀μ₀ √ε₀μ₀       F = lₚ² ℏ²c²/λ₁λ₂ / (nlₚ)² ℏε₀μ₀ √ε₀μ₀ =  lₚ² ℏ/λ₁λ₂ / (nlₚ)² √ε₀μ₀ =  ℏ/λ₁λ₂ / n² √ε₀μ₀
    public static void main(String[] args) {
        // G = r²c³/ℏ
        double r = lp;     //  G: 6.67259E-11
        r = (2.176 * Math.pow(10, -8))/c * ℏ;    //  1.4969630658774433 E-41
        r = (2.176 * Math.pow(10, -8))/c * h;    //  5.909773305300895 E-40
        System.out.println("G: " + r * r * c * c * c / ℏ);

        System.out.println("ρ: " + Math.pow(c, 4) / (G * lp * lp) );    // 4.6350556553924966 E113

        System.out.println("Eₚ: 1.956 * 10^9 J");
        electronTest();

        System.out.println("actual G: " + G);
        System.out.println("theory: " + c*lp*lp/(2 * π * ℏ * ε0 * μ0));
        // G = lₚ²/ℏε₀μ₀ √ε₀μ₀
        System.out.println("fix: " + c*lp*lp/(ℏ * ε0 * μ0));
    }

    // 将电荷e 与能量E（质量m）关联
    // 荷质比最高粒子 电子

    public static void electronTest(){
    /*
          电子质量   m = ℏ/cλ                                                           +    -
              磁矩  μ = 0.5 * qcλ                                                          ↓
            角动量  S = 1/2 * mR²ω = 0.5 * ℏ     disk shape?    true vacuum             vacuum
                                                                                           ↓
          E = ℏc/λ = (2)Fλ                                                              ~    ~
          F = ℏc/(2)λ²
     */
        double m = 9.1093837015 * Math.pow(10, -31);
        double l = h/(c*m*2*π);
        System.out.println("F: " + ℏ*c/Math.pow(l, 2));

        System.out.println("F·λ: " + l*ℏ*c/Math.pow(l, 2));
        //ringForce(l);
    }
    // wrong
    public static void ringForce(double r){
        // F = Q / 16π²ε0r²
        System.out.println("F_e: " + e/(16*π*π*ε0*r*r));
    }

    /** 电磁波能量密度 u = 0.5( ε₀E + μ₀⁻¹B)
     *  真空中，电场和磁场满足关系 E=cB
     *
     */


}
