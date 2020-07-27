package com.jitus.backend.API.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SysAttachments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileid;

    private String filename;

    private String fileurl;

    private String fileurlm;

    private String fileurls;

    private String filetype;

    private Integer filesize;

    private String localpath;

    private String previewurl;

    private String pdfurl;

    private String downloadurl;

    private String status;

    private String extension;

    private Date createdtime;

    public Integer getFileid() {
        return fileid;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public String getFileurlm() {
        return fileurlm;
    }

    public String getFileurls() {
        return fileurls;
    }

    public String getFiletype() {
        return filetype;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public String getLocalpath() {
        return localpath;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public String getStatus() {
        return status;
    }

    public String getExtension() {
        return extension;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public void setFileurlm(String fileurlm) {
        this.fileurlm = fileurlm;
    }

    public void setFileurls(String fileurls) {
        this.fileurls = fileurls;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}
