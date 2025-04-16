package com.test.quantumMechanics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 函数图像绘制 2025-4-4
 * 读取png像素 或txt点数据
 * aided by DeepSeek-r1
 */
public class OutputPlotter {

    public static void main(String[] args) {
        String path = "D:/Code/java_algorithms_test/quantum_interference_sphere_high_res_10_cut.png"; // 修改为你的图片路径
        int y = 321; // 修改为需要的y坐标
        int radius = 160;

        //path = "D:/Code/java_algorithms_test/quantum_interference_large.png"; // 修改为你的图片路径
        //int y = 900; // 修改为需要的y坐标

        try {
            //BufferedImage image = ImageIO.read(new File(path));
            //int width = image.getWidth();
            //int height = image.getHeight();

//            if (y < 0 || y >= height) {
//                System.err.println("错误：y坐标超出图片范围 (0-" + (height-1) + ")");
//                return;
//            }

            //int[] grayValues = getGrayValues(image, y)[0];
            //int[] curveValue = getGrayValues(image, y)[1];

            double[] grayValues = loadProbMapFromFile("D:/Code/java_algorithms_test/probMap_results.txt");
            int width = grayValues.length;

            double[] curveValue = getMinAndMax(grayValues);

            double[] targetValues = getTargetValues(curveValue[0], curveValue[1], radius + (width/2) , radius);

            SwingUtilities.invokeLater(() -> createAndShowGUI(grayValues, targetValues, y));

        } catch (IOException e) {
            System.err.println("无法读取图片文件: " + e.getMessage());
        }
    }
    // 从文件读取概率数组
    public static double[] loadProbMapFromFile(String filename) throws IOException {
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

    private static int[][] getGrayValues(BufferedImage image, int y) {
        int width = image.getWidth();
        int[] grayValues = new int[width];
        int[] minAndMax = new int[2];
        int min = 255;
        int max = 0;

        for (int x = 0; x < width; x++) {
            int rgb = image.getRGB(x, y);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;
            int gray = (int)(0.299 * r + 0.587 * g + 0.114 * b);
            grayValues[x] = gray;
            if(gray != 0 && gray < min) min = gray;
            if(gray > max) max = gray;
        }
        minAndMax[0] = min;
        minAndMax[1] = max;
        return new int[][] {grayValues, minAndMax};
    }

    private static void createAndShowGUI(double[] grayValues, double[] targetValues, int y) {
        JFrame frame = new JFrame("灰度值曲线 - y=" + y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphPanel panel = new GraphPanel(grayValues, targetValues);
        frame.add(panel);

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static double[] getTargetValues(double minTarget, double maxTarget, int origin, int radius) {
        int width = origin - radius;

        double[] grayValues = new double[width];

        double paramA = 1.0;
        double paramB = 1.0;
        double param = 1.0;
        double min = 255;
        double max = 0;

        for (int x = 80; x < 400 - 3; x++) {
            double gray = 100 / Math.pow((origin - x), 2);   //4.4   00000
            grayValues[x] = gray;
            if(gray != 0 && gray < min) min = gray;
            if(gray > max) max = gray;
        }

        System.out.println("min: " + minTarget + "   " + min);
        System.out.println("max: " + maxTarget + "   " + max);

        paramA = minTarget/min;
        paramB = maxTarget/max;
        param = (paramA + paramB)/2;

        System.out.println("paramA: " + paramA);
        System.out.println("paramB: " + paramB);

        for (int x = 0; x < width; x++) {
            grayValues[x] = grayValues[x] * (param);  //  - 9999    576000
        }
        // 归一化并转换为灰度值(0-255)
//        for (int x = 0; x < width; x++) {
//            grayValues[x] = (160 * Math.sqrt(grayValues[x] / max));
//        }

        return grayValues;
    }
    public static double[] getMinAndMax(double[] grayValue){
        double[] res = {255,0};
        for(double d : grayValue){
            if(d != 0 && d < res[0]){
                res[0] = d;
            } else if(d > res[1]) {
                res[1] = d;
            }
        }
        return res;
    }
}

class GraphPanel extends JPanel {
    private final double[] grayValues;
    private final double[] targetValues;

    public GraphPanel(double[] grayValues, double[] targetValues) {
        this.grayValues = grayValues;
        this.targetValues = targetValues;
    }

    public GraphPanel(double[] grayValues) {
        this.grayValues = grayValues;
        this.targetValues = new double[] {0.0};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int margin = 50;
        int graphWidth = panelWidth - 2 * margin;
        int graphHeight = panelHeight - 2 * margin;

        // 绘制坐标轴
        g2d.setColor(Color.BLACK);
        // Y轴
        g2d.drawLine(margin, margin, margin, panelHeight - margin);
        // X轴
        g2d.drawLine(margin, panelHeight - margin, panelWidth - margin, panelHeight - margin);

        // 绘制标签
        g2d.drawString("X Coordinate", panelWidth/2 - 40, panelHeight - margin/2 + 20);
        g2d.drawString("Gray Value (0-255)", margin - 45, margin/2);

        // 绘制刻度
        drawAxisMarkers(g2d, margin, panelWidth, panelHeight, graphHeight);

        // 绘制曲线
        if (grayValues.length == 0) return;

        g2d.setColor(Color.BLUE);
        double xScale = (double) graphWidth / (grayValues.length - 1);
        double yScale = (double) graphHeight / 125;

        for (int x = 1; x < grayValues.length; x++) {
            int x1 = margin + (int)(x * xScale);
            int y1 = panelHeight - margin - (int)(grayValues[x] * yScale);
            int x0 = margin + (int)((x-1) * xScale);
            int y0 = panelHeight - margin - (int)(grayValues[x-1] * yScale);
            g2d.drawLine(x0, y0, x1, y1);
        }

        int offset = 3;
        // 绘制目标曲线
        if (targetValues.length == 0) return;

        g2d.setColor(Color.RED);
        //xScale = (double) graphWidth / (targetValues.length - 1);
        yScale = (double) graphHeight / 125;

        for (int x = 1; x < targetValues.length; x++) {
            int x1 = margin + (int)(x * xScale);
            int y1 = panelHeight - margin - (int)(targetValues[x] * yScale) + offset;
            int x0 = margin + (int)((x-1) * xScale);
            int y0 = panelHeight - margin - (int)(targetValues[x-1] * yScale) + offset;
            g2d.drawLine(x0, y0, x1, y1);
        }

    }

    private void drawAxisMarkers(Graphics2D g2d, int margin, int width, int height, int graphHeight) {
        // X轴刻度
        g2d.drawString("0", margin, height - margin + 20);
        g2d.drawString(String.valueOf(grayValues.length - 1), width - margin - 20, height - margin + 20);

        // Y轴刻度
        for (int i = 0; i <= 255; i += 32) {
            int yPos = height - margin - (int)(i * (graphHeight / 255.0));
            g2d.drawString(String.valueOf(i), margin - 40, yPos + 5);
            g2d.drawLine(margin - 5, yPos, margin, yPos);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    private static double[] getTargetValuesBak(int minTarget, int maxTarget, int origin, int radius) {
        int width = origin - radius;

        double[] grayValues = new double[width];

        double paramA = 1.0;
        double paramB = 1.0;
        double param = 1.0;
        double min = 255;
        double max = 0;

        for (int x = 0; x < width; x++) {
            double gray = 255/Math.pow((origin - x) * 0.7, 2);   //4.4
            grayValues[x] = gray;
            if(gray != 0 && gray < min) min = gray;
            if(gray > max) max = gray;
        }

        System.out.println("min: " + minTarget + "   " + min);
        System.out.println("max: " + maxTarget + "   " + max);

        paramA = minTarget/min;
        paramB = maxTarget/max;
        param = (paramA + paramB)/2;

        System.out.println("paramA: " + paramA);
        System.out.println("paramB: " + paramB);

        for (int x = 0; x < width; x++) {
            grayValues[x] = grayValues[x]*(param - 9999);  //576000
        }

        return grayValues;
    }

}