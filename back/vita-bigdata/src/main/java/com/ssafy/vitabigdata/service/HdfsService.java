package com.ssafy.vitabigdata.service;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class HdfsService {

    @Autowired
    private FileSystem fileSystem;

    public void uploadFile(MultipartFile file, String destPath) throws IOException {
        Path path = new Path(destPath + "/" + file.getOriginalFilename());
        FSDataOutputStream outputStream = fileSystem.create(path);
        InputStream inputStream = file.getInputStream();
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
        inputStream.close();
    }

}