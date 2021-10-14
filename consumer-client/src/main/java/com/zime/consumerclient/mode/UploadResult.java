package com.zime.consumerclient.mode;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResult {

    private int code;
    private String message;
    private String url;


}
