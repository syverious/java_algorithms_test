package com.test.defination;

/**
 * 2025-4-16
 *
 */
public class ElectronOrbit {
    /** data gathered from Kimi */
    /** 角量子数 l */
    // 描述电子轨道角动量

    /* 电子轨道角动量L
     * L = Math.sqr(l(l+1))ℏ    ℏ 约化普朗克常数 h/2π
     *
     * l 取值范围为 0，1，2，..., n-1
     * 其中n是主量子数，表示电子所在壳层
     */

    /*
     // 角量子数的物理意义
     1. 轨道形状
     角量子数 l 决定了电子轨道的形状：
     l = 0（s 轨道）：轨道形状为球形对称，电子在空间中出现的概率密度呈球形分布。
     l = 1（p 轨道）：轨道形状为哑铃形，电子在空间中出现的概率密度沿着某个方向（通常是 x、y 或 z 方向）呈双峰分布。
     l = 2（d 轨道）：轨道形状更复杂，通常有四种不同的形状（如四叶草形、双哑铃形等）。
     l = 3（f 轨道）：轨道形状更为复杂，通常有七种不同的形状。
     */

    /*
     2. 轨道角动量
     对于 l = 0（s 轨道），轨道角动量 L = 0。
     对于 l = 1（p 轨道），轨道角动量 L = √2ℏ。
     对于 l = 2（d 轨道），轨道角动量 L = √6ℏ。
     */

    /*
     c. 能级结构
     角量子数 l 也影响电子的能级结构。在多电子原子中，具有相同主量子数 n 但不同角量子数 l 的电子能级是不同的。
     这是因为电子之间的屏蔽效应和相对论效应会导致能级的分裂。例如：
     对于 n=2，l=0 的 2S 轨道和 l=1 的 2P 轨道的能级在多电子原子中是不同的。
     在氢原子中，虽然 2S 和 2P 轨道在经典量子力学中是简并的，但由于量子电动力学效应（如兰姆位移），它们的能级也会出现微小的分裂。
     */

     /** 主量子数 n */
     /*
      1. 定义
      主量子数 n 是一个正整数，用于描述电子在原子中的能级和轨道大小。它取值范围为：
                                n = 1, 2, 3,…
      其中，n=1 对应于最低的能级（基态），n=2 对应于第一激发态，以此类推。

      */

     /*
      能级
      主量子数 n 决定了电子的能级。在氢原子中，电子的能级 E 与主量子数 n 的关系为：
                             E = −13.6 eV / n²
      其中，13.6 eV 是氢原子的电离能。例如：
      对于 n = 1，能级 E = −13.6 eV。
      对于 n = 2，能级 E = −3.4 eV。
      对于 n = 3，能级 E = −1.51 eV。
      在多电子原子中，能级的计算更为复杂，但主量子数 n 仍然是决定电子能级的重要因素。
      */

     /*
      轨道大小
      对于 n = 1（1S 轨道），电子在原子核附近的概率密度最高。
      对于 n = 2（2S 和 2P 轨道），电子的平均距离原子核更远。
      对于 n = 3（3S、3P 和 3D 轨道），电子的平均距离原子核更远。
      */

     //电子的平均距离 ⟨r⟩ 与 n² 成正比。
}