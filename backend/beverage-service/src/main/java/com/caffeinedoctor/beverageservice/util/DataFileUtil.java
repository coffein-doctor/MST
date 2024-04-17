package com.caffeinedoctor.beverageservice.util;

import com.caffeinedoctor.beverageservice.entity.DataFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class DataFileUtil {
    @Value("{file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public DataFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storedFileName = generateStoreFileName(originalFilename);
        String contentType = multipartFile.getContentType();

        multipartFile.transferTo(new File(getFullPath(storedFileName)));

        return createDataFile(originalFilename, storedFileName, contentType);
    }

    public DataFile saveImageFromURL(String imageUrl) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ByteArrayResource> response = restTemplate.exchange(
                imageUrl,
                HttpMethod.GET,
                entity,
                ByteArrayResource.class
        );

        String storedFileName = null;
        if (response.getStatusCode() == HttpStatus.OK) {
            ByteArrayResource resource = response.getBody();
            if (resource != null) {
                try {
                    storedFileName = generateStoreFileName("profile.jpg");
                    Path path = Paths.get(getFullPath(storedFileName));
                    Files.write(path, resource.getByteArray());
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return createDataFile(storedFileName, storedFileName, "image/jpeg");
    }

    public DataFile createDataFile(String originalFilename, String storedFileName, String contentType) {
        DataFile dataFile = DataFile.builder()
                .originalFileName(originalFilename)
                .storedFileName(storedFileName)
                .contentType(contentType)
                .build();
        return dataFile;
    }

    public boolean deleteFile(String path) {
        File temp = new File(path);
        if (temp.exists()) {
            temp.delete();
        }
        return false;
    }

    private String generateStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
