create database ums;
use ums;

CREATE TABLE login (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('Admin', 'Student', 'Lecturer'))  
);

CREATE TABLE persistent_logins (
    username VARCHAR(40) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    FOREIGN KEY (username) REFERENCES login(username)
);

-- Department table
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    department_code VARCHAR(4) NOT NULL UNIQUE,
    department_name VARCHAR(60) NOT NULL UNIQUE,
    head_lecturer_code VARCHAR(6)
);


-- Lecturer table
CREATE TABLE lecturer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecturer_id VARCHAR(8) NOT NULL UNIQUE,
    department_name VARCHAR(60),
    full_name VARCHAR(50) NOT NULL,
    gender ENUM('Nam', 'Nữ') NOT NULL,
    date_of_birth DATE NOT NULL,
    phone_number VARCHAR(15) UNIQUE,
    address VARCHAR(100),
    national_id CHAR(12) UNIQUE NOT NULL,
    degree VARCHAR(50) CHECK (degree IN ('Cử nhân', 'Thạc sĩ', 'Tiến sĩ','PGS.TS', 'GS.TS', 'GS.TS.BS', 'PGS.TS.BS', 'TS.BS')), 
    position VARCHAR(50) CHECK (position IN ('Trưởng khoa', 'Giảng viên', 'Trợ giảng', 'Phó trưởng khoa')),
    password CHAR(8) NOT NULL,
    email VARCHAR(50) GENERATED ALWAYS AS (CONCAT(lecturer_id, '@tchr.phenikaa-uni.edu.vn')) STORED UNIQUE,
    CONSTRAINT fk_lecturer_department FOREIGN KEY (department_name) 
        REFERENCES department(department_name) ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_lecturer_login FOREIGN KEY (email) 
        REFERENCES login(username)
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    intake CHAR(2),
    student_id VARCHAR(8) NOT NULL UNIQUE,
    department_name VARCHAR(60),
    full_name VARCHAR(50) NOT NULL,
    gender ENUM('Nam', 'Nữ') NOT NULL,
    date_of_birth DATE NOT NULL,
    phone_number VARCHAR(15) UNIQUE,
    address VARCHAR(100),
    national_id CHAR(12) UNIQUE NOT NULL,
    password CHAR(8) NOT NULL,
    email VARCHAR(50) GENERATED ALWAYS AS (CONCAT(student_id, '@st.phenikaa-uni.edu.vn')) STORED UNIQUE,
    CONSTRAINT fk_student_department FOREIGN KEY (department_name) 
        REFERENCES department(department_name) ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_student_login FOREIGN KEY (email) 
		REFERENCES login(username)
);

CREATE TABLE course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(7) NOT NULL UNIQUE,
    course_name VARCHAR(100) NOT NULL UNIQUE,
    credits TINYINT UNSIGNED CHECK (credits BETWEEN 1 AND 10) NOT NULL,
    department_name VARCHAR(60),
    CONSTRAINT fk_course_department FOREIGN KEY (department_name) 
        REFERENCES department(department_name) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE class (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    class_code VARCHAR(7) NOT NULL UNIQUE,
    class_name VARCHAR(100) NOT NULL,
    course_name VARCHAR(100),
    department_name VARCHAR(60),
    student_count INT UNSIGNED DEFAULT 0 CHECK (student_count <= 500),
    lecturer_id VARCHAR(8) NOT NULL,
    academic_year CHAR(9) NOT NULL CHECK (academic_year REGEXP '^[0-9]{4}-[0-9]{4}$'),
    semester TINYINT CHECK (semester BETWEEN 1 AND 4) NOT NULL,
    CONSTRAINT fk_class_course FOREIGN KEY (course_name) 
        REFERENCES course(course_name) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_class_lecturer FOREIGN KEY (lecturer_id) 
        REFERENCES lecturer(lecturer_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_class_department FOREIGN KEY (department_name) 
        REFERENCES department(department_name) ON UPDATE CASCADE ON DELETE SET NULL
);

-- News table
CREATE TABLE news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    news_code VARCHAR(8) NOT NULL UNIQUE,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    document_url VARCHAR(255),
    image VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    author VARCHAR(100),
    created_by VARCHAR(40),
    CONSTRAINT fk_news_created_by FOREIGN KEY (created_by) 
    REFERENCES login(username)
);

CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    class_code VARCHAR(7) NOT NULL,
    lecturer_id VARCHAR(8) NOT NULL,
    classroom VARCHAR(20) NOT NULL,
    start_period TINYINT UNSIGNED NOT NULL CHECK (start_period BETWEEN 1 AND 12),
    end_period TINYINT UNSIGNED NOT NULL CHECK (end_period BETWEEN 2 AND 12),
    weekday ENUM('Thứ hai', 'Thứ ba', 'Thứ tư', 'Thứ năm', 'Thứ sáu', 'Thứ bảy', 'Chủ nhật') NOT NULL,
    start_date DATE NOT NULL,
    academic_year CHAR(9) NOT NULL CHECK (academic_year REGEXP '^[0-9]{4}-[0-9]{4}$'),
    semester TINYINT NOT NULL CHECK (semester BETWEEN 1 AND 4),
    schedule_summary VARCHAR(50) GENERATED ALWAYS AS (CONCAT(weekday, ' ', start_period, '-', end_period)) STORED,
    UNIQUE (class_code, start_period, weekday, academic_year, semester),
    UNIQUE (classroom, start_period, weekday, academic_year, semester),
    UNIQUE (lecturer_id, start_period, weekday, academic_year, semester),
    FOREIGN KEY (class_code) REFERENCES class(class_code) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Activity Log table
CREATE TABLE activity_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(40) NOT NULL,
    action VARCHAR(50) NOT NULL,
    target VARCHAR(50) NOT NULL,
    target_id BIGINT NOT NULL,
    timestamp DATETIME NOT NULL,
    CONSTRAINT fk_login_activity FOREIGN KEY (username) 
        REFERENCES login(username) ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Payment table
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(8) NOT NULL,
    payment_date DATE NOT NULL,
    amount DECIMAL(15,2) NOT NULL CHECK (amount >= 0),
    description VARCHAR(200) NOT NULL,
    status ENUM('Xác nhận', 'Đang chờ', 'Hết hạn') NOT NULL DEFAULT 'Đang chờ',
    approved_by VARCHAR(40),
    approval_date DATETIME,
    CONSTRAINT fk_payment_student FOREIGN KEY (student_id)
        REFERENCES student(student_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_payment_login FOREIGN KEY (approved_by)
        REFERENCES login(username) ON UPDATE CASCADE ON DELETE SET NULL
);
