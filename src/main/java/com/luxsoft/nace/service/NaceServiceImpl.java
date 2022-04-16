package com.luxsoft.nace.service;

import com.luxsoft.nace.entity.NaceEntity;
import com.luxsoft.nace.repository.NaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class NaceServiceImpl implements NaceService{

    private NaceRepository naceRepository;

    @Autowired
    public NaceServiceImpl(NaceRepository naceRepository) {
        this.naceRepository = naceRepository;
    }
    @Override
    public void insertNaceList(List<NaceEntity> naceEntities) {
        naceRepository.saveAll(naceEntities);
    }

    @Override
    public NaceEntity getNaceDetailsById(String  order) {
        return naceRepository.findNaceByOrder(order).orElseThrow(EntityNotFoundException::new);
    }
}
