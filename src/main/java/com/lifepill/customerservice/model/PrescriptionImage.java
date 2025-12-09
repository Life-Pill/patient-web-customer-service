package com.lifepill.customerservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "savedPrescriptions")
public class PrescriptionImage {
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] file;

    public PrescriptionImage() {
    }

    public PrescriptionImage(String fileName, String fileType, String fileSize, byte[] file) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.file = file;
    }

    public static PrescriptionImageBuilder builder() {
        return new PrescriptionImageBuilder();
    }

    public static class PrescriptionImageBuilder {
        private String fileName;
        private String fileType;
        private String fileSize;
        private byte[] file;

        PrescriptionImageBuilder() {
        }

        public PrescriptionImageBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public PrescriptionImageBuilder fileType(String fileType) {
            this.fileType = fileType;
            return this;
        }

        public PrescriptionImageBuilder fileSize(String fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public PrescriptionImageBuilder file(byte[] file) {
            this.file = file;
            return this;
        }

        public PrescriptionImage build() {
            return new PrescriptionImage(fileName, fileType, fileSize, file);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionImage that = (PrescriptionImage) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(fileType, that.fileType) && Objects.equals(fileSize, that.fileSize) && java.util.Arrays.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileName, fileType, fileSize);
        result = 31 * result + java.util.Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "PrescriptionImage{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", file=" + java.util.Arrays.toString(file) +
                '}';
    }
}
