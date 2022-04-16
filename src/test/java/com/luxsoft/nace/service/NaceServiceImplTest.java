package com.luxsoft.nace.service;

import com.luxsoft.nace.entity.NaceEntity;
import com.luxsoft.nace.repository.NaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class NaceServiceImplTest {

    @Mock
    private NaceRepository naceRepository;

    @InjectMocks
    private NaceServiceImpl naceService;

    private NaceEntity naceEntity;


    @Test
    public void testGetNaceDetailsByIdSuccess() {
        naceEntity = new NaceEntity("1", "2", "3", "Nace 1", "ref");
        when(naceRepository.findNaceByOrder("1")).thenReturn(Optional.of(naceEntity));
        Assertions.assertEquals(naceEntity, naceService.getNaceDetailsById("1"), "Nace entity should be as expected");
    }

    @Test
    public void testGetNaceDetailsByIdFailure() {
        Assertions.assertThrows(EntityNotFoundException.class,
                ()->{
                    when(naceRepository.findNaceByOrder("1")).thenThrow(EntityNotFoundException.class);
                    naceService.getNaceDetailsById("1");
                });
    }

}
