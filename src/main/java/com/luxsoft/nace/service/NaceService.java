package com.luxsoft.nace.service;

import com.luxsoft.nace.entity.NaceEntity;

import java.util.List;

public interface NaceService {

    void insertNaceList(List<NaceEntity> naceEntities);
    NaceEntity getNaceDetailsById(String id);
}