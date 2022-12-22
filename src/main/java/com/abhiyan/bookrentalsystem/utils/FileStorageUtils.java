package com.abhiyan.bookrentalsystem.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class FileStorageUtils {

    @Value("${course.file.storage.directory}")
    private String courseStoragePath;

    private final String userHome = System.getProperty("user.home");


//     this function takes multipart file as input parameter and
//    saves it and then returns the file location

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String directoryPath = userHome + courseStoragePath;
        File directoryFile = new File(directoryPath);
        if(!directoryFile.exists()){
            directoryFile.mkdirs();
        }
        else{
            System.out.println("Directory already exists");
        }

        String fileStorageLocation = directoryPath+multipartFile.getOriginalFilename();
        File fileTOSave = new File(fileStorageLocation);

        multipartFile.transferTo(fileTOSave);

        return fileStorageLocation;
    }

    public String getBase64FileFromFilePath(String filePath) throws IOException {
        File readingFile = new File(filePath);
        if(readingFile.exists()){
            // get byte array of file and conver it to base64
            System.out.println("File found");
            byte [] bytes = Files.readAllBytes((readingFile.toPath()));

            String base64String = Base64.getEncoder().encodeToString(bytes);

            return "data:image/jpeg;base64," + base64String;
        } else{
            return null;
        }
    }
}
