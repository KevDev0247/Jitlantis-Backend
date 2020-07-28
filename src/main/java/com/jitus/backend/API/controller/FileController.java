package com.jitus.backend.API.controller;

import com.jitus.backend.API.model.SysAttachments;
import com.jitus.backend.API.utils.OSSFileUtils;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Api(tags = {"File"})
@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> map = OSSFileUtils.saveFile(file);
        if (map != null && map.containsKey("r_code") && map.get("r_code").equals("1")) {
            SysAttachments attachmentEntity = new SysAttachments();
            attachmentEntity.setFileurl(map.get("file_url"));
            attachmentEntity.setFilename(map.get("file_name"));
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
