package com.luxsoft.nace.repository;

import com.luxsoft.nace.entity.NaceEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class NaceRepositoryTest {

    @Autowired
    private NaceRepository naceRepository;

    @AfterEach
    public void cleanup() {
        naceRepository.deleteAll();
    }

    @Test
    public void testPersistence() {
        NaceEntity nace = new NaceEntity();
        nace.setDescription("test");
        nace.setCode("123");
        nace.setLevel("1");
        nace.setOrder("1");

        naceRepository.save(nace);

        assertNotNull(nace.getId());
        NaceEntity newNace = naceRepository.findById(nace.getId()).orElse(null);
        assertEquals("test", newNace.getDescription());
        assertEquals("123".compareTo(newNace.getCode()), 0);
        assertEquals("1", newNace.getLevel());
    }


}
