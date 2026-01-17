package payments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import payments.model.dto.ErrorResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandle {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now().format(FORMATTER))
                .status(HttpStatus.NO_CONTENT.value())
                .message("Resource not Found")
                .path(webRequest.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, WebRequest request){
        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now().format(FORMATTER))
                .status(HttpStatus.NOT_FOUND.value())
                .message("Resource not found")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
