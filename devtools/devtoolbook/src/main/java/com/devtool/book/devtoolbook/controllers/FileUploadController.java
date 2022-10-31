package com.devtool.book.devtoolbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtool.book.devtoolbook.helper.FileUploadHelper;

@RestController
public class FileUploadController {
    @Autowired
        private FileUploadHelper fileUploadHelper;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
    {
        try {
            //validations
            if(file.isEmpty())
            {
                
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file must be uploaded");
            }

            // System.out.println(file.getOriginalFilename());
            // System.out.println(file.getContentType());
            // System.out.println(file.getSize());
            // System.out.println(file.isEmpty());
            // System.out.println(file.getBytes());
         
            if(!file.getContentType().equals("image/jpeg"))
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only JPEG format files allowed...");
            }

            // uploading file into the server

            boolean f =fileUploadHelper.uploadHander(file);
            if(f)
            {
                // return ResponseEntity.status(HttpStatus.CREATED).body("file uploaded successfully........");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        return ResponseEntity.ok("working.......");
    }

}
