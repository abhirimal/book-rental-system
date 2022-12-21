package com.abhiyan.bookrentalsystem.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStorageUtils {

    @Value("${course.file.storage.directory}")
    private String courseStoragePath;

    private String userHome = System.getProperty("user.home");


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
}
