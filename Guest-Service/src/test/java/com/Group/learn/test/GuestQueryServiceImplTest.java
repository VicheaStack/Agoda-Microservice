//package com.Group.learn.test;
//
//import com.group.learn.entity.Guest;
//import com.group.learn.repository.GuestRepository;
//import com.group.learn.serviceImpl.GuestQeuryServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class GuestQueryServiceImplTest {
//
//    @Mock
//    private GuestRepository guestRepository;
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @InjectMocks
//    private GuestQeuryServiceImpl guestQeuryService;
//
//    @Test
//    void testFindById_Success() {
//        Long id = 99L;
//        Guest find = new Guest();
//        find.setId(id);
//
//        when(guestRepository.findById(99L)).thenReturn(Optional.of(find));
//
//        Guest guestById = guestQeuryService.getGuestById(id);
//
//        assertNotNull(guestById);
//        assertEquals(99L, guestById.getId());
//
//        verify(guestRepository).findById(id);
//    }
//
//    @Test
//    void testFindAlluser(){
//        Long id = 99L;
//        String firstname = "Leng";
//        String lastname = "ChanVichea";
//        String email = "Lengchanvichea@gmail.com";
//
//        Guest guest = new Guest();
//        guest.setId(id);
//        guest.setFirstName(firstname);
//        guest.setLastName(lastname);
//        guest.setEmail(email);
//
//        Pageable pageable = PageRequest.of(0, 10);
//        when(guestRepository.findAll(pageable))
//                .thenReturn(new PageImpl<>(List.of(guest), pageable, 1));
//
//        Page<Guest> allGuests = guestQeuryService.getAllGuests(pageable);
//
//        assertNotNull(allGuests);
//        assertFalse(allGuests.isEmpty());
//        verify(guestRepository).findAll(pageable);  // ✅ FIXED THIS LINE
//    }
//
//    @Test
//    void testFindAllReview(){
//        Review review = new Review();
//        review.setId(1L);
//        review.setComment("KdorThom");
//        review.setRating("10/10");
//
//        Pageable pageable = PageRequest.of(0, 10);
//
//        Page<Review> reviewPage = new PageImpl<>(List.of(review), pageable, 1);
//
//        when(reviewRepository.findAll(pageable)).thenReturn(reviewPage);
//
//        Page<Review> method = guestQeuryService.getAllReviewedGuests(pageable);
//
//        assertNotNull(method);
//        assertEquals(1, method.getContent().size());
//        verify(reviewRepository).findAll(pageable);
//
//    }
//}