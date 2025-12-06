//package com.Group.learn.test;
//
//import com.group.learn.exception.ResourceNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ReviewServiceImplTest {
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @InjectMocks
//    private ReviewServiceImpl reviewService;
//
////    @Test
////    void create(){
////        Review review = new Review();
////        review.setComment("suck my dick baby");
////
////        when(reviewRepository.save(review)).thenReturn(review);
////
////        Review create = reviewService.create(review);
////
////        assertNotNull(create);
////        assertEquals("suck my dick baby" , create.getComment());
////
////        verify(reviewRepository, times(1)).save(any(Review.class));
////    }
//
//    @Test
//    void update(){
//        Review review = new Review();
//        review.setId(1L);
//        review.setComment("this service are good");
//
//        Review update = new Review();
//        update.setComment("new comment");
//
//        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
//        when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Review method = reviewService.update(1L, update);
//
//        assertNotNull(method);
//        assertEquals("new comment", method.getComment());
//        verify(reviewRepository, times(1)).findById(1L);
//        verify(reviewRepository, times(1)).save(any(Review.class));
//
//    }
//
//    @Test
//    void delete(){
//        Long id = 99L;
//
//        when(reviewRepository.findById(id)).thenReturn(Optional.empty());
//
//        ResourceNotFoundException ex = assertThrows(
//          ResourceNotFoundException.class,
//                () -> reviewService.delete(id)
//        );
//
//      //  assertEquals("review not found " , ex.getMessage());
//        assertEquals("Review not found", ex.getMessage());
//
//        verify(reviewRepository, times(1)).findById(id);
//        verify(reviewRepository, never()).delete(any());
//    }
//
////    @Test
////    void delete_WhenReviewNotFound_ShouldThrowException() {
////        // Arrange
////        Long id = 99L;
////
////        // Mock: repository returns empty (review not found)
////        when(reviewRepository.findById(id)).thenReturn(Optional.empty());
////
////        // Act & Assert
////        ResourceNotFoundException ex = assertThrows(
////                ResourceNotFoundException.class,
////                () -> reviewService.delete(id)  // ✅ Call the actual service method
////        );
////
////        // Optional: Verify exception message
////        assertEquals("Review not found", ex.getMessage());
////
////        // Optional: Verify repository interactions
////        verify(reviewRepository).findById(id);
////        verify(reviewRepository, never()).delete(any());
////    }
//
//    @Test
//    void findById(){
//        Long id = 99L;
//        when(reviewRepository.findById(id)).thenReturn(Optional.empty());
//
//        ResourceNotFoundException ex = assertThrows(
//                ResourceNotFoundException.class,
//                () -> reviewService.findById(id)
//        );
//
//        // Remove the trailing space - match EXACTLY
//        assertEquals("Review not found by id", ex.getMessage()); // ✅ No space
//        verify(reviewRepository, times(1)).findById(id);
//    }
//}
