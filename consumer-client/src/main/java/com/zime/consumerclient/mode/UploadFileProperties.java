package com.zime.consumerclient.mode;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.servlet")
@Data
public class UploadFileProperties {


    private String uploadFileDirectory;
    private MultipartFileProperties multipart;

    public UploadFileProperties(MultipartFileProperties multipart){

        this.multipart=multipart;
    }
}
