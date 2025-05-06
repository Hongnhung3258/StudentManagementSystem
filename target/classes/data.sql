-- Initial data for the University Management System

-- Insert initial departments
MERGE INTO department (makhoa, tenkhoa) KEY(makhoa) 
VALUES 
    ('KH01', 'Computer Science'),
    ('KH02', 'Information Technology'),
    ('KH03', 'Electrical Engineering'),
    ('KH04', 'Mechanical Engineering'),
    ('KH05', 'Business Administration');

-- Insert an admin account
MERGE INTO login (tendangnhap, matkhau) KEY(tendangnhap)
VALUES ('admin', 'admin123');

-- Insert initial lecturer data
MERGE INTO lecturer (maGV, tenkhoa, hoten, gioitinh, ngaysinh, sdt, diachi, cccd, hocvi, chucvu, matkhau) KEY(maGV)
VALUES 
    ('GV0001', 'Computer Science', 'John Smith', 'Nam', '1980-05-15', '0901234567', 'Hanoi, Vietnam', '012345678901', 'Tiến sĩ', 'Trưởng khoa', 'pass1234'),
    ('GV0002', 'Information Technology', 'Jane Doe', 'Nữ', '1985-08-20', '0909876543', 'Ho Chi Minh City, Vietnam', '098765432109', 'Tiến sĩ', 'Trưởng khoa', 'pass1234'),
    ('GV0003', 'Computer Science', 'David Johnson', 'Nam', '1978-03-10', '0907654321', 'Danang, Vietnam', '023456789012', 'PGS.TS', 'Giảng viên', 'pass1234');

-- Insert initial course data
MERGE INTO course (mamon, tenmon, tinchi, tenkhoa) KEY(mamon)
VALUES 
    ('MH001', 'Programming Fundamentals', 3, 'Computer Science'),
    ('MH002', 'Database Systems', 4, 'Computer Science'),
    ('MH003', 'Web Development', 3, 'Information Technology'),
    ('MH004', 'Computer Networks', 3, 'Information Technology'),
    ('MH005', 'Software Engineering', 4, 'Computer Science');

-- Insert class data
MERGE INTO class (malop, tenlop, tenmon, tenkhoa, soluongSV, maGV, namhoc, hocky) KEY(malop)
VALUES 
    ('LH001', 'Programming Class A', 'Programming Fundamentals', 'Computer Science', 30, 'GV0001', '2023-2024', 1),
    ('LH002', 'Database Systems Class B', 'Database Systems', 'Computer Science', 25, 'GV0003', '2023-2024', 1),
    ('LH003', 'Web Development Class A', 'Web Development', 'Information Technology', 35, 'GV0002', '2023-2024', 2);

-- Insert initial student data
MERGE INTO student (khoa, maSV, tenkhoa, hoten, gioitinh, ngaysinh, sdt, diachi, cccd, matkhau) KEY(maSV)
VALUES 
    ('CS', 'SV0001', 'Computer Science', 'Nguyen Van A', 'Nam', '2003-01-15', '0912345678', 'Hanoi, Vietnam', '123456789012', 'pass1234'),
    ('CS', 'SV0002', 'Computer Science', 'Tran Thi B', 'Nữ', '2003-05-20', '0923456789', 'Hanoi, Vietnam', '234567890123', 'pass1234'),
    ('IT', 'SV0003', 'Information Technology', 'Le Van C', 'Nam', '2002-12-10', '0934567890', 'Ho Chi Minh City, Vietnam', '345678901234', 'pass1234');

-- Insert news items
MERGE INTO news (matin, tieude, noidung, nguoitao, ngaytao) KEY(matin)
VALUES 
    ('TT000001', 'Welcome to the new semester!', 'The new academic year 2023-2024 has officially begun. We welcome all new and returning students.', 'Admin', NOW()),
    ('TT000002', 'Course registration deadline', 'The deadline for course registration is September 15, 2023. Please make sure to register for your courses before the deadline.', 'Admin', NOW()),
    ('TT000003', 'Library hours extended', 'The university library has extended its opening hours during the exam period. New hours: 8:00 AM - 10:00 PM', 'Admin', NOW());