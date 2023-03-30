---- MEMBER
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('AAA', '1', '홍길동', 'H@H.COM',  '1995-12-12');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL,  MBIRTH)
    VALUES ('BBB', '1', '김지연', 'K@K.COM', '1997-09-22');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('CCC', '1', '최인성', 'C@C.COM',  '1991-04-23');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('DDD', '1', '조미림', 'J@J.COM', '1989-12-14');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('EEE', '1', '이재호', 'L@L.COM', '1994-01-30');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('FFF', '1', '임빛나', 'I@I.COM', '1992-05-24');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('GGG', '1', '강주호', 'K@K.COM', '2000-06-14');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('HHH', '1', '박진우', 'P@P.COM', '1999-02-01');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('III', '1', '설희연', 'S@S.COM', '1972-08-29');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('JJJ', '1', '정해준', 'J1@J.COM', '1989-11-30');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MBIRTH)
    VALUES ('KKK', '1', '문지수', 'M@M.COM', '2001-11-08');
 
---- ADMIN
INSERT INTO ADMIN (AID, APW, ANAME)
    VALUES ('ADMIN', '1', '관리자');
    
---- NOTICEBOARD
INSERT INTO NOTICEBOARD (NNO, AID, NTITLE, NCONTENT)
    VALUES (NOTICE_SEQ.NEXTVAL, 'ADMIN', 'Must-Eat 사이트 이용규칙', '본문');
INSERT INTO NOTICEBOARD (NNO, AID, NTITLE, NCONTENT)
    VALUES (NOTICE_SEQ.NEXTVAL, 'ADMIN', '공지2', '본문');
    
---- CCODE
INSERT INTO CCODE (CNO, CNAME)
    VALUES (CCODE_SEQ.NEXTVAL, '한식');
INSERT INTO CCODE (CNO, CNAME)
    VALUES (CCODE_SEQ.NEXTVAL, '일식');
INSERT INTO CCODE (CNO, CNAME)
    VALUES (CCODE_SEQ.NEXTVAL, '중식');
INSERT INTO CCODE (CNO, CNAME)
    VALUES (CCODE_SEQ.NEXTVAL, '양식');
INSERT INTO CCODE (CNO, CNAME)
    VALUES (CCODE_SEQ.NEXTVAL, '카페/디저트');
INSERT INTO CCODE (CNO, CNAME)
    VALUES (CCODE_SEQ.NEXTVAL, '기타');

---- RESTAURNT
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE, AVGHIT)
    VALUES (RES_SEQ.NEXTVAL, 'AAA', 5, '서울 송파구', '썬앤문', '맛있고 정성 가득한 샌드위치를 먹을 수 있는 곳, 재료가 실해서 한 끼 식사로도 괜찮으니 주변에 계시는 분들은 꼭 드셔보세요!', '썬앤문.jpg', '070-4300-2338', '단호박 샌드위치', '만원 미만', 2);
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE, AVGHIT)
    VALUES (RES_SEQ.NEXTVAL, 'BBB', 3, '부산 동래구', '백객도', '부산에서 손 꼽히는 간짜장 맛집, 하루 4시간만 영업하는 초 인기 노포. 여행객 뿐만 아니라 로컬들도 줄서서 먹는 찐맛집', '백객도.jpg', '051-554-5873', '간짜장', '만원 미만', 3);
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE, AVGHIT)
    VALUES (RES_SEQ.NEXTVAL, 'CCC', 1, '서울 중랑구', '용마해장국', ' 신선한 한우 목뼈와 선지 그리고 우거지와 아삭한 콩나물이 어우러진 국밥으로 다진 마늘을 넣은 국물의 깔끔하고 시원한 맛이 좋고 또 소뼈와 선지가 푸짐하게 들어 있어 누구에게나 만족스러운 식사가 가능한 해장국', '용마해장국.jpg',
        '02-2209-5938', '선지소뼈해장국', '만원 미만', 1);
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'BBB', 4, '서울 강남구', '레아', '빽빽한 플렌테리어와 부담스럽지 않은 파스타가 인상적', '레아.jpg', '02-579-6369', '봉골레', '2만원~3만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'AAA', 1, '성남 분당구', '윤밀원', '족발, 평양냉면, 양곰탕을 먹어봤는데 족발이 가장 좋았고 평냉과 양곰탕도 수준급이었습니다. 게다가 업장청결도 객장넓이, 개방형 주방 너비 및 청결도, 직원 친절도 모두 좋았고, 질과 양 모두 좋았어요. 추천합니다', '윤밀원.jpg', '032-714-8388', '족발', '2만원~3만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'CCC', 4, '서울 서초구', '릿잇타미', '세련되고 감각적인 인테리어, 엄선된 재료로 만든 특별한 메뉴와 독특한 맥주로 채워진 정통 아메리칸 수제버거 다이닝 해가 지면 감성적인 분위기로 채워지는 새로운 힙 플레이스', '릿잇타미.jpg', '02-525-1976', '햄버거', '만원~2만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'BBB', 2, '서울시 서초구', '스시쇼우', '값싼 가격에 즐길 수 있는 런치 오마카세, 쉐프님도 친절하고 단새우와 불향 나는 참돔이 일품', '스시쇼우.jpg', '02-595-4510', '런치 오마카세', '4만원 이상');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'CCC', 5, '서울시 강남구', '슬로우치즈', '기나긴 웨이팅이었지만 기다린 만큼의 값을 하는 브런치 카페, 치즈를 좋아하는 사람이라면 꼭 한번쯤은 가봐야할 식당', '슬로우치즈.jpg', '02-517-7172', '부라타', '만원~2만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'DDD', 1, '서울시 강남구', '노란상소갈비', '적당한 마블링으로 촉촉함을 더한 생갈비와 시그니처 메뉴인 정갈비는 육향이 너무 좋네요. 깔끔하고 짜지않은 밑반찬은 덤.', '노란상소갈비.jpg', '02-543-9290', '정갈비', '2만원~3만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'BBB', 1, '서울시 영등포구', '삼거리먼지막순대국', '50년 전통의 순대국 전문점 내장과 머리 고리를 따로 삶아 재배합한 육수로 만든 순대국이 대표메뉴이며 매일 겉절이를 담는다고 합니다', '삼거리먼지막순대국.jpg', '02-848-2469', '순대국', '만원이하');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'FFF', 4, '서울시 용산구', '지노스뉴욕피자', '뉴욕 현지 스타일의 정통 뉴욕 피자를 추구하는 곳 정통 뉴욕 스타일 피자의 맛을 내기 위해 엄선한 무표백 밀가루와 캘리포니아산 플럼 토마토를 사용함 직접 만든 수제 도우가 일품', '지노스뉴욕피자.jpg', '02-792-2234', '	
브루클린스 베스트', '만원~2만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'KKK', 6, '서울시 마포구', '코랏', '태국음식을 대중적으로 즐길 수 있도록 코랏 출신의 현지인 쉐프가 직접 끓인 육수와 직접 만든 소스로 정성껏 조리합니다', '코랏.jpg', '0507-1337-1679', '푸팟퐁커리', '만원미만');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'JJJ', 4, '경기도 안성시', '세컨드코너', '안성 중앙시장 청년몰에 위치한 식당, 안성 시민은 물론 한경대 학생들 사이에서도 꽤나 유명한 수제버거 맛집', '세컨드코너.jpg', '031-676-3669', '아메리칸 클래식 버거', '만원미만');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'HHH', 2, '인천시 미추홀구', '탄포포', '인하대에서 먹을 수 있는 돈까스 중 최고의 품질을 자랑하고 있습니다. 샐러드 리필은 필수', '탄포포.jpg', '032-872-1764', '로스까스', '만원이하');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'III', 2, '부산시 수영구', '고옥', '부산 장어덮밥의 원조 맛집, 장어가 정말 부드럽고 하나도 느끼하지 않으며 김와사비와 깻잎과 함께 먹는 방법이 제일 좋음', '고옥.jpg', '051-622-1638', '히츠마부시', '2만원~3만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'EEE', 5, '부산시 수영구', '스크러피', '되게 뜬금없는 곳에 위치한 멋있는 카페! 에그타르트가 꽤 다양하게 준비가 되어 있는데 제일 대표 메뉴인 해바라기 에그타르트와 쏠티드카라멜 휘낭시에는 꼭 추천합니다', '스크러피.jpg', '0507-1389-5438', '에그타르트', '만원미만');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'GGG', 6, '강원도 속초', '매자식당', '베트남음식 많이 안먹어 봤고 쌀국수도 별로 안좋아하는데 여기 볶음밥 진짜 맛있고 쌀국수도 괜찮았어요! 쌀국수 좋어허는 친구는 진짜 맛있다고 하더라고요. 추천합니다', '매자식당.jpg', '010-9193-0807', '팟타이', '만원~2만원');


---- RAVG
INSERT INTO RAVG (ANO, RNO, RATING)
    VALUES (RAVG_SEQ.NEXTVAL, '1', 4);
INSERT INTO RAVG (ANO, RNO, RATING)
    VALUES (RAVG_SEQ.NEXTVAL, '1', 3);
INSERT INTO RAVG (ANO, RNO, RATING)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 5);
INSERT INTO RAVG (ANO, RNO, RATING)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 4);
INSERT INTO RAVG (ANO, RNO, RATING)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 4);
INSERT INTO RAVG (ANO, RNO, RATING)
    VALUES (RAVG_SEQ.NEXTVAL, '3', 4);

SELECT * FROM RAVG;
    
---- ONEREVIEWBOARD
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 1, 'BBB', '샌드위치 정말 맛있네요 깔끔하고 한끼 식사로 너무 좋았어요');
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 2, 'CCC', '서울에서 출발하여 너무 먼 여정이었지만 음식을 보자마자 행복했습니다 꼭 다시 한번 가보고 싶어요');
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 3, 'BBB', '이만한 가격대에 이런 해장국을 찾기가 쉽지 않은데 정말 맛있었습니다 ');
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 3, 'DDD', '국물도 깔끔하고 식당도 깨끗하고 다시 찾고 싶은 맛집입니다');

INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 3, 'AAA', '얼큰한 국물에 밥 말아서 먹으면 술은 안마셨지만 아침 해장메뉴구나 싶다
맵지도 않고 고기도 푹 익어서 부드럽고 같이 나오는 깍두기가 잘 어울린다');
    
---- FREEBOARD
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'AAA', '서울 맛집 추천해주세요', '신촌쪽 맛집 추천 부탁', NULL, FREE_SEQ.CURRVAL, 0, 0);
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'BBB', '인천에 갈만한 곳 있나요?', '추천해주세요', NULL, FREE_SEQ.CURRVAL, 0, 0);
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'CCC', '인천 여행지 추천합니다', '영종도 추천해요', 'IMAGE3', 2, 1, 1);
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'BBB', '추천 감사합니다', '꼭 가볼게요', NULL, 2, 2, 2);
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'BBB', '인천 다녀왔습니다', '좋네요', NULL, FREE_SEQ.CURRVAL, 0, 0);
    
select * from restaurant;
select * from ccode;
SELECT * FROM RAVG;
COMMIT;
