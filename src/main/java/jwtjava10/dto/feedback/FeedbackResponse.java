package jwtjava10.dto.feedback;

/**
 * @author Mukhammed Asantegin
 */
public record FeedbackResponse(
        Long feedBackId,
        String description,
        String image,
        Long userId,
        String email

) {

}
