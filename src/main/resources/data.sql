-- Initial data for the University Management System

-- Insert initial departments
INSERT INTO department (department_code, department_name, head_lecturer_code)
VALUES
    (1022, 'Khách sạn', '144924'),
    (1021, 'Ngôn ngữ Hàn Quốc', NULL),
    (1020, 'Ngôn ngữ Pháp', NULL),
    (1019, 'Ngôn ngữ Nhật Bản', NULL),
    (1018, 'Ngôn ngữ Trung Quốc', NULL),
    (1017, 'Ngôn ngữ Anh', '145088'),
    (1016, 'Khoa học Đông Phương', NULL),
    (1015, 'Y tế công cộng', NULL),
    (1014, 'Răng hàm mặt', NULL),
    (1013, 'Điều dưỡng', NULL),
    (1012, 'Y học cổ truyền', NULL),
    (1011, 'Y', '147424'),
    (1010, 'Dược', '145037'),
    (1009, 'Khoa học Y sinh', '142701'),
    (1008, 'Kỹ thuật Y học', '143335'),
    (1007, 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường', '142777'),
    (1006, 'Cơ khí - Cơ điện tử', '146236'),
    (1005, 'Điện - Điện tử', '142837'),
    (1004, 'Khoa học và Kỹ thuật vật liệu', '147136'),
    (1003, 'Du lịch', '144809'),
    (1002, 'Kinh tế và Kinh doanh', '141781'),
    (1001, 'Kỹ thuật ô tô và Năng lượng', '149193'),
    (1000, 'Công nghệ thông tin', '144690');

-- Insert an admin account  
INSERT INTO login (username, password, role)
VALUES 
('admin', '12345678', 'Admin'),
('admin1', '23456789', 'Admin'),
('admin2', '00000000', 'Admin'),
('admin3', '46572387', 'Admin'),
('admin4', '12345678', 'Admin');
('200177', '1111111', 'Student');
('140210', '12345678', 'Lecturer');

-- Insert initial lecturer data
INSERT INTO lecturer (lecturer_id, department_name, full_name, gender, date_of_birth, phone_number, address, national_id, degree, position, password)
VALUES
    ('143941', 'Ngôn ngữ Nhật Bản', 'Trương Thị Mai', 'Nữ', '1979-04-03', '0983360522', 'Tầng 11 - A9', '038254667854', 'Tiến sĩ', 'Trưởng khoa', '5C*snavs', '143941@tchr.phenikaa-uni.edu.vn'),
    ('147002', 'Khoa học Đông Phương', 'Nguyễn Văn Khang', 'Nam', '1967-07-21', '0912118665', 'Tầng 12 - A9', '038254678452', 'GS.TS', 'Trưởng khoa', 'G4I@1wwX', '147002@tchr.phenikaa-uni.edu.vn'),
    ('145088', 'Ngôn ngữ Anh', 'Nguyễn Đăng Sửu', 'Nam', '1951-06-28', '0928576410', 'Tầng 12A - A9', '038697004631', 'PGS.TS', 'Trưởng khoa', 'LTqR9Kh@', '145088@tchr.phenikaa-uni.edu.vn'),
    ('147424', 'Y', 'Nguyễn Thanh Hồi', 'Nam', '1973-07-17', '02466873489', 'Khoa Y - Đại học Phenikaa', '038574659068', 'PGS.TS', 'Trưởng khoa', '4kS#@W99', '147424@tchr.phenikaa-uni.edu.vn'),
    ('145037', 'Dược', 'Nguyễn Văn Hùng', 'Nam', '1960-08-16', '0913552872', 'Tầng 22 - A9', '038154627834', 'PGS.TS', 'Trưởng khoa', 'T,y47j!L', '145037@tchr.phenikaa-uni.edu.vn'),
    ('142701', 'Khoa học Y sinh', 'Lưu Ngọc Hoạt', 'Nam', '1956-10-15', '0913232714', 'Tầng 21 - A9', '037219475860', 'GS.TS.BS', 'Trưởng khoa', '&@56OiSk', '142701@tchr.phenikaa-uni.edu.vn'),
    ('144924', 'Khách sạn', 'Trần Đức Thành', 'Nam', '1979-10-16', '0913808163', 'Tầng 14 - A9', '038214453657', 'Tiến sĩ', 'Phó trưởng khoa', 'W%CVV9Cc', '144924@tchr.phenikaa-uni.edu.vn'),
    ('143335', 'Kỹ thuật Y học', 'Cao Minh Châu', 'Nam', '1954-07-07', '0913343461', 'Tầng 22 - A9', '037283645967', 'GS.TS.BS', 'Trưởng khoa', '5K2^8Ca,', '143335@tchr.phenikaa-uni.edu.vn'),
    ('142777', 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường', 'Chử Lương Luân', 'Nam', '1987-07-10', '0985464929', 'Phòng 603 - A5', '038465721964', 'Tiến sĩ', 'Giảng viên', 'u2bdIjY$', '142777@tchr.phenikaa-uni.edu.vn'),
    ('146236', 'Cơ khí - Cơ điện tử', 'Vũ Lê Huy', 'Nam', '1979-07-18', '0913541514', 'Tầng 17 - A9', '038564760481', 'PGS.TS', 'Trưởng khoa', 'J@Ftv5WE', '146236@tchr.phenikaa-uni.edu.vn'),
    ('142837', 'Điện - Điện tử', 'Nguyễn Văn Hiếu', 'Nam', '1978-10-08', '0912786572', 'Phòng 708 - A2', '038273659067', 'PGS.TS', 'Trưởng khoa', 'y1KV0N.P', '142837@tchr.phenikaa-uni.edu.vn'),
    ('147136', 'Khoa học và Kỹ thuật vật liệu', 'Phương Đình Tâm', 'Nam', '1979-10-25', '0906158386', 'Tầng 18 - A9', '038102948576', 'PGS.TS', 'Trưởng khoa', 'WN#^v3tT', '147136@tchr.phenikaa-uni.edu.vn'),
    ('141781', 'Kinh tế và Kinh doanh', 'Trần Thị Minh Hòa', 'Nữ', '1966-05-22', '0904226130', 'Phòng 304 - A2', '038103758497', 'PGS.TS', 'Trưởng khoa', 'eBT.6eK3', '141781@tchr.phenikaa-uni.edu.vn'),
    ('144809', 'Du lịch', 'Nguyễn Quang Vinh', 'Nam', '1975-01-31', '02438588591', 'Tầng 14 - A9', '037485620175', 'Tiến sĩ', 'Trưởng khoa', 'v%Ft$5qe', '144809@tchr.phenikaa-uni.edu.vn'),
    ('149193', 'Kỹ thuật ô tô và Năng lượng', 'Trần Quang Vinh', 'Nam', '1975-05-15', '0912153295', 'Tầng 17 - A9', '038254760987', 'PGS.TS', 'Trưởng khoa', '0rtQr2$5', '149193@tchr.phenikaa-uni.edu.vn'),
    ('144690', 'Công nghệ thông tin', 'Ngô Hồng Sơn', 'Nam', '2025-09-04', '02466569110', 'Tầng 15 - A9', '038405993857', 'PGS.TS', 'Trưởng khoa', 'SK7@Uo%Z', '144690@tchr.phenikaa-uni.edu.vn'),
    ('140001', 'Công nghệ thông tin', 'Nguyễn Văn Hùng', 'Nam', '1975-03-15', '0912345001', 'Tầng 15- A9', '001975000001', 'Tiến sĩ', 'Giảng viên', 'Kj#9mP$2'),
    ('140002', 'Công nghệ thông tin', 'Trần Thị Lan', 'Nữ', '1980-07-22', '0912345002', 'Phòng 202 - A2', '001980000002', 'Thạc sĩ', 'Trợ giảng', 'Xy@5vN!8'),
    ('140003', 'Kỹ thuật ô tô và Năng lượng', 'Lê Minh Tuấn', 'Nam', '1978-05-10', '0912345003', 'Tầng 13 - A9', '001978000003', 'PGS.TS', 'Phó trưởng khoa', 'Zm*3kQ$7'),
    ('140004', 'Kỹ thuật ô tô và Năng lượng', 'Phạm Thị Hồng', 'Nữ', '1985-11-30', '0912345004', 'Tầng 10- A9', '001985000004', 'Thạc sĩ', 'Giảng viên', 'Rw#8pL@6'),
    ('140005', 'Kinh tế và Kinh doanh', 'Hoàng Văn Nam', 'Nam', '1970-09-05', '0912345005', 'P505/T25 - A5', '001970000005', 'GS.TS', 'Giảng viên', 'Tf$2jM%9'),
    ('140006', 'Kinh tế và Kinh doanh', 'Nguyễn Thị Mai', 'Nữ', '1982-02-18', '0912345006', 'P606/T2 - A6', '001982000006', 'Tiến sĩ', 'Trợ giảng', 'Yh@4nK#1'),
    ('140007', 'Du lịch', 'Trần Văn Bình', 'Nam', '1976-12-25', '0912345007', 'P707/T7 - A7', '001976000007', 'Thạc sĩ', 'Giảng viên', 'Uk*6mP$3'),
    ('140008', 'Du lịch', 'Lê Thị Thu', 'Nữ', '1988-04-12', '0912345008', 'P808/T12 - A8', '001988000008', 'Cử nhân', 'Trợ giảng', 'Si#9vN!5'),
    ('140009', 'Khoa học và Kỹ thuật vật liệu', 'Phạm Văn Long', 'Nam', '1974-06-20', '0912345009', 'P909/T17 - A9', '001974000009', 'Tiến sĩ', 'Giảng viên', 'Qj@7kQ$2'),
    ('140010', 'Khoa học và Kỹ thuật vật liệu', 'Hoàng Thị Ngọc', 'Nữ', '1983-08-15', '0912345010', 'P101/T22 - A10', '001983000010', 'Thạc sĩ', 'Phó trưởng khoa', 'Pl*3mP@8'),
    ('140011', 'Điện - Điện tử', 'Nguyễn Văn Anh', 'Nam', '1977-01-25', '0912345011', 'P102/T3 - A1', '001977000011', 'PGS.TS', 'Giảng viên', 'Kj#9mP$2'),
    ('140012', 'Điện - Điện tử', 'Trần Thị Hoa', 'Nữ', '1981-10-10', '0912345012', 'P203/T8 - A2', '001981000012', 'Thạc sĩ', 'Giảng viên', 'Xy@5vN!8'),
    ('140013', 'Cơ khí - Cơ điện tử', 'Lê Văn Dũng', 'Nam', '1979-03-05', '0912345013', 'P304/T13 - A3', '001979000013', 'Tiến sĩ', 'Giảng viên', 'Zm*3kQ$7'),
    ('140014', 'Cơ khí - Cơ điện tử', 'Phạm Thị Yến', 'Nữ', '1986-07-15', '0912345014', 'P405/T18 - A4', '001986000014', 'Thạc sĩ', 'Trợ giảng', 'Rw#8pL@6'),
    ('140015', 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường', 'Hoàng Văn Khoa', 'Nam', '1972-09-20', '0912345015', 'P506/T23 - A5', '001972000015', 'GS.TS', 'Giảng viên', 'Tf$2jM%9'),
    ('140016', 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường', 'Nguyễn Thị Linh', 'Nữ', '1984-11-12', '0912345016', 'P607/T4 - A6', '001984000016', 'Thạc sĩ', 'Giảng viên', 'Yh@4nK#1'),
    ('140017', 'Kỹ thuật Y học', 'Trần Văn Minh', 'Nam', '1973-05-30', '0912345017', 'P708/T9 - A7', '001973000017', 'TS.BS', 'Giảng viên', 'Uk*6mP$3'),
    ('140018', 'Khoa học Y sinh', 'Lê Thị Hạnh', 'Nữ', '1987-02-28', '0912345018', 'P809/T14 - A8', '001987000018', 'Thạc sĩ', 'Trợ giảng', 'Si#9vN!5'),
    ('140019', 'Dược', 'Phạm Văn Hậu', 'Nam', '1971-04-15', '0912345019', 'P910/T19 - A9', '001971000019', 'PGS.TS', 'Giảng viên', 'Qj@7kQ$2'),
    ('140020', 'Y', 'Hoàng Thị Oanh', 'Nữ', '1980-06-22', '0912345020', 'P103/T24 - A10', '001980000020', 'PGS.TS.BS', 'Phó trưởng khoa', 'Pl*3mP@8'),
    ('140021', 'Y học cổ truyền', 'Nguyễn Văn Phúc', 'Nam', '1975-08-10', '0912345021', 'P104/T5 - A1', '001975000021', 'Tiến sĩ', 'Giảng viên', 'Kj#9mP$2'),
    ('140022', 'Điều dưỡng', 'Trần Thị Quỳnh', 'Nữ', '1982-12-05', '0912345022', 'P205/T10 - A2', '001982000022', 'Thạc sĩ', 'Giảng viên', 'Xy@5vN!8'),
    ('140023', 'Răng hàm mặt', 'Lê Văn Sơn', 'Nam', '1978-03-20', '0912345023', 'P306/T15 - A3', '001978000023', 'TS.BS', 'Giảng viên', 'Zm*3kQ$7'),
    ('140024', 'Y tế công cộng', 'Phạm Thị Thảo', 'Nữ', '1985-05-15', '0912345024', 'P407/T20 - A4', '001985000024', 'Thạc sĩ', 'Trợ giảng', 'Rw#8pL@6'),
    ('140025', 'Khoa học Đông Phương', 'Hoàng Văn Tâm', 'Nam', '1976-07-25', '0912345025', 'P508/T25 - A5', '001976000025', 'Tiến sĩ', 'Giảng viên', 'Tf$2jM%9'),
    ('140026', 'Ngôn ngữ Anh', 'Nguyễn Thị Vân', 'Nữ', '1983-09-30', '0912345026', 'P609/T6 - A6', '001983000026', 'Thạc sĩ', 'Giảng viên', 'Yh@4nK#1'),
    ('140027', 'Ngôn ngữ Trung Quốc', 'Trần Văn Xuân', 'Nam', '1974-11-10', '0912345027', 'P710/T11 - A7', '001974000027', 'Thạc sĩ', 'Giảng viên', 'Uk*6mP$3'),
    ('140028', 'Ngôn ngữ Nhật Bản', 'Lê Thị Ánh', 'Nữ', '1981-01-22', '0912345028', 'P811/T16 - A8', '001981000028', 'Thạc sĩ', 'Trợ giảng', 'Si#9vN!5'),
    ('140029', 'Ngôn ngữ Pháp', 'Phạm Văn Bảo', 'Nam', '1979-04-18', '0912345029', 'P912/T21 - A9', '001979000029', 'Tiến sĩ', 'Giảng viên', 'Qj@7kQ$2'),
    ('140030', NULL, 'Hoàng Thị Duyên', 'Nữ', '1986-06-12', '0912345030', 'P105/T26 - A10', '001986000030', 'Thạc sĩ', 'Giảng viên', 'Pl*3mP@8');

-- Insert initial course data
INSERT INTO course (course_code, course_name, credits, department_name)
VALUES
    ('00001', 'Lập trình Java', 3, 'Công nghệ thông tin'),
    ('00002', 'Cơ sở dữ liệu', 3, 'Công nghệ thông tin'),
    ('00003', 'Trí tuệ nhân tạo', 4, 'Công nghệ thông tin'),
    ('00004', 'Kỹ thuật ô tô', 4, 'Kỹ thuật ô tô và Năng lượng'),
    ('00005', 'Năng lượng tái tạo', 3, 'Kỹ thuật ô tô và Năng lượng'),
    ('00006', 'Quản trị kinh doanh', 3, 'Kinh tế và Kinh doanh'),
    ('00007', 'Marketing', 3, 'Kinh tế và Kinh doanh'),
    ('00008', 'Tài chính doanh nghiệp', 4, 'Kinh tế và Kinh doanh'),
    ('00009', 'Quản lý du lịch', 3, 'Du lịch'),
    ('00010', 'Lập kế hoạch du lịch', 3, 'Du lịch'),
    ('00011', 'Vật liệu nano', 4, 'Khoa học và Kỹ thuật vật liệu'),
    ('00012', 'Kỹ thuật vật liệu', 3, 'Khoa học và Kỹ thuật vật liệu'),
    ('00013', 'Điện tử công suất', 4, 'Điện - Điện tử'),
    ('00014', 'Hệ thống nhúng', 3, 'Điện - Điện tử'),
    ('00015', 'Cơ khí chế tạo', 4, 'Cơ khí - Cơ điện tử'),
    ('00016', 'Tự động hóa', 3, 'Cơ khí - Cơ điện tử'),
    ('00017', 'Công nghệ sinh học', 4, 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường'),
    ('00018', 'Kỹ thuật môi trường', 3, 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường'),
    ('00019', 'Kỹ thuật xét nghiệm y học', 4, 'Kỹ thuật Y học'),
    ('00020', 'Sinh học phân tử', 3, 'Khoa học Y sinh'),
    ('00021', 'Dược lý học', 4, 'Dược'),
    ('00022', 'Hóa dược', 3, 'Dược'),
    ('00023', 'Giải phẫu học', 4, 'Y'),
    ('00024', 'Bệnh lý học', 4, 'Y'),
    ('00025', 'Y học cổ truyền Việt Nam', 3, 'Y học cổ truyền'),
    ('00026', 'Châm cứu', 3, 'Y học cổ truyền'),
    ('00027', 'Điều dưỡng cơ bản', 3, 'Điều dưỡng'),
    ('00028', 'Chăm sóc bệnh nhân', 4, 'Điều dưỡng'),
    ('00029', 'Nha khoa lâm sàng', 4, 'Răng hàm mặt'),
    ('00030', 'Kỹ thuật chỉnh nha', 3, 'Răng hàm mặt'),
    ('00031', 'Y tế cộng đồng', 3, 'Y tế công cộng'),
    ('00032', 'Văn hóa Đông Phương', 3, 'Khoa học Đông Phương'),
    ('00033', 'Tiếng Anh thương mại', 3, 'Ngôn ngữ Anh'),
    ('00034', 'Dịch thuật Anh-Việt', 3, 'Ngôn ngữ Anh'),
    ('00035', 'Tiếng Trung thương mại', 3, 'Ngôn ngữ Trung Quốc'),
    ('00036', 'Văn hóa Trung Quốc', 3, 'Ngôn ngữ Trung Quốc'),
    ('00037', 'Tiếng Nhật giao tiếp', 3, 'Ngôn ngữ Nhật Bản'),
    ('00038', 'Văn học Nhật Bản', 3, 'Ngôn ngữ Nhật Bản'),
    ('00039', 'Tiếng Pháp cơ bản', 3, 'Ngôn ngữ Pháp'),
    ('00040', 'Văn hóa Pháp', 3, 'Ngôn ngữ Pháp'),
    ('00041', 'Tiếng Hàn giao tiếp', 3, 'Ngôn ngữ Hàn Quốc'),
    ('00042', 'Văn hóa Hàn Quốc', 3, 'Ngôn ngữ Hàn Quốc'),
    ('00043', 'Quản lý khách sạn', 4, 'Khách sạn'),
    ('00044', 'Dịch vụ nhà hàng', 3, 'Khách sạn'),
    ('00045', 'Toán đại cương', 3, NULL),
    ('00046', 'Vật lý đại cương', 3, NULL),
    ('00047', 'Hóa học đại cương', 3, NULL),
    ('00048', 'Kỹ năng mềm', 2, NULL),
    ('00049', 'Lịch sử Việt Nam', 2, NULL),
    ('00050', 'Triết học Mác-Lênin', 2, NULL);

-- Insert class data
INSERT INTO class (class_code, class_name, course_name, department_name, student_count, lecturer_id, academic_year, semester)
VALUES
    ('00001', 'Lớp Lập trình Java HK1 2023-2024', 'Lập trình Java', 'Công nghệ thông tin', 30, '140001', '2023-2024', 1),
    ('00002', 'Lớp Cơ sở dữ liệu HK2 2023-2024', 'Cơ sở dữ liệu', 'Công nghệ thông tin', 35, '140002', '2023-2024', 2),
    ('00003', 'Lớp Trí tuệ nhân tạo HK1 2024-2025', 'Trí tuệ nhân tạo', 'Công nghệ thông tin', 25, '140001', '2024-2025', 1),
    ('00004', 'Lớp Kỹ thuật ô tô HK1 2023-2024', 'Kỹ thuật ô tô', 'Kỹ thuật ô tô và Năng lượng', 28, '140003', '2023-2024', 1),
    ('00005', 'Lớp Năng lượng tái tạo HK2 2023-2024', 'Năng lượng tái tạo', 'Kỹ thuật ô tô và Năng lượng', 30, '140004', '2023-2024', 2),
    ('00006', 'Lớp Quản trị kinh doanh HK1 2023-2024', 'Quản trị kinh doanh', 'Kinh tế và Kinh doanh', 40, '140005', '2023-2024', 1),
    ('00007', 'Lớp Marketing HK2 2023-2024', 'Marketing', 'Kinh tế và Kinh doanh', 35, '140006', '2023-2024', 2),
    ('00008', 'Lớp Tài chính doanh nghiệp HK1 2024-2025', 'Tài chính doanh nghiệp', 'Kinh tế và Kinh doanh', 38, '140005', '2024-2025', 1),
    ('00009', 'Lớp Quản lý du lịch HK1 2023-2024', 'Quản lý du lịch', 'Du lịch', 32, '140007', '2023-2024', 1),
    ('00010', 'Lớp Lập kế hoạch du lịch HK2 2023-2024', 'Lập kế hoạch du lịch', 'Du lịch', 30, '140008', '2023-2024', 2),
    ('00011', 'Lớp Vật liệu nano HK1 2024-2025', 'Vật liệu nano', 'Khoa học và Kỹ thuật vật liệu', 25, '140009', '2024-2025', 1),
    ('00012', 'Lớp Kỹ thuật vật liệu HK2 2024-2025', 'Kỹ thuật vật liệu', 'Khoa học và Kỹ thuật vật liệu', 28, '140010', '2024-2025', 2),
    ('00013', 'Lớp Điện tử công suất HK1 2023-2024', 'Điện tử công suất', 'Điện - Điện tử', 30, '140011', '2023-2024', 1),
    ('00014', 'Lớp Hệ thống nhúng HK2 2023-2024', 'Hệ thống nhúng', 'Điện - Điện tử', 32, '140012', '2023-2024', 2),
    ('00015', 'Lớp Cơ khí chế tạo HK1 2024-2025', 'Cơ khí chế tạo', 'Cơ khí - Cơ điện tử', 35, '140013', '2024-2025', 1),
    ('00016', 'Lớp Tự động hóa HK2 2024-2025', 'Tự động hóa', 'Cơ khí - Cơ điện tử', 30, '140014', '2024-2025', 2),
    ('00017', 'Lớp Công nghệ sinh học HK1 2023-2024', 'Công nghệ sinh học', 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường', 28, '140015', '2023-2024', 1),
    ('00018', 'Lớp Kỹ thuật môi trường HK2 2023-2024', 'Kỹ thuật môi trường', 'Công nghệ sinh học, Hóa học và Kỹ thuật môi trường', 25, '140016', '2023-2024', 2),
    ('00019', 'Lớp Kỹ thuật xét nghiệm y học HK1 2024-2025', 'Kỹ thuật xét nghiệm y học', 'Kỹ thuật Y học', 30, '140017', '2024-2025', 1),
    ('00020', 'Lớp Sinh học phân tử HK2 2024-2025', 'Sinh học phân tử', 'Khoa học Y sinh', 28, '140018', '2024-2025', 2),
    ('00021', 'Lớp Dược lý học HK1 2024-2025', 'Dược lý học', 'Dược', 35, '140019', '2024-2025', 1),
    ('00022', 'Lớp Hóa dược HK2 2024-2025', 'Hóa dược', 'Dược', 30, '140019', '2024-2025', 2),
    ('00023', 'Lớp Giải phẫu học HK1 2024-2025', 'Giải phẫu học', 'Y', 40, '140020', '2024-2025', 1),
    ('00024', 'Lớp Bệnh lý học HK2 2024-2025', 'Bệnh lý học', 'Y', 38, '140020', '2024-2025', 2),
    ('00025', 'Lớp Y học cổ truyền Việt Nam HK1 2024-2025', 'Y học cổ truyền Việt Nam', 'Y học cổ truyền', 25, '140021', '2024-2025', 1),
    ('00026', 'Lớp Điều dưỡng cơ bản HK1 2024-2025', 'Điều dưỡng cơ bản', 'Điều dưỡng', 30, '140022', '2024-2025', 1),
    ('00027', 'Lớp Nha khoa lâm sàng HK1 2024-2025', 'Nha khoa lâm sàng', 'Răng hàm mặt', 28, '140023', '2024-2025', 1),
    ('00028', 'Lớp Y tế cộng đồng HK1 2024-2025', 'Y tế cộng đồng', 'Y tế công cộng', 25, '140024', '2024-2025', 1),
    ('00029', 'Lớp Tiếng Anh thương mại HK1 2024-2025', 'Tiếng Anh thương mại', 'Ngôn ngữ Anh', 35, '140026', '2024-2025', 1),
    ('00030', 'Lớp Tiếng Hàn giao tiếp HK1 2025-2026', 'Tiếng Hàn giao tiếp', 'Ngôn ngữ Hàn Quốc', 30, '140027', '2025-2026', 1),
    ('00031', 'Lớp Toán đại cương HK1 2023-2024', 'Toán đại cương', NULL, 50, '140030', '2023-2024', 1),
    ('00032', 'Lớp Vật lý đại cương HK2 2023-2024', 'Vật lý đại cương', NULL, 48, '140030', '2023-2024', 2),
    ('00033', 'Lớp Hóa học đại cương HK1 2024-2025', 'Hóa học đại cương', NULL, 45, '140030', '2024-2025', 1),
    ('00034', 'Lớp Kỹ năng mềm HK2 2024-2025', 'Kỹ năng mềm', NULL, 40, '140030', '2024-2025', 2),
    ('00035', 'Lớp Lịch sử Việt Nam HK1 2025-2026', 'Lịch sử Việt Nam', NULL, 50, '140030', '2025-2026', 1),
    ('00036', 'Lớp Triết học Mác-Lênin HK2 2025-2026', 'Triết học Mác-Lênin', NULL, 48, '140030', '2025-2026', 2),
    ('00037', 'Lớp Văn hóa Đông Phương HK1 2024-2025', 'Văn hóa Đông Phương', 'Khoa học Đông Phương', 25, '140025', '2024-2025', 1),
    ('00038', 'Lớp Quản lý khách sạn HK1 2025-2026', 'Quản lý khách sạn', 'Khách sạn', 30, '140028', '2025-2026', 1),
    ('0009', 'Lớp Tiếng Nhật giao tiếp HK1 2025-2026', 'Tiếng Nhật giao tiếp', 'Ngôn ngữ Nhật Bản', 28, '140029', '2025-2026', 1),
    ('00040', 'Lớp Tiếng Trung thương mại HK1 2025-2026', 'Tiếng Trung thương mại', 'Ngôn ngữ Trung Quốc', 30, '140027', '2025-2026', 1);

-- Insert initial student data
INSERT INTO student (intake, student_id, department_name, full_name, gender, date_of_birth, phone_number, address, national_id, password)
VALUES
    ('14', '200001', 'Công nghệ thông tin', 'Nguyễn Văn An', 'Nam', '2002-03-15', '0935123001', 'Hà Nội', '001202000001', 'Kj#9mP$2'),
    ('14', '200002', 'Kỹ thuật ô tô và Năng lượng', 'Trần Thị Bình', 'Nữ', '2002-07-22', '0935123002', 'TP.HCM', '001202000002', 'Xy@5vN!8'),
    ('14', '200003', 'Kinh tế và Kinh doanh', 'Lê Minh Châu', 'Nam', '2002-05-10', '0935123003', 'Đà Nẵng', '001202000003', 'Zm*3kQ$7'),
    ('14', '200004', 'Du lịch', 'Phạm Thị Dung', 'Nữ', '2002-11-30', '0935123004', 'Hải Phòng', '001202000004', 'Rw#8pL@6'),
    ('15', '210001', 'Dược', 'Nguyễn Văn Long', 'Nam', '2003-01-25', '0935123011', 'Hà Nội', '001203000011', 'Kj#9mP$2'),
    ('15', '210002', 'Y', 'Trần Thị Mai', 'Nữ', '2003-10-10', '0935123012', 'TP.HCM', '001203000012', 'Xy@5vN!8'),
    ('15', '210003', 'Y học cổ truyền', 'Lê Văn Nam', 'Nam', '2003-03-05', '0935123013', 'Đà Nẵng', '001203000013', 'Zm*3kQ$7'),
    ('16', '220001', 'Ngôn ngữ Pháp', 'Nguyễn Văn Vũ', 'Nam', '2004-08-10', '0935123021', 'Hà Nội', '001204000021', 'Kj#9mP$2'),
    ('16', '220002', 'Ngôn ngữ Hàn Quốc', 'Trần Thị Xuân', 'Nữ', '2004-12-05', '0935123022', 'TP.HCM', '001204000022', 'Xy@5vN!8'),
    ('16', '220003', 'Khách sạn', 'Lê Văn Ý', 'Nam', '2004-03-20', '0935123023', 'Đà Nẵng', '001204000023', 'Zm*3kQ$7'),
    ('16', '220004', 'Công nghệ thông tin', 'Phạm Thị Z', 'Nữ', '2004-05-15', '0935123024', 'Hải Phòng', '001204000024', 'Rw#8pL@6');

-- Insert news data
INSERT INTO news (news_code, title, content, document_url, image, created_at, created_by)
VALUES 
    ('TT000001', 'Khai giảng năm học mới 2025', 'Lễ khai giảng năm học 2025 sẽ diễn ra vào ngày 5/9/2025 tại hội trường lớn. Sinh viên vui lòng có mặt đúng giờ.', '/uploads/khaigiang_2025.pdf', '/uploads/khaigiang_2025.jpg', '2025-09-01 08:00:00', 'Admin'),
    ('TT000002', 'Hội thảo công nghệ AI 2025', 'Hội thảo về trí tuệ nhân tạo sẽ tổ chức vào ngày 15/10/2025 tại phòng hội thảo A1. Đăng ký trước ngày 10/10.', NULL, '/uploads/ai_seminar_2025.jpg', '2025-10-01 09:30:00', 'Nguyễn Văn A'),
    ('TT000003', 'Thông báo tuyển sinh 2025', 'Thông báo tuyển sinh đại học năm 2025. Hạn nộp hồ sơ: 30/6/2025. Xem chi tiết tại website trường.', '/uploads/tuyensinh_2025.pdf', NULL, '2025-06-01 10:00:00', 'Trần Thị B'),
    ('TT000004', 'Ngày hội thể thao sinh viên', 'Ngày hội thể thao sẽ diễn ra vào 20/11/2025 tại sân vận động. Các đội đăng ký trước ngày 15/11.', '/uploads/thethao_2025.doc', '/uploads/thethao_2025.png', '2025-11-01 07:45:00', 'Admin'),
    ('TT000005', 'Học bổng khuyến học kỳ 1/2025', 'Thông báo học bổng khuyến học cho sinh viên xuất sắc. Hạn nộp hồ sơ: 15/12/2025.', NULL, NULL, '2025-12-01 14:20:00', 'Lê Minh C'),
    ('TT000006', 'Cuộc thi lập trình PTIT Code 2025', 'Cuộc thi lập trình dành cho sinh viên sẽ tổ chức vào 25/12/2025. Đăng ký tại phòng đào tạo.', '/uploads/ptitcode_2025.pdf', '/uploads/ptitcode_2025.jpg', '2025-12-10 11:00:00', 'Phạm Văn D');

-- Insert activity log data
INSERT INTO activity_log (username, action, target, target_id, timestamp)
VALUES
    ('admin', 'Thêm', 'Sinh viên', 1, '2023-09-01 08:00:00'),
    ('admin2', 'Sửa', 'Giảng viên', 2, '2023-11-05 10:30:00'),
    ('admin2', 'Xóa', 'Lớp học', 3, '2024-04-20 10:00:00'),
    ('admin', 'Xác nhận', 'Thanh toán', 1, '2023-09-02 10:00:00'),
    ('admin1', 'Xác nhận', 'Thanh toán', 4, '2023-09-06 14:30:00'), 
    ('admin2', 'Xác nhận', 'Thanh toán', 6, '2023-10-02 09:15:00');

-- Insert payment data 
INSERT INTO payment (student_id, payment_date, amount, description, status, approved_by, approval_date)
VALUES 
    ('200001', '2023-09-01', 15000000.00, 'Học phí kỳ 1/2023', 'Xác nhận', 'admin', '2023-09-02 10:00:00'),
    ('200001', '2024-01-15', 2000000.00, 'Phí ký túc xá kỳ 1/2024', 'Đang chờ', NULL, NULL),
    ('210001', '2024-09-01', 18000000.00, 'Học phí kỳ 1/2024', 'Xác nhận', 'admin', '2024-09-02 08:30:00'),
    ('210001', '2024-10-05', 2500000.00, 'Phí ký túc xá kỳ 1/2024', 'Đang chờ', NULL, NULL),
    ('220004', '2025-09-15', 11000000.00, 'Học phí kỳ 1/2025', 'Xác nhận', 'admin2', '2025-09-16 12:30:00'),
    ('220004', '2025-10-20', 2500000.00, 'Phí ký túc xá kỳ 1/2025', 'Đang chờ', NULL, NULL),
    ('220004', '2025-12-01', 200000.00, 'Phí tham gia hội thảo CNTT', 'Hết hạn', NULL, NULL);

-- Insert schedule data
INSERT INTO schedule (class_code, lecturer_id, classroom, start_period, end_period, weekday, start_date, academic_year, semester) 
VALUES
    ('00001', '140001', 'A1-101', 1, 3, 'Thứ hai', '2023-09-04', '2023-2024', 1),
    ('00002', '140002', 'A2-202', 4, 6, 'Thứ ba', '2024-01-10', '2023-2024', 2),
    ('00003', '140001', 'A1-103', 7, 9, 'Thứ tư', '2024-09-02', '2024-2025', 1),
    ('00004', '140003', 'A3-301', 1, 3, 'Thứ năm', '2023-09-07', '2023-2024', 1),
    ('00005', '140004', 'A4-401', 4, 6, 'Thứ sáu', '2024-01-12', '2023-2024', 2);
