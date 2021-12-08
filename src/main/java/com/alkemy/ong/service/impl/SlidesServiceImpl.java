package com.alkemy.ong.service.impl;

import com.alkemy.ong.aws.IAWSS3Service;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.dto.SlideRequest;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.service.ISlidesService;
import com.alkemy.ong.util.ContentTypeEnum;
import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;

@Service
@AllArgsConstructor
public class SlidesServiceImpl implements ISlidesService{

    private final IAWSS3Service awss3Service;
    private final IOrganizationService organizationService;
    private final SlideRepository slideRepository;
    private final MessageSource messageSource;

    @Override
    public Slide addSlide(SlideRequest slide) throws Exception {
        String contentTypeErrorMessage = messageSource.getMessage("awss3.errorMessage.contentType", null, Locale.US);
        File outputFile = null;
        final String FILE_NAME = "slides";

        if (Strings.startsWithIgnoreCase(slide.getBase64Image(),"iVBORw0KGgo")){
            outputFile = new File(FILE_NAME + ContentTypeEnum.PNG.getFileExtension());
        }else if (Strings.startsWithIgnoreCase(slide.getBase64Image(),"/9j/4")){
            outputFile = new File(FILE_NAME + ContentTypeEnum.JPG.getFileExtension());
        }else {
            throw new IllegalArgumentException(contentTypeErrorMessage);
        }

        byte[] decodedImage = Base64.getDecoder().decode(slide.getBase64Image());
        FileUtils.writeByteArrayToFile(outputFile, decodedImage);

       String imageURL = awss3Service.uploadImage(outputFile);

        if(slide.getOrderSlide()== null){
            //TODO buscar el orden mas alto y ponerlo ultimo
        }

        organizationService.findById(slide.getOrganizationId());

        Slide slideToAdd = new Slide();
        slideToAdd.setImageUrl(imageURL);
        slideToAdd.setText(slide.getText());
        slideToAdd.setOrderSlide(slide.getOrderSlide());//TODO ver en el aterior caso
        slideToAdd.setOrganizationId(slide.getOrganizationId());

        return slideRepository.save(slideToAdd);

    }

}
