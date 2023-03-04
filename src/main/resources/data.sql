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

insert into item (item_id, box_ea_qty, box_barcode, box_depth, box_height, box_weight, box_width,
                  case_ea_qty, case_barcode, case_depth, case_height, case_weight, case_width,
                  description, from_make_day, is_make_day, is_set, item_barcode, item_depth, item_height,
                  item_weight, item_width, location, name, near_exp_day, non_deliver_day, pallet_ea_qty, pallet_barcode, pallet_depth, pallet_height,
                  pallet_weight, pallet_width, safe_stock_qty, sname, status, unit, biz_id, customer_id)
values (1, 1, 'test', 1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'TEST ITEM',1,1,1,1,11,1,1,1,1,1,1,1,1,1);