package com.alkemy.ong.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IAWSS3Service {
    String uploadImage(MultipartFile multipartFile) throws Exception;
    String uploadImage(File file) throws Exception;
}
