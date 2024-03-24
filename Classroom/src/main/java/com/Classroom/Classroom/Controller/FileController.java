package com.Classroom.Classroom.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {

    @PostMapping("/upload/{sem}/{sub}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @PathVariable String sem,
                                   @PathVariable String sub) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            String uploadDir = "/data2/materials/"+sem+"/"+sub;
            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            return "File uploaded successfully!";
        } catch (IOException e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }
    @GetMapping("/files/{sem}/{sub}")
    public List<String> listFiles(@PathVariable String sem,@PathVariable String sub) {
        List<String> fileList = new ArrayList<>();
        String directoryPath = "/data2/materials/"+sem+"/"+sub;
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    }
                }
            }
        }
        return fileList;
    }
    @GetMapping("/download/{sem}/{sub}/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName ,
                                               @PathVariable String sem,
                                               @PathVariable String sub) {
        String directoryPath = "/data2/materials/"+sem+"/"+sub;
        File file = new File(directoryPath + File.separator + fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
