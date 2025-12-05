-- V2__Seed_Customer_Data.sql
-- Seed data for Patient Customer Service
-- Password is BCrypt hash of 'password123' for all customers

-- Insert sample customers from different districts in Sri Lanka
INSERT INTO customer (full_name, email, mobile_number, cus_password, address_street, address_city, address_district, nic) VALUES
-- Colombo District Customers
('Saman Kumara Perera', 'saman.perera@gmail.com', '0771234501', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '45 Park Lane', 'Colombo 03', 'Colombo', '198912345601V'),
('Nimali Priyanka Silva', 'nimali.silva@gmail.com', '0772234502', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '78 Galle Road', 'Dehiwala', 'Colombo', '199022345602V'),
('Kamal Indrajith Fernando', 'kamal.fernando@gmail.com', '0773234503', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '23 High Level Road', 'Nugegoda', 'Colombo', '198532345603V'),
('Shamila Gayathri Rajapaksa', 'shamila.raj@gmail.com', '0774234504', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '156 Baseline Road', 'Borella', 'Colombo', '199242345604V'),

-- Kandy District Customers
('Chandana Pushpakumara', 'chandana.pk@gmail.com', '0715234505', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '67 Dalada Veediya', 'Kandy', 'Kandy', '198852345605V'),
('Ruwani Malika Bandara', 'ruwani.bandara@gmail.com', '0716234506', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '34 Peradeniya Road', 'Peradeniya', 'Kandy', '199162345606V'),
('Arjuna Wimalasuriya', 'arjuna.wimal@gmail.com', '0717234507', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '89 Katugastota Road', 'Katugastota', 'Kandy', '198772345607V'),

-- Galle District Customers
('Priyantha Kumara Wickramasinghe', 'priyantha.w@gmail.com', '0768234508', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '12 Church Street', 'Galle Fort', 'Galle', '198682345608V'),
('Shanika Dilrukshi', 'shanika.d@gmail.com', '0769234509', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '56 Beach Road', 'Unawatuna', 'Galle', '199382345609V'),
('Mahesh Sanjeewa', 'mahesh.sanjeewa@gmail.com', '0760234510', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '78 Matara Road', 'Ambalangoda', 'Galle', '199082345610V'),

-- Negombo/Gampaha District Customers
('Dilshan Prasanna Jayawardena', 'dilshan.j@gmail.com', '0751234511', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '90 Lewis Place', 'Negombo', 'Gampaha', '199192345611V'),
('Nayomi Sachini Fernando', 'nayomi.f@gmail.com', '0752234512', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '45 Colombo Road', 'Ja-Ela', 'Gampaha', '199402345612V'),
('Lahiru Sampath', 'lahiru.sampath@gmail.com', '0753234513', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '23 Station Road', 'Ragama', 'Gampaha', '198802345613V'),

-- Jaffna District Customers
('Raj Kumar Sivanathan', 'raj.sivanathan@gmail.com', '0784234514', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '34 Hospital Road', 'Jaffna', 'Jaffna', '198512345614V'),
('Priya Shankar Nadarajah', 'priya.nadarajah@gmail.com', '0785234515', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '67 KKS Road', 'Nallur', 'Jaffna', '199512345615V'),

-- Kurunegala District Customers
('Malith Chamara Dissanayake', 'malith.d@gmail.com', '0796234516', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '12 Colombo Road', 'Kurunegala', 'Kurunegala', '199312345616V'),
('Thilini Madhushani', 'thilini.m@gmail.com', '0797234517', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '45 Negombo Road', 'Kuliyapitiya', 'Kurunegala', '199612345617V'),

-- Matara District Customers
('Asanka Pradeep Rathnayake', 'asanka.r@gmail.com', '0708234518', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '78 Galle Road', 'Matara', 'Matara', '198812345618V'),

-- Anuradhapura District Customers
('Kumara Bandara Herath', 'kumara.herath@gmail.com', '0729234519', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '56 Sacred City Road', 'Anuradhapura', 'Anuradhapura', '197912345619V'),

-- Ratnapura District Customers
('Chaminda Lakmal Gamage', 'chaminda.g@gmail.com', '0740234520', '$2a$10$1P.byW2hXv3kt/gkG5W4puF6AfXh6dB6I6YGJI4xA8DlKDjtRv1Ma', '34 Colombo Road', 'Ratnapura', 'Ratnapura', '199212345620V');

-- Insert sub-customers (family members/dependents)
INSERT INTO "subCustomer" (parent_id, full_name, nic) VALUES
-- Family members of Saman Kumara Perera (Customer ID 1)
(1, 'Ishara Perera', '201112345621V'),
(1, 'Kasuni Perera', '200812345622V'),
(1, 'Lakshmi Perera', '196512345623V'),

-- Family members of Nimali Priyanka Silva (Customer ID 2)
(2, 'Pasindu Silva', '200912345624V'),
(2, 'Chamodi Silva', '201212345625V'),

-- Family members of Chandana Pushpakumara (Customer ID 5)
(5, 'Nethmi Pushpakumara', '200712345626V'),
(5, 'Ashan Pushpakumara', '201012345627V'),
(5, 'Malini Pushpakumara', '196812345628V'),
(5, 'Gunasena Pushpakumara', '196012345629V'),

-- Family members of Priyantha Kumara Wickramasinghe (Customer ID 8)
(8, 'Sadun Wickramasinghe', '200512345630V'),
(8, 'Dinusha Wickramasinghe', '200312345631V'),

-- Family members of Raj Kumar Sivanathan (Customer ID 14)
(14, 'Kamala Sivanathan', '198712345632V'),
(14, 'Arun Sivanathan', '201112345633V'),
(14, 'Meena Sivanathan', '201312345634V'),

-- Family members of Dilshan Prasanna Jayawardena (Customer ID 11)
(11, 'Rashmi Jayawardena', '199512345635V'),
(11, 'Tharindu Jayawardena', '202012345636V'),

-- Family members of Malith Chamara Dissanayake (Customer ID 16)
(16, 'Nishantha Dissanayake', '196512345637V'),
(16, 'Chamari Dissanayake', '199812345638V');

-- Note: Password for all customers is 'password123'
-- Total: 20 customers, 18 sub-customers (family members)
