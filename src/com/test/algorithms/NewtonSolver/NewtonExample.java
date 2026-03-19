package com.test.algorithms.NewtonSolver;

public class NewtonExample {
    public static void main(String[] args) {

        // 示例1: 求 √2 (解 x² - 2 = 0)
        System.out.println("========== 求 √2 ==========");
        double sqrt2 = NewtonSolver.newtonMethod(
                x -> x * x - 2,          // f(x) = x² - 2
                x -> 2 * x,              // f'(x) = 2x
                2.0,                     // 初始猜测
                1e-10,                   // 精度
                100
        );
        System.out.printf("结果: √2 ≈ %.10f%n%n", sqrt2);

        // 示例2: 解方程 cos(x) = x (求不动点)
        System.out.println("========== 解 cos(x) = x ==========");
        double cosSolution = NewtonSolver.newtonMethod(
                x -> Math.cos(x) - x,    // f(x) = cos(x) - x
                x -> -Math.sin(x) - 1,   // f'(x) = -sin(x) - 1
                0.5,                     // 初始猜测 (接近真实解)
                1e-10,
                100
        );
        System.out.printf("结果: x ≈ %.10f (验证: cos(x) ≈ %.10f)%n%n",
                cosSolution, Math.cos(cosSolution));

        // 示例3: 求立方根 (解 x³ - a = 0)
        System.out.println("========== 求 ³√27 ==========");
        double cubeRoot = NewtonSolver.newtonMethod(
                x -> x * x * x - 27,     // f(x) = x³ - 27
                x -> 3 * x * x,          // f'(x) = 3x²
                3.0,                     // 初始猜测
                1e-10,
                100
        );
        System.out.printf("结果: ³√27 ≈ %.10f%n", cubeRoot);
    }

}
