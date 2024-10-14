package com.eshopping.cart.controller;

import com.eshopping.cart.dto.ImageDto;
import com.eshopping.cart.model.Image;
import com.eshopping.cart.response.ApiResponse;
import com.eshopping.cart.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files,@RequestParam int productId){
        List<ImageDto> images = imageService.saveImages(files, productId);
        return ResponseEntity.ok(new ApiResponse("Upload Success", images));
    }
    @GetMapping("/image/download/{id}")
    public ResponseEntity<Resource> downloadImage(@RequestParam int id) throws SQLException {
        Image image=imageService.getImagebyId(id);
        ByteArrayResource resource=new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=/"+ image.getFileName()+"/")
                .body(resource);
    }

    @PostMapping("/image/{imageId}/upload")
    public ResponseEntity<ApiResponse> updateImage(@RequestParam int imageId,@RequestBody MultipartFile file){
        Image image=imageService.getImagebyId(imageId);
        if(image!=null){
            imageService.updateImage(file,imageId);
            return ResponseEntity.ok(new ApiResponse("Update Success", null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update Failed!..",INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/image/{imageId}/upload")
    public ResponseEntity<ApiResponse> deleteImage(@RequestParam int imageId,@RequestBody MultipartFile file){
        Image image=imageService.getImagebyId(imageId);
        if(image!=null){
            return ResponseEntity.ok(new ApiResponse("Delete Success", null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed!..",INTERNAL_SERVER_ERROR));
    }
}
