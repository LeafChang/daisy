package com.gs.leaf;

import java.util.ArrayList;
import java.util.List;

public class LiuHuanDemo {

    public static void main(String[] args) {


        String size = "3 3";
        String template = "0,1 0,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2;1,1 2,1;1,2 2,2;2,0 2,1";

        //矩阵大小为 (2*x+1,2*y+1)
        int l = Integer.valueOf(size.split(" ")[0]);
        int h = Integer.valueOf(size.split(" ")[1]);
        int[][] lengthAndHeight = new int[change2(l)][change2(h)];

        //所有相连的两点
        String[] allBananaPoints = template.split(";");

        //计算交点的集合
        List<String> pointList = calBananaPoint(allBananaPoints);

        System.out.println(pointList.toString());

        for (int i = 0, y = lengthAndHeight.length; i < y; i++) { //外层遍历的是Y（竖）方向
            for (int j = 0, x = lengthAndHeight.length; j < x; j++) { //内层遍历的是X（横）方向
                if ((j == x - 1)) { //X最后一个永远是不带空格的[W] 且要换行
                    System.out.println("[W]");
                } else {
                    boolean isR = false;
                    String point = i+","+j; //将当前点拼接成有(y,x)，便于判断是否是交点
                    if (pointList.contains(point)){ //当前点是交点
                        isR = true;
                    }

                    //(j % 2 == 1 && i % 2 == 1) 这个条件是3*3 矩阵 应有的R，isR 是交点
                    if ((j % 2 == 1 && i % 2 == 1) || isR) {
                        System.out.print("[R] ");
                    } else {
                        System.out.print("[W] ");
                    }


                }
            }
        }


    }

    private static List<String> calBananaPoint(String[] allBananaPoints) {
        List<String> res = new ArrayList<String>();
        for (int i = 0, size = allBananaPoints.length; i < size; i++) {
            String twoPoint = allBananaPoints[i]; // 两个相连点 "0,1 0,2"
            String[] twoPointArry = twoPoint.split(" "); //"0,1"
            int y1 = Integer.parseInt(twoPointArry[0].split(",")[0]); //0 输入的坐标是 y,x
            int x1 = Integer.parseInt(twoPointArry[0].split(",")[1]); //1
            int y2 = Integer.parseInt(twoPointArry[1].split(",")[0]); //0
            int x2 = Integer.parseInt(twoPointArry[1].split(",")[1]); //2
            //计算交点坐标
            res.add(String.valueOf((change2(y2) + change2(y1)) / 2)+ "," + String.valueOf(((change2(x2)) + change2(x1)) / 2));
            System.out.println("交点坐标为：" + res.get(i));
        }
        return res;
    }

    private static int change2(int x2) {
        return 2 * x2 + 1;
    }


}
