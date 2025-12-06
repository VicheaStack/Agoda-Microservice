//package com.Group.learn.test;
//
//import com.group.learn.repository.GuestRepository;
//import com.group.learn.repository.LoyaltyTransactionRepository;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//@ExtendWith(MockitoExtension.class)
//public class RedeenServiceImplTest {
//
//    @Mock
//    private LoyaltyTransactionRepository loyaltyTransactionRepository;
//
//    @Mock
//    private GuestRepository guestRepository;
//
//    @InjectMocks
//    private RedeemServiceImpl redeemService;
//
////    @Disabled
////  //  @Test
////    void getPoint() {
////        LoyaltyTransaction loyaltyTransaction = new LoyaltyTransaction();
////        loyaltyTransaction.setId(1L);
////        loyaltyTransaction.setPoints(10);
////
////        when(loyaltyTransactionRepository.findById(loyaltyTransaction.getId())).thenReturn(Optional.empty());
////
////        ResourceNotFoundException ex = assertThrows(
////                ResourceNotFoundException.class,
////                () -> redeemService.getPoints()
////
////        );
////    }
//}
