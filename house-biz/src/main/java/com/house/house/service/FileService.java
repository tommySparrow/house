package com.house.house.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/10/26
 * @ Description：
 * @ throws
 */
@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;

    public List<String> getImgPaths(List<MultipartFile> files){

        //校验filePath是否有值
        if (Strings.isNullOrEmpty(filePath)){
            //获取绝对路径
            filePath = getAbsolutePath();
        }

        List<String> paths = Lists.newArrayList();
        files.forEach(file -> {
            File localFile = null;
            if (!file.isEmpty()) {
                try {
                    localFile =  saveToLocal(file);
                    String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
                    paths.add(path);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return paths;
    }

    //获取绝对路径
    private String getAbsolutePath() {
        //"." or "/"
        File file = new File(".");
        return file.getAbsolutePath();
    }

    private File saveToLocal(MultipartFile file) throws IOException {
        String fileUrl = filePath + "/" + Instant.now().getEpochSecond() +"/"+file.getOriginalFilename();
        File newFile = new File(fileUrl);
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        }
        Files.write(file.getBytes(), newFile);
        return newFile;
    }
}
