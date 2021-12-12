package com.alkemy.ong.util;

import io.jsonwebtoken.lang.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageExtensionValidator implements ConstraintValidator<ImageExtension, String> {

    @Override
    public void initialize(ImageExtension imageExtension) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        boolean isPNG = Strings.endsWithIgnoreCase(value, ContentTypeEnum.PNG.getFileExtension());
        boolean isJPG = Strings.endsWithIgnoreCase(value, ContentTypeEnum.JPG.getFileExtension());
        boolean isJPEG = Strings.endsWithIgnoreCase(value, ContentTypeEnum.JPEG.getFileExtension());

        if(isPNG || isJPEG || isJPG){
            return true;
        }
        return false;
    }
}
