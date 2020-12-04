package ai.ecma.appapplicationindividual.controller;

import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@RestController
@RequestMapping("/api/attach")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/uploadFile")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        ApiResponse apiResponse = attachmentService.uploadFile(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getApplication(@PathVariable UUID id) {
        return attachmentService.getFile(id);
    }
}
