package com.Classroom.Classroom.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class FileController {


    @PreAuthorize("hasAnyRole('TEACHER','REP')")
    @PostMapping("/upload/{sem}/{sub}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @PathVariable String sem,
                                   @PathVariable String sub) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            String uploadDir = "/home/ec2-user/materials/"+sem+"/"+sub;
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                if (!uploadDirectory.mkdirs()) {
                    return "Failed to create directories.";
                }
            }
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
        String directoryPath = "/home/ec2-user/materials/"+sem+"/"+sub;
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
        String directoryPath = "/home/ec2-user/materials/"+sem+"/"+sub;
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
    @PreAuthorize("hasAnyRole('TEACHER','REP')")
    @DeleteMapping("/delete/{sem}/{sub}/{fileName:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName,
                                             @PathVariable String sem,
                                             @PathVariable String sub) {
        String directoryPath = "/home/ec2-user/materials/" + sem + "/" + sub;
        File file = new File(directoryPath + File.separator + fileName);

        if (file.exists()) {
            if (file.delete()) {
                return ResponseEntity.ok("File " + fileName + " deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to delete file " + fileName);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File " + fileName + " not found");
        }
    }
}
