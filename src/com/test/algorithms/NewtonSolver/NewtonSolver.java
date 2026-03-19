package com.test.algorithms.NewtonSolver;

import java.util.function.Function;

/**
 * 牛顿迭代法求解器
 * 用于寻找 f(x) = 0 的近似解
 */
public class NewtonSolver {

    /**
     * 牛顿迭代法核心实现
     *
     * @param func          目标函数 f(x)
     * @param derivative    导数函数 f'(x)
     * @param initialGuess  初始猜测值 x₀
     * @param tolerance     误差容忍度 (如 1e-7)
     * @param maxIterations 最大迭代次数，防止死循环
     * @return              近似解
     * @throws RuntimeException 当无法收敛或导数为0时
     */
    public static double newtonMethod(
            Function<Double, Double> func,
            Function<Double, Double> derivative,
            double initialGuess,
            double tolerance,
            int maxIterations) {

        double x = initialGuess;
        int iteration = 0;

        System.out.printf("初始猜测: x₀ = %.6f%n", x);

        while (iteration < maxIterations) {
            double f_x = func.apply(x);
            double f_prime_x = derivative.apply(x);

            // 检查导数是否为0（避免除零错误）
            if (Math.abs(f_prime_x) < 1e-12) {
                throw new RuntimeException(
                        String.format("导数接近零 (f'(%f) ≈ %f)，无法继续迭代", x, f_prime_x)
                );
            }

            // 计算下一个近似值: x_{n+1} = x_n - f(x_n)/f'(x_n)
            double x_next = x - f_x / f_prime_x;

            System.out.printf("迭代 %d: x = %.10f, f(x) = %.10f%n",
                    iteration + 1, x_next, func.apply(x_next));

            // 检查是否收敛
            if (Math.abs(x_next - x) < tolerance) {
                System.out.printf("✅ 收敛成功！共 %d 次迭代%n", iteration + 1);
                return x_next;
            }

            x = x_next;
            iteration++;
        }

        throw new RuntimeException(
                String.format("未在 %d 次迭代内收敛，当前值 x = %f", maxIterations, x)
        );
    }

    /**
     * 简化版：使用默认参数
     */
    public static double newtonMethod(
            Function<Double, Double> func,
            Function<Double, Double> derivative,
            double initialGuess) {
        return newtonMethod(func, derivative, initialGuess, 1e-7, 1000);
    }

}
