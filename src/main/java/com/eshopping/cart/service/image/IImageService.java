package com.eshopping.cart.service.image;

import com.eshopping.cart.dto.ImageDto;
import com.eshopping.cart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<ImageDto> saveImages(List<MultipartFile> files, int productId);
    void updateImage(MultipartFile file,int imageId);
    void deleteImage(int id);
    Image getImagebyId(int id);
}
