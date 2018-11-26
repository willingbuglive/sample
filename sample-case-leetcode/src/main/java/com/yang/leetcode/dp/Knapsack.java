package com.yang.leetcode.dp;

/**
 * 金典的01背包问题.
 * 有6件物品,物品编号，重量，价值如下:
 * 
 * 物品编号：a  b  c  d  e  f
 * 物品重量：0  1  3  2  6  2
 * 物品价值：0  2  5  3  10 4
 * 
 * 现在给你个承重为12的背包， 如何让背包里装入的物品具有最大的价值总和？
 *
 */
public class Knapsack {
	
	private int solution(int[] weight,int[] value,int bagWeight){
		int len = weight.length;
		
		//maxValue[i][j] 表示前i件中,若干个物品,放入重量为j的背包中的最大价值
		int[][] maxValue = new int[len][bagWeight+1];
		
		for(int i = 1; i< len; i++){
			
	        for( int j=1 ; j <= bagWeight; j++){
	        	//物品i的重量 > 背包承重j  说明不能放入 
	            if( weight[i] > j ) 
	            	maxValue[i][j] = maxValue[i-1][j]; //不能放入，则对于承重为j的背包，(在前i件物品中，最大的价值) = (在前i-1件物品中，最大的价值),因为不能放入嘛。
	            else{
	            	//如果放入物品i,这是获取到的最大价值, 这最大价值= 前i-1件物品中，重量为(j-weight[i])的背包的最大值 + 物品i的价值
	            	int newMaxValue = maxValue[i-1][j-weight[i]] + value[i];
	            	maxValue[i][j] = Math.max(maxValue[i-1][j] ,newMaxValue); 
	            }
	            	      
	        }   
		}
		
		return maxValue[len-1][bagWeight];
	}
	
	
	/***
	1、动态规划（DP）
	 　动态规划（Dynamic Programming，DP）与分治区别在于划分的子问题是有重叠的，解过程中对于重叠的部分只要求解一次，记录下结果，其他子问题直接使用即可，减少了重复计算过程。 
	 　另外，DP在求解一个问题最优解的时候，不是固定的计算合并某些子问题的解，而是根据各子问题的解的情况选择其中最优的。 
	　　动态规划求解具有以下的性质： 
	　　最优子结构性质、子问题重叠性质　　 
	　　最优子结构性质：最优解包含了其子问题的最优解，不是合并所有子问题的解，而是找最优的一条解线路，选择部分子最优解来达到最终的最优解。 
	　　子问题重叠性质：先计算子问题的解，再由子问题的解去构造问题的解（由于子问题存在重叠，把子问题解记录下来为下一步使用，这样就直接可以从备忘录中读取）。其中备忘录中先记录初始状态。

	2、求解思路
	　　①、将原问题分解为子问题（子问题和原问题形式相同，且子问题解求出就会被保存）； 
	　　②、确定状态：01背包中一个状态就是N个物体中第i个是否放入体积为V背包中； 
	　　③、确定一些初始状态（边界状态）的值； 
	　　④、确定状态转移方程，如何从一个或多个已知状态求出另一个未知状态的值。（递推型）

	3、01背包问题求解思路
	　　①、确认子问题和状态 
	　　01背包问题需要求解的就是，为了体积V的背包中物体总价值最大化，N件物品中第i件应该放入背包中吗？（其中每个物品最多只能放一件） 
	　　为此，我们定义一个二维数组，其中每个元素代表一个状态，即前ii个物体中若干个放入体积为V背包中最大价值。数组为：f[N][V]，其中fij表示前i件中若干个物品放入体积为j的背包中的最大价值。 
	　　②、初始状态 
	　　初始状态为f[0][0−V]和f[0−N][0]都为0，前者表示前0个物品（也就是空物品）无论装入多大的包中总价值都为0，后者表示体积为0的背包啥价值的物品都装不进去。 
	　　③、转移函数

		if (背包体积j小于物品i的体积)
		    f[i][j] = f[i-1][j] //背包装不下第i个物体，目前只能靠前i-1个物体装包
		else
		    f[i][j] = max(f[i-1][j], f[i-1][j-Vi] + Wi)
　　	最后一句的意思就是根据“为了体积V的背包中物体总价值最大化，N件物品中第ii件应该放入背包中吗？”转化而来的。
	Vi表示第ii件物体的体积，WiWi表示第ii件物品的价值。
	这样f[i-1][j]代表的就是不将这件物品放入背包，而f[i-1][j-Vi] + Wi则是代表将第i件放入背包之后的总价值，比较两者的价值，得出最大的价值存入现在的背包之中。
	
	 */
	public static void main(String[] args){
		
		int[] weight = new int[]{0,1,3,2,6,2};
		int[] value = new int[]{0,2,5,3,10,4};
		int bagWeight = 12;
		
		System.out.println(new Knapsack().solution(weight, value, bagWeight));
	}
	
	/**
	 * 以本题为例，maxValue的表格如下：
	 * 
	 *  	\背包体积 
	 * 物品编号 \      0  1  2  3  4  5  6  7  8  9  10  11 
	 *   0         0  0
	 *   1         0  1
	 *   2         0
	 *   3         0
	 *   4         0
	 *   5         0
	 *   6         0
	 */
	
}
