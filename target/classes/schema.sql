-- Initialize database schema for the University Management System

-- Login table
CREATE TABLE IF NOT EXISTS login (
    stt INT AUTO_INCREMENT PRIMARY KEY,
    tendangnhap VARCHAR(40) NOT NULL UNIQUE,
    matkhau VARCHAR(255) NOT NULL
);

-- Department table
CREATE TABLE IF NOT EXISTS department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    makhoa CHAR(4) NOT NULL UNIQUE,
    tenkhoa VARCHAR(60) NOT NULL UNIQUE,
    matruongkhoa CHAR(6)
);

-- Lecturer table (create before adding the foreign key to department)
CREATE TABLE IF NOT EXISTS lecturer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    maGV CHAR(6) NOT NULL UNIQUE,
    tenkhoa VARCHAR(60),
    hoten VARCHAR(50) NOT NULL,
    gioitinh VARCHAR(3) NOT NULL CHECK (gioitinh IN ('Nam', 'Nữ')),
    ngaysinh DATE NOT NULL,
    sdt VARCHAR(15) UNIQUE,
    diachi VARCHAR(100),
    cccd CHAR(12) UNIQUE NOT NULL,
    hocvi VARCHAR(50), 
    chucvu VARCHAR(50),
    matkhau CHAR(8) NOT NULL,
    email VARCHAR(50) UNIQUE
);

-- Add foreign key to department after lecturer is created
-- We'll add this constraint after inserting initial data
-- ALTER TABLE department
-- ADD CONSTRAINT fk_lecturer_department 
-- FOREIGN KEY (matruongkhoa) REFERENCES lecturer(maGV)
-- ON UPDATE CASCADE
-- ON DELETE SET NULL;

-- Student table
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    khoa CHAR(2),
    maSV CHAR(6) NOT NULL UNIQUE,
    tenkhoa VARCHAR(60),
    hoten VARCHAR(50) NOT NULL,
    gioitinh VARCHAR(3) NOT NULL CHECK (gioitinh IN ('Nam', 'Nữ')),
    ngaysinh DATE NOT NULL,
    sdt VARCHAR(15) UNIQUE,
    diachi VARCHAR(100),
    cccd CHAR(12) UNIQUE NOT NULL,
    matkhau CHAR(8) NOT NULL,
    email VARCHAR(50) UNIQUE
);

-- Add foreign keys after all tables are created
-- We'll handle these constraints when we have data
-- ALTER TABLE student
-- ADD CONSTRAINT fk_student_department 
-- FOREIGN KEY (tenkhoa) REFERENCES department(tenkhoa)
-- ON UPDATE CASCADE
-- ON DELETE SET NULL;

-- ALTER TABLE lecturer
-- ADD CONSTRAINT fk_lecturer_department_tenkhoa 
-- FOREIGN KEY (tenkhoa) REFERENCES department(tenkhoa)
-- ON UPDATE CASCADE
-- ON DELETE SET NULL;

-- Course table
CREATE TABLE IF NOT EXISTS course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mamon CHAR(5) NOT NULL UNIQUE,
    tenmon VARCHAR(100) NOT NULL UNIQUE,
    tinchi TINYINT UNSIGNED NOT NULL,
    tenkhoa VARCHAR(60)
);

-- Class table
CREATE TABLE IF NOT EXISTS class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    malop CHAR(5) NOT NULL UNIQUE,
    tenlop VARCHAR(100) NOT NULL,
    tenmon VARCHAR(100),
    tenkhoa VARCHAR(60),
    soluongSV INT UNSIGNED DEFAULT 0,
    maGV CHAR(6) NOT NULL,
    namhoc CHAR(9) NOT NULL,
    hocky TINYINT NOT NULL
);

-- News table
CREATE TABLE IF NOT EXISTS news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matin CHAR(8) NOT NULL UNIQUE,
    tieude VARCHAR(200) NOT NULL,
    noidung TEXT NOT NULL,
    tailieu_url VARCHAR(255),
    ngaytao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nguoitao VARCHAR(100)
);

-- Add initial admin user
INSERT IGNORE INTO login (tendangnhap, matkhau) 
VALUES ('admin', 'admin123');