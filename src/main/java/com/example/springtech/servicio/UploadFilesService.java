package com.example.springtech.servicio;

import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFilesService implements IUploadFilesService {

    @Override
    public String handleFileUpload(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;

            if (fileSize > maxFileSize) {
                throw new RecursoNoEncontradoExcepcion("El tamaño de la imagen debe ser menor o igual a 5 MB");
            }

            if (!fileOriginalName.endsWith(".jpg") && !fileOriginalName.endsWith(".jpeg") && !fileOriginalName.endsWith(".png")) {
                throw new RecursoNoEncontradoExcepcion("Solo imagenes JPG, JPEG, PNG son permitidos");
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));

            String newFileName = fileName + fileExtension;

            File folder = new File("src/main/resources/picture");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            Path path = Paths.get("src/main/resources/picture/" + newFileName);
            Files.write(path, bytes);
            return newFileName;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
