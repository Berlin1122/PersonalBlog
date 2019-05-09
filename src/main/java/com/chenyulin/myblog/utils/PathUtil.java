package com.chenyulin.myblog.utils;


public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "E:/myblog/upload";
        } else {
            basePath = "/home/chenyulin/myblog/upload";
        }
        return basePath;
    }

    public static String getImagePath() {
        String imagePath = "/image/";
        return imagePath;
    }
}
