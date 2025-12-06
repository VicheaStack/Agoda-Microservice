package com.Group.learn.test;

import com.group.learn.entity.Guest;
import com.group.learn.exception.ResourceNotFoundException;
import com.group.learn.repository.GuestRepository;
import com.group.learn.serviceImpl.GuestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GuestServiceImplTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestServiceImpl guestService;

    @BeforeEach
    void setup() {}

    @Test
    void creativenessNonreturnable() {
        // 1️⃣ Create fake guest
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setFirstName("leng");
        guest.setLastName("chanvichea");
        guest.setEmail("lengchanvichea@gmail.com");

        // 2️⃣ Prepare mock behavior
        when(guestRepository.save(any(Guest.class))).thenReturn(guest);

        // 3️⃣ Call the real service method
        Guest result = guestService.create(guest);

        // 4️⃣ Verify results
        assertNotNull(result);
        assertEquals("leng", result.getFirstName());
        assertEquals("lengchanvichea@gmail.com", result.getEmail());

        // 5️⃣ Verify repository interaction
        verify(guestRepository, times(1)).save(any(Guest.class));
    }

    @Test
    void update(){
        Guest exising = new Guest();
        exising.setId(1L);
        exising.setFirstName("Vichea");
        exising.setLastName("Leng");
        exising.setEmail("old@gamil.com");

        Guest update = new Guest();
        update.setId(1L);
        update.setFirstName("Mr");
        update.setLastName("BigDick");
        update.setEmail("new@gamil.com");

        when(guestRepository.findById(1L)).thenReturn(Optional.of(exising));
        when(guestRepository.save(any(Guest.class))).thenReturn(update);

        Guest result = guestService.update(1L, update);

        assertNotNull(result);
        assertEquals("Mr", result.getFirstName());
        assertEquals("new@gamil.com", result.getEmail());
        verify(guestRepository).findById(1L);
        verify(guestRepository).save(any(Guest.class));

    }

    @Test
    void deleteGuest() {
       Long id = 99L;

       when(guestRepository.existsById(id)).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> guestService.delete(id)
        );

        assertEquals("Guest not found with id: " + id, ex.getMessage());
        verify(guestRepository, times(1)).existsById(id);
        verify(guestRepository, never()).deleteById(anyLong());



    }
}
