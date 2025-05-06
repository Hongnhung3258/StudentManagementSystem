-- Initialize database schema for the University Management System

-- Login table
CREATE TABLE IF NOT EXISTS login (
    stt SERIAL PRIMARY KEY,
    tendangnhap VARCHAR(40) NOT NULL UNIQUE,
    matkhau VARCHAR(30) NOT NULL
);

-- Department table
CREATE TABLE IF NOT EXISTS department (
    id SERIAL PRIMARY KEY,
    makhoa CHAR(4) NOT NULL UNIQUE,
    tenkhoa VARCHAR(60) NOT NULL UNIQUE,
    matruongkhoa CHAR(6)
);

-- Lecturer table (create before adding the foreign key to department)
CREATE TABLE IF NOT EXISTS lecturer (
    id SERIAL PRIMARY KEY,
    maGV CHAR(6) NOT NULL UNIQUE,
    tenkhoa VARCHAR(60),
    hoten VARCHAR(50) NOT NULL,
    gioitinh VARCHAR(3) NOT NULL CHECK (gioitinh IN ('Nam', 'Nữ')),
    ngaysinh DATE NOT NULL,
    sdt VARCHAR(15) UNIQUE,
    diachi VARCHAR(100),
    cccd CHAR(12) UNIQUE NOT NULL,
    hocvi VARCHAR(50) CHECK (hocvi IN ('Cử nhân', 'Thạc sĩ', 'Tiến sĩ', 'PGS.TS', 'GS.TS', 'GS.TS.BS', 'PGS.TS.BS', 'TS.BS')), 
    chucvu VARCHAR(50) CHECK (chucvu IN('Trưởng khoa', 'Giảng viên', 'Trợ giảng', 'Phó trưởng khoa')),
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
    id SERIAL PRIMARY KEY,
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
    id SERIAL PRIMARY KEY,
    mamon CHAR(5) NOT NULL UNIQUE,
    tenmon VARCHAR(100) NOT NULL UNIQUE,
    tinchi SMALLINT CHECK (tinchi BETWEEN 1 AND 10) NOT NULL,
    tenkhoa VARCHAR(60)
    -- Foreign key will be added later
    -- CONSTRAINT fk_course_department 
    -- FOREIGN KEY (tenkhoa) REFERENCES department(tenkhoa)
    -- ON UPDATE CASCADE
    -- ON DELETE SET NULL
);

-- Class table
CREATE TABLE IF NOT EXISTS class (
    id SERIAL PRIMARY KEY,
    malop CHAR(5) NOT NULL UNIQUE,
    tenlop VARCHAR(100) NOT NULL,
    tenmon VARCHAR(100),
    tenkhoa VARCHAR(60),
    soluongSV INTEGER DEFAULT 0 CHECK (soluongSV <= 500),
    maGV CHAR(6) NOT NULL,
    namhoc CHAR(9) NOT NULL CHECK (namhoc ~ '^[0-9]{4}-[0-9]{4}$'),
    hocky SMALLINT CHECK (hocky BETWEEN 1 AND 4) NOT NULL
    -- Foreign keys will be added later
    -- CONSTRAINT fk_class_course 
    -- FOREIGN KEY (tenmon) REFERENCES course(tenmon)
    -- ON UPDATE CASCADE
    -- ON DELETE RESTRICT,
    -- CONSTRAINT fk_class_lecturer 
    -- FOREIGN KEY (maGV) REFERENCES lecturer(maGV)
    -- ON UPDATE CASCADE
    -- ON DELETE RESTRICT,
    -- CONSTRAINT fk_class_department 
    -- FOREIGN KEY (tenkhoa) REFERENCES department(tenkhoa)
    -- ON UPDATE CASCADE
    -- ON DELETE SET NULL
);

-- News table
CREATE TABLE IF NOT EXISTS news (
    id SERIAL PRIMARY KEY,
    matin CHAR(8) NOT NULL UNIQUE,
    tieude VARCHAR(200) NOT NULL,
    noidung TEXT NOT NULL,
    tailieu_url VARCHAR(255),
    ngaytao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nguoitao VARCHAR(100)
);

-- Add initial admin user
INSERT INTO login (tendangnhap, matkhau) 
VALUES ('admin', 'admin123')
ON CONFLICT (tendangnhap) DO NOTHING;