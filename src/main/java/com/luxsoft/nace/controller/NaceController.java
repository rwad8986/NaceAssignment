package com.luxsoft.nace.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.luxsoft.nace.entity.NaceEntity;
import com.luxsoft.nace.service.NaceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("nace")
@Api(value = "Endpoint for exposing NACE features.")
public class NaceController {

    private NaceService naceService;

    @Autowired
    public void setNaceService(NaceService naceService) {
        this.naceService = naceService;
    }

    @ApiOperation(value = "Fetch NACE details for an id")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Nace details Not found."),
            @ApiResponse(code = 200, message = "NACE details received successfully.", response = NaceEntity.class)})
    @GetMapping("/{id}")
    public NaceEntity getNaceDetails(@PathVariable String id){
       return naceService.getNaceDetailsById(id);
    }

    @ApiOperation(value = "Insert NACE details.")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Incompatible data."),
            @ApiResponse(code = 200, message = "insertion successful")})
    @PostMapping(consumes = "multipart/form-data")
    public void insertNaceDetails(@RequestParam("file") MultipartFile file) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MappingIterator<NaceEntity> readValues = mapper.readerFor(NaceEntity.class).with(bootstrapSchema).readValues(file.getInputStream());
        naceService.insertNaceList(readValues.readAll());
    }
}
