package com.test.algorithms.Calculus;

/**
 * 符号求导表达式接口
 * 每个表达式节点都能对自身求导，返回新的表达式树
 */
interface Expression {
    /** 计算表达式在x处的值 */
    double eval(double x);

    /** 符号求导，返回导函数表达式 */
    Expression derivative();

    /** 打印表达式结构 */
    String toString();
}

/* ==================== 基础表达式节点 ==================== */

class Variable implements Expression {
    @Override
    public double eval(double x) {
        return x;
    }

    @Override
    public Expression derivative() {
        return new Const(1); // d/dx(x) = 1
    }

    @Override
    public String toString() {
        return "x";
    }
}

class Const implements Expression {
    private final double value;

    public Const(double value) {
        this.value = value;
    }

    @Override
    public double eval(double x) {
        return value;
    }

    @Override
    public Expression derivative() {
        return new Const(0); // d/dx(c) = 0
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

/* ==================== 算术运算节点 ==================== */

class Plus implements Expression {
    private final Expression left, right;

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval(double x) {
        return left.eval(x) + right.eval(x);
    }

    @Override
    public Expression derivative() {
        // d/dx(u + v) = u' + v'
        return new Plus(left.derivative(), right.derivative());
    }

    @Override
    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}

class Minus implements Expression {
    private final Expression left, right;

    public Minus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval(double x) {
        return left.eval(x) - right.eval(x);
    }

    @Override
    public Expression derivative() {
        // d/dx(u - v) = u' - v'
        return new Minus(left.derivative(), right.derivative());
    }

    @Override
    public String toString() {
        return "(" + left + " - " + right + ")";
    }
}

class Multiply implements Expression {
    private final Expression left, right;

    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval(double x) {
        return left.eval(x) * right.eval(x);
    }

    @Override
    public Expression derivative() {
        // 乘积法则: d/dx(u * v) = u' * v + u * v'
        return new Plus(
                new Multiply(left.derivative(), right),
                new Multiply(left, right.derivative())
        );
    }

    @Override
    public String toString() {
        return "(" + left + " * " + right + ")";
    }
}

class Divide implements Expression {
    private final Expression numerator, denominator;

    public Divide(Expression numerator, Expression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public double eval(double x) {
        return numerator.eval(x) / denominator.eval(x);
    }

    @Override
    public Expression derivative() {
        // 商法则: d/dx(u / v) = (u' * v - u * v') / v²
        return new Divide(
                new Minus(
                        new Multiply(numerator.derivative(), denominator),
                        new Multiply(numerator, denominator.derivative())
                ),
                new Power(denominator, new Const(2))
        );
    }

    @Override
    public String toString() {
        return "(" + numerator + " / " + denominator + ")";
    }
}

class Power implements Expression {
    private final Expression base, exponent;

    public Power(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public double eval(double x) {
        return Math.pow(base.eval(x), exponent.eval(x));
    }

    @Override
    public Expression derivative() {
        // 幂法则: d/dx(u^n) = n * u^(n-1) * u' (n为常数时)
        if (exponent instanceof Const) {
            double n = ((Const) exponent).eval(0);
            return new Multiply(
                    new Multiply(new Const(n),
                            new Power(base, new Const(n - 1))),
                    base.derivative() // 链式法则
            );
        }
        // 通用指数求导（需对数微分法，此处简化）
        throw new UnsupportedOperationException("非常数指数的幂函数求导未实现");
    }

    @Override
    public String toString() {
        return base + "^" + exponent;
    }
}

/* ==================== 常用函数节点 ==================== */

class Sin implements Expression {
    private final Expression arg;

    public Sin(Expression arg) {
        this.arg = arg;
    }

    @Override
    public double eval(double x) {
        return Math.sin(arg.eval(x));
    }

    @Override
    public Expression derivative() {
        // d/dx(sin(u)) = cos(u) * u'
        return new Multiply(new Cos(arg), arg.derivative());
    }

    @Override
    public String toString() {
        return "sin(" + arg + ")";
    }
}

class Cos implements Expression {
    private final Expression arg;

    public Cos(Expression arg) {
        this.arg = arg;
    }

    @Override
    public double eval(double x) {
        return Math.cos(arg.eval(x));
    }

    @Override
    public Expression derivative() {
        // d/dx(cos(u)) = -sin(u) * u'
        return new Multiply(
                new Const(-1),
                new Multiply(new Sin(arg), arg.derivative())
        );
    }

    @Override
    public String toString() {
        return "cos(" + arg + ")";
    }
}

/* ==================== 使用示例 ==================== */

public class SymbolicDifferentiator {
    public static void main(String[] args) {

        // 示例1: f(x) = √(x^4 + 16(16-x^2）)
        System.out.println("========== 示例1: f(x) = x² + 2x + 1 ==========");
        Expression f0 = new Power(new Plus(
                new Power(new Variable(), new Const(4)),
                new Multiply(new Plus(new Multiply(new Power(new Variable(), new Const(2)), new Const(-1)), new Const(16)), new Const(16))
        ), new Const(0.5));
        Expression f0Prime = f0.derivative();

        System.out.println("原函数: " + f0);
        System.out.println("导函数: " + f0Prime);
        //System.out.println("f'(0) = " + f0Prime.eval(0)); // 预期: 0 = 0
        //((0.5 * (Math.pow(x,4) + (((x*x * -1.0) + 16.0) * 16.0))^-0.5) * (((4.0 * x*x*x) * 1.0) + (((((((2.0 * x) * 1.0) * -1.0) + (x*x * 0.0)) + 0.0) * 16.0) + (((x*x * -1.0) + 16.0) * 0.0))))
        System.out.println();

        // 示例1: f(x) = x² + 2x + 1
        System.out.println("========== 示例1: f(x) = x² + 2x + 1 ==========");
        Expression f1 = new Plus(
                new Power(new Variable(), new Const(2)),
                new Plus(new Multiply(new Const(2), new Variable()), new Const(1))
        );
        Expression f1Prime = f1.derivative();

        System.out.println("原函数: " + f1);
        System.out.println("导函数: " + f1Prime);
        System.out.println("f'(3) = " + f1Prime.eval(3)); // 预期: 2*3 + 2 = 8
        System.out.println();

        // 示例2: f(x) = sin(x²)
        System.out.println("========== 示例2: f(x) = sin(x²) ==========");
        Expression f2 = new Sin(new Power(new Variable(), new Const(2)));
        Expression f2Prime = f2.derivative();

        System.out.println("原函数: " + f2);
        System.out.println("导函数: " + f2Prime);
        System.out.println("f'(π) = " + f2Prime.eval(Math.PI)); // 预期: 2π*cos(π²)
        System.out.println();

        // 示例3: f(x) = x³ * sin(x)
        System.out.println("========== 示例3: f(x) = x³ * sin(x) ==========");
        Expression f3 = new Multiply(
                new Power(new Variable(), new Const(3)),
                new Sin(new Variable())
        );
        Expression f3Prime = f3.derivative();

        System.out.println("原函数: " + f3);
        System.out.println("导函数: " + f3Prime);
        System.out.println("f'(1) = " + f3Prime.eval(1)); // 预期: 3*1²*sin(1) + 1³*cos(1)
        System.out.println();

        // 示例4: 验证导数正确性
        System.out.println("========== 验证 f(x) = e^x * cos(x) ==========");
        Expression f4 = new Multiply(
                new Power(new Const(Math.E), new Variable()), // e^x
                new Cos(new Variable())
        );
        Expression f4Prime = f4.derivative();

        double x = 1.0;
        double numericDeriv = (f4.eval(x + 1e-7) - f4.eval(x - 1e-7)) / (2e-7);
        double symbolicDeriv = f4Prime.eval(x);

        System.out.println("数值导数: " + numericDeriv);
        System.out.println("符号导数: " + symbolicDeriv);
        System.out.println("误差: " + Math.abs(numericDeriv - symbolicDeriv));
    }
}

// 补充: Exp类实现
class Exp implements Expression {
    private final Expression exponent;

    public Exp(Expression exponent) {
        this.exponent = exponent;
    }

    @Override
    public double eval(double x) {
        return Math.pow(Math.E, exponent.eval(x));
    }

    @Override
    public Expression derivative() {
        // d/dx(e^u) = e^u * u'
        return new Multiply(this, exponent.derivative());
    }

    @Override
    public String toString() {
        return "e^(" + exponent + ")";
    }
}
