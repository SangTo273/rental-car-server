package net.codejava.controller;

import java.util.List;
import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.codejava.constant.Endpoint;
import net.codejava.domain.dto.feedback.FeedbackResponseDTO;
import net.codejava.domain.dto.meta.MetaRequestDTO;
import net.codejava.domain.dto.meta.MetaResponseDTO;
import net.codejava.responses.MetaResponse;
import net.codejava.responses.Response;
import net.codejava.service.FeedbackService;
import net.codejava.utility.AuthUtil;

@Tag(name = "Feedbacks")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping(Endpoint.V1.Feedback.LIST_FOR_OWNER)
    public ResponseEntity<MetaResponse<MetaResponseDTO, List<FeedbackResponseDTO>>> getListFeedbackByOwner(
            @RequestParam(name = "rating", required = false) Integer rating,
            @ParameterObject MetaRequestDTO metaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(feedbackService.getListFeedbackByOwner(
                        AuthUtil.getRequestedUser().getId(), rating, metaRequestDTO));
    }

    @GetMapping(Endpoint.V1.Feedback.GET_RATING)
    public ResponseEntity<Response<Map<String, String>>> getRating() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(feedbackService.getRating(AuthUtil.getRequestedUser().getId()));
    }
}
