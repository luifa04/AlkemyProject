package com.alkemy.ong.aws;

import com.alkemy.ong.aws.utils.ValidImage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/s3")
public class AWSS3Controller {

        private final AWSS3ServiceImpl amazonClient;


        @PostMapping("/uploadImage")
        //@PreAuthorize("hasAnyRole(T(com.alkemy.ong.security.RoleEnum).ADMIN)")
        public ResponseEntity<String> uploadImage(@RequestPart("file") @ValidImage MultipartFile file) throws Exception{
            return new ResponseEntity<>(this.amazonClient.uploadFile(file), HttpStatus.CREATED);
        }

}
