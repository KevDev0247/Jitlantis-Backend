package com.jitlantis.backend.API.controller;

import com.jitlantis.backend.API.model.SysAttachments;
import com.jitlantis.backend.API.service.SysAttachmentsService;
import com.jitlantis.backend.API.utils.OSSFileUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        String url = OSSFileUtils.uploadToCloud(file);
        map.put("url", url);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

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
