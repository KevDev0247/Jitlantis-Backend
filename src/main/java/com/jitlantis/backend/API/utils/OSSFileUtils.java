package com.jitlantis.backend.API.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.jitlantis.backend.API.dto.UploadResponseDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OSSFileUtils {

    private static String domain = "";

    private static String endpoint ="";

    private static String accessKeyId = "";

    private static String accessKeySecret = "";

    private static String bucketName = "";

    public static Map<String, String> saveFile(MultipartFile file) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String appName = dateFormat.format(new Date());

            MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
            bodyMap.add("file", new FileSystemResource(convert(file)));
            bodyMap.add("appName", appName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(domain, HttpMethod.POST, requestEntity, String.class);

            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            String data = String.valueOf(jsonObject.get("data"));
            String code = String.valueOf(jsonObject.get("code"));

            Map<String, String> map = new HashMap<>();
            if (code.equals(HttpCode.CODE_200)) {
                map.put("file_url", data);
                map.put("file_name", appName);
                map.put("r_code", "1");
                return map;
            }
            throw new BusinessException("-1", "upload failed, please try again in a few minutes");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static File convert(MultipartFile file) {
        File fileConverted = new File("temp_image", file.getOriginalFilename());
        if (!fileConverted.getParentFile().exists()) {
            System.out.println("mkdir: " + fileConverted.getParentFile().mkdirs());
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileConverted);
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileConverted;
    }

    public static UploadResponseDto uploadToCloud(MultipartFile file) throws IOException {
        UploadResponseDto responseDto = new UploadResponseDto();
        InputStream inputStream = file.getInputStream();

        try {
            OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            ObjectMetadata metadata = new ObjectMetadata();
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));
            metadata.setContentType(getContentType(extension));

            ossClient.putObject(bucketName, fileName, inputStream, metadata);
            ossClient.shutdown();

            responseDto.setUrl("https://" + bucketName + "." + endpoint + "/" + fileName);
            responseDto.setMessage("Upload Successful");
            responseDto.setCode("1");

            return responseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("-1", "Upload failed, please try a few minutes later");
        } finally {
            inputStream.close();
        }
    }

    public static String getContentType(String fileNameExtension) {
        if (fileNameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (fileNameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (fileNameExtension.equalsIgnoreCase("jpeg")
                || fileNameExtension.equalsIgnoreCase("jpg")
                || fileNameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (fileNameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (fileNameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (fileNameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (fileNameExtension.equalsIgnoreCase("pptx") || fileNameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (fileNameExtension.equalsIgnoreCase("docx") || fileNameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (fileNameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }

        return "image/jpeg";
    }
}
