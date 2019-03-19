package com.chenyulin.myblog.utils;

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
     * @return
     */
    public static String getRandomFileName() {
        // 获取随机五位数
        int rannum = r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }


    public static String saveToLocal(MultipartFile file,String path){
        String imagePath = PathUtil.getImagePath();
        String trueFileName = file.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = getRandomFileName()+suffix;
        String fullPath = path+imagePath;
        File targetFile = new File(fullPath,fileName);
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(fullPath+fileName));
            return imagePath+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
