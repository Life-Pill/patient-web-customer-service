package com.lifepill.customerservice.service;

import com.lifepill.customerservice.model.Prescription;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PrescriptionService {
    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    //add new prescription
    public String addPrescription(MultipartFile upload) throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());

        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

        return fileID.toString();
    }

    public Prescription getPrescription(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        Prescription prescription = new Prescription();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            prescription.setFileName( gridFSFile.getFilename() );

            prescription.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            prescription.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            prescription.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );

//            prescription.setFile((operations.getResource(gridFSFile).getInputStream()).readAllBytes());
        }

        return prescription;
    }
}
