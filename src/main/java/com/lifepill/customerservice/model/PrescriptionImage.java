package com.lifepill.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "savedPrescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionImage {
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
