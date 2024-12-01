package net.codejava.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.codejava.domain.dto.image.ImageResponseDTO;
import net.codejava.domain.dto.image.UpdImageRequestDTO;
import net.codejava.domain.entity.Image;
import net.codejava.domain.mapper.ImageMapper;
import net.codejava.exceptions.AppException;
import net.codejava.repository.ImageRepository;
import net.codejava.responses.Response;
import net.codejava.service.CloudinaryService;
import net.codejava.service.ImageService;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepo;
    private final CloudinaryService cloudinaryService;

    @Override
    public Response<ImageResponseDTO> updImage(UpdImageRequestDTO requestDTO, String folder) {
        Optional<Image> findImage = imageRepo.findById(requestDTO.id());
        if (findImage.isEmpty()) throw new AppException("Image is not existed");
        Image updImage = findImage.get();
        // Delete Image on Cloudinary
        try {
            cloudinaryService.deleteFile(findImage.get().getImagePublicId());
        } catch (IOException e) {
            throw new AppException("Delete image on cloudinary unsuccessfully");
        }
        // Upload New Image to Cloudinary
        try {
            Map uploadImage = cloudinaryService.uploadFileBase64(requestDTO.imageItem(), folder);
            updImage.setName((String) uploadImage.get("original_filename"));
            updImage.setImageUrl((String) uploadImage.get("url"));
            updImage.setImagePublicId((String) uploadImage.get("public_id"));
            Image saveImage = imageRepo.save(updImage);
            return Response.successfulResponse("Update image successful", imageMapper.toImageResponseDTO(saveImage));
        } catch (IOException e) {
            throw new AppException("Upload image unsuccessfully");
        }
    }
}
