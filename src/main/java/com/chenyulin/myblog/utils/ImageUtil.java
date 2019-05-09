package com.chenyulin.myblog.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     * 生成随机文件名当年月日小时分钟秒钟+5位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        // 获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }


    public static String saveToLocal(MultipartFile uploadFile, String path) {
        String imagePath = PathUtil.getImagePath();
        String trueFileName = uploadFile.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        //将图片名字进行重命名,产生随机的名字
        String fileName = getRandomFileName() + suffix;
        //构建图片在主机磁盘上的实际存储路径
        String fullPath = path + imagePath;

        File targetFile = new File(fullPath, fileName);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        if (uploadFile != null) {
            compressImageUseThumbnator(uploadFile, fullPath + fileName);
            return imagePath + fileName;
        } else {
            return imagePath + "hasNoImage";
        }
    }

    public static void compressImageUseThumbnator(MultipartFile uploadFile, String savePath) {
        //TODO 添加日志
        try {
            Thumbnails.of(uploadFile.getInputStream()).size(500, 500)
                    .toFile(savePath);
        } catch (IOException e) {
            System.out.println("获取图片输入流出错");
            System.out.println(e.getMessage());
        }
    }
}
