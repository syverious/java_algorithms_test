package com.test.quantumMechanics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 路径积分 模拟万有引力 绘制数据点导出
 * @author loftingyoung
 * 2025-3-31
 */
public class QuantumMechanicsPlot {

    private static double epsilon = 1e-10;

    // config
    private static int[] centerOfSpace = { 0, 0, 0 }; // 中心位置
    private static double radiusOfSpace = 320; // 180,
    private static double wavelength = 10; // 波长 λ = h/p = h/mv
    private static double[] centerOfObject = { 160, 0, 0 }; // 圆形物体中心位置
    private static double radiusOfObject = 160; // 圆形物体半径

    private static int drawWidth = 800;
    private static int drawHeight = 640;
    private static int drawDepth = 320;
    private static int centerX = 400;
    private static double centerY = 320;
    private static int centerZ = 160;

    private static final int NUM_THREADS = 12; // 定义线程数目
    // private static final int ARRAY_SIZE= 1000;// 定义数组大小 String[] args

    public static void test() throws InterruptedException {
        int ARRAY_SIZE = 1000;

        int[] array = new int[ARRAY_SIZE];
        int[] result = new int[ARRAY_SIZE];
        // 1 初始化数组
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = i + 1;
        }
        Thread[] threads = new Thread[NUM_THREADS];

        // 1 创建线程并启动
        for (int i = 0; i < NUM_THREADS; i++) {
            final int start = i * (ARRAY_SIZE / NUM_THREADS);
            final int end = (i == NUM_THREADS - 1) ? ARRAY_SIZE : (i + 1) * (ARRAY_SIZE / NUM_THREADS);
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    result[j] = array[j] * array[j];
                }
            });
            threads[i].start();
        }

        // 等待所有钱强执行完感
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }
        // 输出结累
        System.out.println(Arrays.toString(result));
    }

    public class Complex {

        private double re;
        private double im;

        public Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public Complex() {
            this.re = 0;
            this.im = 0;
        }

        public Complex add(Complex a, Complex b) {
            return new Complex(a.re + b.re, a.im + b.im);
        }

        public Complex add(Complex other) {
            return new Complex(this.re + other.re, this.im + other.im);
        }

        public Complex mul(Complex a, Complex b) {
            return new Complex(
                    a.re * b.re - a.im * b.im,
                    a.re * b.im + a.im * b.re);
        }

        public Complex phase(double theta) { // 欧拉公式实现相位因子
            return new Complex(Math.cos(theta), Math.sin(theta));
        }

        // normalize
        public double norm() {
            return Math.sqrt(this.re * this.re + this.im * this.im);
        }

        // magnitude
        public double magnitudeSq() {
            return this.re * this.re + this.im * this.im;
        }

        public Complex conjugate() {
            return new Complex(this.re, -this.im);
        }
    }

    // 复数形式的路径积分计算
    public double calculateProbability(int dx, int dy, double raduisSquare) { // int dz,
        Complex total = new Complex();

        // 将目标点坐标转换为空间坐标
        double dxSpace = dx - centerX;
        double dySpace = 0; //dy - centerY;
        // double dzSpace = dz - centerZ;

        // int z = 81;
        // double zSpace = z - centerZ;

        for (int x = 0; x < drawWidth; x++) {
            for (int y = 0; y < drawHeight; y++) {
                for (int z = 0; z < drawDepth; z++) {
                    // 进行坐标转换，将画布坐标转换为空间坐标
                    double xSpace = x - centerX;
                    double ySpace = y - centerY;
                    double zSpace = z - centerZ;

                    // 计算粒子从圆形物体中发射路径叠加概率
                    if (Math.pow(xSpace - centerOfObject[0], 2) +
                            Math.pow(ySpace - centerOfObject[1], 2) +
                            Math.pow(zSpace - centerOfObject[2], 2) < raduisSquare &&  // dx - centerOfObject[0] > radiusOfObject){
                            Math.pow(dxSpace - centerOfObject[0], 2) +
                            Math.pow(centerOfObject[1], 2) > raduisSquare) {

                        double distance = Math.sqrt(
                                Math.pow(xSpace - dxSpace, 2) +
                                        Math.pow(ySpace, 2) +
                                        Math.pow(-zSpace, 2));

                        // 计算波函数（包含相位和振幅衰减）
                        double phase = (2 * Math.PI / wavelength) * distance;
                        /** 避免除零错误 */
                        double amplitude = 1 / (distance + epsilon); // Math.sqrt(e); // 球面波衰减
                        // 构建复数波函数
                        Complex psi = new Complex(
                                amplitude * Math.cos(phase),
                                amplitude * Math.sin(phase));
                        total = total.add(psi);
                        // Complex psi = Complex.mul(
                        // new Complex(amplitude, 0),
                        // Complex.phase(phase)
                        // );
                        // total = Complex.add(total, psi);
                    }
                }
            }
        }

        return total.magnitudeSq();
    }


    // 主函数 绘制路径积分图像
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long s = System.currentTimeMillis();
        String formatted = sdf.format(s);
        System.out.println("===============start: " + formatted);
        QuantumMechanicsPlot pathIntegral = new QuantumMechanicsPlot();
        pathIntegral.drawInterferencePatternMulti();
        long d = System.currentTimeMillis();
        formatted = sdf.format(d);
        System.out.println("===============end: " + formatted);
        System.out.println("===============cost: " + (d - s) + " ms");
    }

    // 绘制干涉图样（带自动归一化）
    public void drawInterferencePatternMulti() throws InterruptedException {

        // 修复1：正确初始化二维概率数组
        // double[][] probMap = new double[drawWidth][]; // [drawHeight][drawDepth];
        // for (int x = 0; x < drawWidth; x++) {
        // probMap[x] = new double[drawHeight];
        // }
        double[] probMap = new double[drawWidth];
        AtomicReference<Double> maxProb = new AtomicReference<>((double) 0);

        // const centerX = canvas.width / 2;
        // const centerY = canvas.height / 2;
        // double centerSquare = radiusOfSpace * radiusOfSpace;
        double raduisSquare = radiusOfObject * radiusOfObject;

        Thread[] threads = new Thread[NUM_THREADS];

        // 1 创建线程并启动
        for (int i = 0; i < NUM_THREADS; i++) {
            final int start = i * (drawWidth / NUM_THREADS);
            final int end = (i == NUM_THREADS - 1) ? drawWidth : (i + 1) * (drawWidth / NUM_THREADS);
            int finalI = i;
            threads[i] = new Thread(() -> {
                // 计算粒子路径积分
                for (int x = start; x < end; x++) { // drawWidth
                    for (int y = 0; y < drawHeight; y++) {
                        // for (int z = 0; z < drawDepth; z++) {
                        // 仅计算中心区域范围概率, 结果只保留 z = 81 的平面
                        // if (Math.abs(x - centerX) > config.radiusOfSpace[0] || Math.abs(y - centerY)
                        // > config.centerOfSpace) {
                        // probMap[x][y] = 0;
                        // continue;
                        // }
                        // int z =
                        // 使用欧氏距离判断空间范围
                        double dx = x - centerX;
                        // double dy = y - centerY;
                        // double dz = z - centerZ;
                        // if (dx * dx + dy * dy + dz * dz> centerSquare) {
                        // probMap[x][y] = 0; //epsilon
                        // continue;
                        // }
                        // 改为立方体范围
                        if (y != centerY || Math.abs(dx) > radiusOfSpace) { // || Math.abs(dz) > radiusOfSpace
                            // probMap[x] = 0; // epsilon
                            continue;
                        } else {
                            double prob = calculateProbability(x, y, raduisSquare);
                            probMap[x] = prob;
                            maxProb.set(Math.max(maxProb.get(), prob));
                        }

                        // 采用镜像翻转减少一半计算
                        // if(y > centerY) {
                        //
                        // } else {
                        //
                        // }
                        // }
                    }
                    if (x % 10 == 0)
                        System.out.println(finalI + ": " + x + " pix");
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完整
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("max: " + maxProb.get());

        System.out.println("\n");

        for (int x = 0; x < drawWidth; x++) {
            probMap[x] = (probMap[x] /maxProb.get()) * 100;
        }

        System.out.println(probMap[drawWidth/2]);

        saveProbMapToFile(probMap, "probMap_results.txt");

        // // 创建BufferedImage绘制灰度图
        // BufferedImage image = new BufferedImage(drawWidth, drawHeight,
        // BufferedImage.TYPE_BYTE_GRAY);
        // WritableRaster raster = image.getRaster();
        //
        // // 绘制概率分布
        // // 绘制中间平面
        // int z = centerZ; // z=81的平面
        // for (int x = 0; x < drawWidth; x++) {
        // for (int y = 0; y < drawHeight; y++) {
        // // 归一化并转换为灰度值(0-255)
        // int grayValue = (int)(255 * Math.sqrt(probMap[x][y] / maxProb.get()));
        // grayValue = Math.min(255, Math.max(0, grayValue)); // 确保在0-255范围内
        // raster.setSample(x, y, 0, grayValue);
        // }
        // }
        //
        // // 保存图像文件
        // try {
        // File output = new File("quantum_interference_sphere_high_res.png");
        // ImageIO.write(image, "png", output);
        // System.out.println("图像已保存到: " + output.getAbsolutePath());
        // } catch (IOException e) {
        // System.err.println("保存图像时出错: " + e.getMessage());
        // }

    }

    // 保存概率数组到文件
    private void saveProbMapToFile(double[] probMap, String filename) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(filename)) {
            for (double value : probMap) {
                writer.println(value);
            }
            System.out.println("概率数组已保存到: " + new File(filename).getAbsolutePath());
        } catch (IOException e) {
            System.err.println("保存文件时出错: " + e.getMessage());
        }
    }

    // 从文件读取概率数组
    public double[] loadProbMapFromFile(String filename) throws IOException {
        java.util.List<Double> probList = new java.util.ArrayList<>();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                probList.add(Double.parseDouble(line));
            }
        }
        double[] probMap = new double[probList.size()];
        for (int i = 0; i < probList.size(); i++) {
            probMap[i] = probList.get(i);
        }
        System.out.println("已从文件加载概率数组，长度: " + probMap.length);
        return probMap;
    }

}
