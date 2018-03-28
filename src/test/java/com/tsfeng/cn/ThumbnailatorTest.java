package com.tsfeng.cn;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @author tsfeng
 * @version 创建时间 2018/3/28 14:12
 */
public class ThumbnailatorTest {

    public static void main(String[] args) throws Exception{
//        Thumbnails.of("F:/imgTest/old.jpg").size(200, 300).toFile("F:/imgTest/image_200x300.jpg");
//        Thumbnails.of("F:/imgTest/old.jpg").size(75, 75).keepAspectRatio(false).toFile("F:/imgTest/image_75x75.jpg");
//        Thumbnails.of("F:/imgTest/old.jpg").size(60, 60).keepAspectRatio(false).toFile("F:/imgTest/image_60x60.jpg");
//        Thumbnails.of("F:/imgTest/old.jpg").scale(0.25f).toFile("F:/imgTest/image_25%.jpg");
//        Thumbnails.of("F:/imgTest/old.jpg").scale(0.5f).toFile("F:/imgTest/image_50%.jpg");
//        Thumbnails.of("F:/imgTest/old.jpg").scale(0.5f).outputFormat("jpg").toFile("F:/imgTest/image_50%.jpg");

        Thumbnails.of("F:/imgTest/old.jpg").scale(0.1f).outputQuality(1f).toFile("F:/imgTest/image_10%.jpg");
        System.out.println("success");
    }


}
