package com.devtool.book.devtoolbook.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    // private final String UPLOAD_DIR="E:\\Spring Projects\\devtools\\devtoolbook\\src\\main\\resources\\static\\image";
    private final String UPLOAD_DIR= new ClassPathResource("static\\image\\").getFile().getAbsolutePath();

    public FileUploadHelper()throws IOException
    {

    }

    public boolean uploadHander(MultipartFile multipartFile )
    {
        boolean f=false;

        try {
            //code for copy data into path

            //reading data...........
            // InputStream iStream=multipartFile.getInputStream();
            // byte[] data= new byte[iStream.available()];
            // iStream.read(data);

            // //writing the data......
            // FileOutputStream fos= new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());
            // fos.write(data);
            // fos.flush();
            // fos.close();
        
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING) ;


            f=true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}
