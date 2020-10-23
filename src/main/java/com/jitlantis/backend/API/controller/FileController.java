package com.jitlantis.backend.API.controller;

import com.jitlantis.backend.API.dto.UploadResponseDto;
import com.jitlantis.backend.API.model.SysAttachments;
import com.jitlantis.backend.API.service.SysAttachmentsService;
import com.jitlantis.backend.API.utils.OSSFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.jitlantis.backend.API.utils.SystemConstants.UPLOAD_FILE_PATH;

/**
 * The controller for File that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see SysAttachments
 * created on 2020/07/26
 */
@Api(tags = {"file"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private SysAttachmentsService sysAttachmentsService;

    @RequestMapping(value = "/uploadToOOS", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> uploadToOOS(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) {
            UploadResponseDto responseDto = OSSFileUtils.uploadToCloud(file);
            if (responseDto.getCode().equals("1")) {
                String url = responseDto.getUrl();
                String fileName = file.getOriginalFilename();
                String extension = fileName.substring(fileName.lastIndexOf("."));

                SysAttachments attachmentEntity = new SysAttachments();
                attachmentEntity.setFilename(fileName);
                attachmentEntity.setExtension(extension);
                sysAttachmentsService.create(attachmentEntity);
                
                map.put("fileid", attachmentEntity.getFileid());
                map.put("url", url);
            }
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

//    @ApiOperation(value = "create attachment")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "attachment", value = "Attachment Entity", required = true, dataType = "SysAttachments")
//    })
//    @RequestMapping(value = "/createAttachment", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> createAttachment(@RequestBody SysAttachments attachment) {
//        Map<String, Object> map = new HashMap<>();
//        int attachmentId = sysAttachmentsService.create(attachment);
//        map.put("data", attachment.getFileid());
//
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                String extension = fileName.substring(fileName.lastIndexOf("."));
                String fileUrl = UPLOAD_FILE_PATH + fileName;
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(fileUrl)));

                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();

                SysAttachments sysAttachmentsEntity = new SysAttachments();
                sysAttachmentsEntity.setFileurl(fileUrl);
                sysAttachmentsEntity.setFilename(fileName);
                sysAttachmentsEntity.setExtension(extension);
                sysAttachmentsService.insert(sysAttachmentsEntity);
            } catch (IOException e) {
                e.printStackTrace();
                return "upload failed" + e.getMessage();
            }
            return "upload successful";
        } else {
            return "upload failed, the file is empty";
        }
    }
}
