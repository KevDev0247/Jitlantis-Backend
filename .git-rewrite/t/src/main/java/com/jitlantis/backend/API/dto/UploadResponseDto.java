package com.jitlantis.backend.API.dto;

public class UploadResponseDto extends BaseResponseDto {

    private String url;

    private String fileId;

    public String getUrl() {
        return url;
    }

    public String getFileId() {
        return fileId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
