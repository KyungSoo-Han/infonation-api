INSERT INTO infonation.biz
(create_id, created_datetime, modified_datetime, modified_id, zip_addr1, zip_addr2, zip_no, biz_item, biz_no, biz_type, eng_name, name, owner_name)
VALUES(NULL, NULL, NULL, NULL, '경기도 수원시 장안구', '화산로', '263', '물류', '910716', '물류', 'hanks', '행크스', '한경수');


INSERT INTO infonation.center
(create_id, created_datetime, modified_datetime, modified_id, zip_addr1, zip_addr2, zip_no, name, biz_id)
VALUES(NULL, NULL, NULL, NULL, '경기도 수원시', '장안구 화산로', '263', '수원센터', 1);

INSERT INTO infonation.center
(create_id, created_datetime, modified_datetime, modified_id, zip_addr1, zip_addr2, zip_no, name, biz_id)
VALUES(NULL, NULL, NULL, NULL, '경기도 이천시', '별동별', '1442', '이천센터', 1);

INSERT INTO infonation.customer
(create_id, created_datetime, modified_datetime, modified_id, zip_addr1, zip_addr2, zip_no, biz_item, biz_no, biz_type, email, eng_name, fax_no, homepage, name, owner_name, status, tel_no, biz_id)
VALUES(NULL, NULL, NULL, NULL, '서울시', '광진구 자양동','5804', '반도체', '580405', '제조', 'hanks716@naver.com', 'jejo', '99085-111', 'https://hankyungsoo.oopy.io', '경남아파', '한길봉', 1, '457505', 1);

INSERT INTO infonation.destination
(create_id, created_datetime, modified_datetime, modified_id, zip_addr1, zip_addr2, zip_no, biz_item, biz_no, biz_type, email, eng_name, fax_no, homepage, name, owner_name, status, tel_no, biz_id, customer_id)
VALUES(NULL, NULL, NULL, NULL, '수원시', '화산로 33번길','123451', '떡케이크/디저트', '582215', '생산', 'hanks716@cake.com', 'onuelcake', '456789', '@onels', '오늘의떡케이크', '황다', 1, '457505', 1, 1);

INSERT INTO infonation.supplier
(create_id, created_datetime, modified_datetime, modified_id, zip_addr1, zip_addr2, zip_no, biz_item, biz_no, biz_type, email, eng_name, fax_no, homepage, name, owner_name, status, tel_no, biz_id, customer_id)
VALUES(NULL, NULL, NULL, NULL, '서울시', '을지로 394번길','8858', '다양함', '111111', '생산', 'hanks716@samsung.com', 'samsung', '99999999', 'https://samsung.oopy.io', '삼성', '이건희', 1, '457505', 1, 1);

