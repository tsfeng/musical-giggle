package com.tsfeng.cn.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

/**
 * 多张图片合并成九宫格图像
 * @author tsfeng
 * @version 创建时间 2017/10/18 17:57
 */
public class ImageUtils {

    /**
     * 最大每行图片数量
     */
    private static final int MAX_ROW_PIC_NUM = 3;
    /**
     * 最大合并图片数量
     */
    private static final int MAX_MERGE_PIC_NUM = 9;
    /**
     * 合并后的图片除去补白的宽度和
     */
    private static final int ALL_PIC_WIDTH = 120;
    /**
     * 合并后的图片宽度
     */
    private static final int DRAWING_BOARD_WIDTH = 132;
    private static final int PIC_NUM_ONE = 1;
    private static final int PIC_NUM_TWO = 2;
    private static final int PIC_NUM_THREE = 3;
    private static final int PIC_NUM_FOUR = 4;
    private static final int PIC_NUM_FIVE = 5;
    private static final int PIC_NUM_SIX = 6;
    private static final int PIC_NUM_SEVEN = 7;
    private static final int PIC_NUM_EIGHT = 8;
    private static final int PIC_NUM_NINE = 9;

    /**
     * 网络图片判断
     */
    private static final String HTTP_URL = "http://";
    private static final String HTTPS_URL = "https://";

    public static void main(String[] args) {
//        List<String> urlList = new ArrayList<>();
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508403431917&di=d09f81b32790a0951bf92277d6c98dd4&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D758c3cb7d743ad4ba67b4ec4b7327699%2F574e9258d109b3de303d257acabf6c81800a4c26.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508403431917&di=d09f81b32790a0951bf92277d6c98dd4&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D758c3cb7d743ad4ba67b4ec4b7327699%2F574e9258d109b3de303d257acabf6c81800a4c26.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508403431917&di=d09f81b32790a0951bf92277d6c98dd4&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D758c3cb7d743ad4ba67b4ec4b7327699%2F574e9258d109b3de303d257acabf6c81800a4c26.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508403431917&di=d09f81b32790a0951bf92277d6c98dd4&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D758c3cb7d743ad4ba67b4ec4b7327699%2F574e9258d109b3de303d257acabf6c81800a4c26.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508403431917&di=d09f81b32790a0951bf92277d6c98dd4&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D758c3cb7d743ad4ba67b4ec4b7327699%2F574e9258d109b3de303d257acabf6c81800a4c26.jpg");
//        getMergedPic(urlList, "F:\\m.jpg");
    }

    /**
     * 合并图片
     * @param urlList 图片路径
     * @return 合并后的图片
     */
    public static void getMergedPic(List<String> urlList, String outUrl){
        //最多取前9张图片
        int mergePicNum = urlList.size();
        if (mergePicNum > MAX_MERGE_PIC_NUM) {
            mergePicNum = MAX_MERGE_PIC_NUM;
        }
        int rowNum = getRowNum(mergePicNum);
        int maxPicNumPerRow = new BigDecimal(mergePicNum).divide(new BigDecimal(rowNum), 2).intValue();
        int perPicWidth = ALL_PIC_WIDTH / maxPicNumPerRow;
        BufferedImage outImage = new BufferedImage(DRAWING_BOARD_WIDTH, DRAWING_BOARD_WIDTH, BufferedImage.TYPE_INT_RGB);
        //设置背景为白色
        for (int m = 0; m < DRAWING_BOARD_WIDTH; m++) {
            for (int n = 0; n < DRAWING_BOARD_WIDTH; n++) {
                outImage.setRGB(m, n, 0xFFFFFF);
            }
        }

        String[] imageSize = getStartXy(mergePicNum);
        for (int i = 0; i < mergePicNum; i++) {
            String size = imageSize[i];
            String[] sizeArr = size.split(",");
            int x = Integer.parseInt(sizeArr[0]);
            int y = Integer.parseInt(sizeArr[1]);
            BufferedImage sourceBufferedImage = ImageUtils.resize(urlList.get(i), perPicWidth, perPicWidth);
            int[] tempImageArray = new int[perPicWidth*perPicWidth];
            if (sourceBufferedImage != null) {
                sourceBufferedImage.getRGB(0, 0, perPicWidth, perPicWidth, tempImageArray, 0, perPicWidth);
            }
            outImage.setRGB(x, y, perPicWidth, perPicWidth, tempImageArray, 0, perPicWidth);
        }
        try {
            ImageIO.write(outImage, outUrl.substring(outUrl.lastIndexOf(".") + 1), new File(outUrl));
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据图片数量返回图片的行数
     * @param picNum 图片数量
     * @return 图片的行数
     */
    public static int getRowNum(int picNum) {
        int rowNum = new BigDecimal(picNum).divide(new BigDecimal(MAX_ROW_PIC_NUM), 2).intValue();
        if (rowNum == 1 && picNum == MAX_ROW_PIC_NUM) {
            rowNum = 2;
        }
        return rowNum;
    }

    /**
     * 图片缩放
     * @param filePath 图片路径
     * @param outHeight 输出高度
     * @param outWidth 输出宽度
     * @return BufferedImage
     */
    public static BufferedImage resize(String filePath, int outHeight, int outWidth) {
        try {
            // 缩放比例
            BufferedImage sourceBufferedImage;
            if(filePath.indexOf(HTTP_URL)==0 || filePath.indexOf(HTTPS_URL)==0){
                sourceBufferedImage = ImageIO.read(new URL(filePath));
            }else{
                sourceBufferedImage = ImageIO.read(new File(filePath));
            }
            Image sourceImage = sourceBufferedImage.getScaledInstance(outWidth, outHeight, Image.SCALE_SMOOTH);
            BufferedImage outImage = new BufferedImage(outWidth, outHeight, sourceBufferedImage.getType());
            Graphics2D gc = outImage.createGraphics();
            //对指定的矩形区域填充颜色
            gc.setColor(Color.white);
            gc.fillRect(0, 0, outWidth, outHeight);
            gc.drawImage(sourceImage, 0, 0, null);
            //释放此图形的上下文并释放它所使用的所有系统资源
            gc.dispose();
            return outImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取坐标数组
     * @param size 总数量
     * @return 坐标数组
     */
    public static String[] getStartXy(int size) {
        String[] s = new String[size];
        int startX;
        int startY;
        switch (size) {
            case PIC_NUM_ONE:
                s[0] = "6,6";
                break;
            case PIC_NUM_TWO:
                startX = 4;
                s[0] = "4," + (132 / 2 - 60 / 2);
                s[1] = 60 + 2 * startX + "," + (132 / 2 - 60 / 2);
                break;
            case PIC_NUM_THREE:
                startX = startY = 4;
                s[0] = (132 / 2 - 60 / 2) + "," + startY;
                s[1] = startX + "," + (60 + 2 * startY);
                s[2] = (60 + 2 * startY) + "," + (60 + 2 * startY);
                break;
            case PIC_NUM_FOUR:
                startX = startY = 4;
                s[0] = startX + "," + startY;
                s[1] = (startX * 2 + 60) + "," + startY;
                s[2] = startX + "," + (60 + 2 * startY);
                s[3] = (60 + 2 * startY) + "," + (60 + 2 * startY);
                break;
            case PIC_NUM_FIVE:
                startX = startY = 3;
                s[0] = (132 - 40 * 2 - startX) / 2 + "," + (132 - 40 * 2 - startY) / 2;
                s[1] = ((132 - 40 * 2 - startX) / 2 + 40 + startX) + "," + (132 - 40 * 2 - startY) / 2;
                s[2] = startX + "," + ((132 - 40 * 2 - startX) / 2 + 40 + startY);
                s[3] = (startX * 2 + 40) + "," + ((132 - 40 * 2 - startX) / 2 + 40 + startY);
                s[4] = (startX * 3 + 40 * 2) + "," + ((132 - 40 * 2 - startX) / 2 + 40 + startY);
                break;
            case PIC_NUM_SIX:
                startX = startY = 3;
                s[0] = startX + "," + ((132 - 40 * 2 - startX) / 2);
                s[1] = (startX * 2 + 40) + "," + ((132 - 40 * 2 - startX) / 2);
                s[2] = (startX * 3 + 40 * 2) + "," + ((132 - 40 * 2 - startX) / 2);
                s[3] = startX + "," + ((132 - 40 * 2 - startX) / 2 + 40 + startY);
                s[4] = (startX * 2 + 40) + "," + ((132 - 40 * 2 - startX) / 2 + 40 + startY);
                s[5] = (startX * 3 + 40 * 2) + "," + ((132 - 40 * 2 - startX) / 2 + 40 + startY);
                break;
            case PIC_NUM_SEVEN:
                startX = startY = 3;
                s[0] = (132 - 40) / 2 + "," + startY;
                s[1] = startX + "," + (startY * 2 + 40);
                s[2] = (startX * 2 + 40) + "," + (startY * 2 + 40);
                s[3] = (startX * 3 + 40 * 2) + "," + (startY * 2 + 40);
                s[4] = startX + "," + (startY * 3 + 40 * 2);
                s[5] = (startX * 2 + 40) + "," + (startY * 3 + 40 * 2);
                s[6] = (startX * 3 + 40 * 2) + "," + (startY * 3 + 40 * 2);
                break;
            case PIC_NUM_EIGHT:
                startX = startY = 3;
                s[0] = (132 - 80 - startX) / 2 + "," + startY;
                s[1] = ((132 - 80 - startX) / 2 + startX + 40) + "," + startY;
                s[2] = startX + "," + (startY * 2 + 40);
                s[3] = (startX * 2 + 40) + "," + (startY * 2 + 40);
                s[4] = (startX * 3 + 40 * 2) + "," + (startY * 2 + 40);
                s[5] = startX + "," + (startY * 3 + 40 * 2);
                s[6] = (startX * 2 + 40) + "," + (startY * 3 + 40 * 2);
                s[7] = (startX * 3 + 40 * 2) + "," + (startY * 3 + 40 * 2);
                break;
            case PIC_NUM_NINE:
                startX = startY = 3;
                s[0] = startX + "," + startY;
                s[1] = startX * 2 + 40 + "," + startY;
                s[2] = startX * 3 + 40 * 2 + "," + startY;
                s[3] = startX + "," + (startY * 2 + 40);
                s[4] = (startX * 2 + 40) + "," + (startY * 2 + 40);
                s[5] = (startX * 3 + 40 * 2) + "," + (startY * 2 + 40);
                s[6] = startX + "," + (startY * 3 + 40 * 2);
                s[7] = (startX * 2 + 40) + "," + (startY * 3 + 40 * 2);
                s[8] = (startX * 3 + 40 * 2) + "," + (startY * 3 + 40 * 2);
                break;
            default:
        }
        return s;
    }
}
