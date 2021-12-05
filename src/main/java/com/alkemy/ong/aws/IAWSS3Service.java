package com.alkemy.ong.aws;

import com.alkemy.ong.aws.utils.ValidImage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAWSS3Service {
    String uploadImage(MultipartFile multipartFile) throws Exception;
}
