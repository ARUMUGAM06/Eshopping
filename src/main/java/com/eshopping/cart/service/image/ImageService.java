package com.eshopping.cart.service.image;

import com.eshopping.cart.dto.ImageDto;
import com.eshopping.cart.exception.ResourceNotFoundException;
import com.eshopping.cart.exception.ResourceNotSavedException;
import com.eshopping.cart.model.Image;
import com.eshopping.cart.model.Product;
import com.eshopping.cart.repository.ImageRepository;
import com.eshopping.cart.service.product.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = ResourceNotSavedException.class)
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, int productId) {
        Product product= productService.getProductById(productId);
        List<ImageDto> savedImages= new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileName(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl="/api/v1/Images/image/";
                String downloadUrl=buildDownloadUrl+image.getProduct().getId()+"/"+image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage=imageRepository.save(image);
                savedImage.setDownloadUrl(savedImage.getDownloadUrl());

                imageRepository.save(savedImage);

                ImageDto imageDto=new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImages.add(imageDto);
            }catch (IOException | SQLException e){
                throw new ResourceNotSavedException("Images not saved!..");
            }
        }
        return savedImages;
    }

    @Override
    public void deleteImage(int id) {
        imageRepository.findById(id)
                       .ifPresentOrElse(imageRepository::delete,()->{
                           throw new ResourceNotFoundException("Image not found");
                       });
    }

    @Override
    public void updateImage(MultipartFile file,int imageId) {
        Image image=getImagebyId(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFiletype(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        }
        catch(SQLException|IOException e){
            throw new ResourceNotSavedException("Image not updated!.");
        }
    }

    @Override
    public Image getImagebyId(int id) {
        return imageRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image not found!.."));
    }
}
