package com.example.springtech.servicio;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFilesService {
    String handleFileUpload (MultipartFile file) throws Exception;

}
