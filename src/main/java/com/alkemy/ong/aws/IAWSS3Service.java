package com.alkemy.ong.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAWSS3Service {
    String uploadFile(MultipartFile multipartFile) throws Exception;
}
