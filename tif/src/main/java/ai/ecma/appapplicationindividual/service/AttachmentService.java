package ai.ecma.appapplicationindividual.service;

import ai.ecma.appapplicationindividual.entity.Attachment;
import ai.ecma.appapplicationindividual.entity.AttachmentContent;
import ai.ecma.appapplicationindividual.entity.template.AbsEntity;
import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        List<Attachment> attachmentList = new ArrayList<>();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            assert file != null;
            if (!Objects.requireNonNull(file.getContentType()).equals("image/jpeg"))
                return new ApiResponse("Fayl formati mos emas", false);
            Attachment attachment = new Attachment(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize());
            AttachmentContent attachmentContent = new AttachmentContent(
                    file.getBytes(),
                    attachment);
            attachment.setAttachmentContent(attachmentContent);
            attachmentList.add(attachment);
        }
        attachmentRepository.saveAll(attachmentList);
        return new ApiResponse(
                "Saqlandi",
                true,
                attachmentList.stream().map(AbsEntity::getId).collect(Collectors.toList()));
    }

    public HttpEntity<?> getFile(UUID id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            return
                    ResponseEntity
                            .ok()
                            .contentType(MediaType.valueOf(attachment.getContentType()))
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + attachment.getName() + "\"")
                            .body(attachment.getAttachmentContent().getContent());
        }
        return null;
    }
}
