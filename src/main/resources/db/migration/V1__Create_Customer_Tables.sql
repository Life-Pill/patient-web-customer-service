-- V1__Create_Customer_Tables.sql
-- Initial schema for Patient Customer Service

-- Create customer table
CREATE TABLE IF NOT EXISTS customer (
    cus_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    mobile_number VARCHAR(50) NOT NULL UNIQUE,
    cus_password VARCHAR(200) NOT NULL,
    address_street VARCHAR(200) NOT NULL,
    address_city VARCHAR(200) NOT NULL,
    address_district VARCHAR(100) NOT NULL,
    nic VARCHAR(20)
);

-- Create subCustomer table for dependents/family members
CREATE TABLE IF NOT EXISTS "subCustomer" (
    "subCus_id" BIGSERIAL PRIMARY KEY,
    parent_id BIGINT NOT NULL,
    full_name VARCHAR(200) NOT NULL,
    nic VARCHAR(20) UNIQUE,
    CONSTRAINT fk_sub_customer_parent FOREIGN KEY (parent_id) REFERENCES customer(cus_id) ON DELETE CASCADE
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_customer_email ON customer(email);
CREATE INDEX IF NOT EXISTS idx_customer_mobile ON customer(mobile_number);
CREATE INDEX IF NOT EXISTS idx_customer_nic ON customer(nic);
CREATE INDEX IF NOT EXISTS idx_sub_customer_parent ON "subCustomer"(parent_id);
