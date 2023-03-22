---- MEMBER
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MGENDER, MBIRTH)
    VALUES ('AAA', '1', '홍길동', 'H@H.COM', '남', '1995-12-12');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MGENDER, MBIRTH)
    VALUES ('BBB', '1', '김지연', 'K@K.COM', '여', '1997-09-22');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MGENDER, MBIRTH)
    VALUES ('CCC', '1', '최인성', 'C@C.COM', '남', '1991-04-23');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MGENDER, MBIRTH)
    VALUES ('DDD', '1', '조미림', 'J@J.COM', '여', '1989-12-14');
 
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
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'AAA', 5, '서울 송파구', '썬앤문', '맛있고 정성 가득한 샌드위치를 먹을 수 있는 곳, 재료가 실해서 한 끼 식사로도 괜찮으니 주변에 계시는 분들은 꼭 드셔보세요!', '썬앤문.jpg', '070-4300-2338', '단호박 샌드위치', '만원 미만');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'BBB', 3, '부산 동래구', '백객도', '부산에서 손 꼽히는 간짜장 맛집, 하루 4시간만 영업하는 초 인기 노포. 여행객 뿐만 아니라 로컬들도 줄서서 먹는 찐맛집', '백객도.jpg', '051-554-5873', '간짜장', '만원 미만');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'CCC', 1, '서울 중랑구', '용마해장국', ' 신선한 한우 목뼈와 선지 그리고 우거지와 아삭한 콩나물이 어우러진 국밥으로 다진 마늘을 넣은 국물의 깔끔하고 시원한 맛이 좋고 또 소뼈와 선지가 푸짐하게 들어 있어 누구에게나 만족스러운 식사가 가능한 해장국', '용마해장국.jpg',
        '02-2209-5938', '선지소뼈해장국', '만원 미만');
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
    VALUES (RES_SEQ.NEXTVAL, 'CCC', 1, '서울시 강남구', '노란상소갈비', '적당한 마블링으로 촉촉함을 더한 생갈비와 시그니처 메뉴인 정갈비는 육향이 너무 좋네요. 깔끔하고 짜지않은 밑반찬은 덤.', '노란상소갈비.jpg', '02-543-9290', '정갈비', '2만원~3만원');
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'BBB', 1, '서울시 영등포구', '삼거리먼지막순대국', '50년 전통의 순대국 전문점 내장과 머리 고리를 따로 삶아 재배합한 육수로 만든 순대국이 대표메뉴이며 매일 겉절이를 담는다고 합니다', '삼거리먼지막순대국.jpg', '02-848-2469', '순대국', '만원이하');


SELECT R.*, (SELECT AVG(AVG) FROM RAVG) AVG FROM RESTAURANT R;

SELECT * FROM RAVG;
SELECT * FROM RESTAURANT;

SELECT R.*, A.* FROM RAVG A, RESTAURANT R WHERE A.RNO = R.RNO;
---- RAVG
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '1', 4);
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '1', 4.5);
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 5);
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 4.5);
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 4);
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '3', 4.5);

SELECT * FROM RAVG;
    

---- ONEREVIEWBOARD
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 1, 'BBB', '샌드위치 정말 맛있네요 깔끔하고 한끼 식사로 너무 좋았어요');
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 2, 'CCC', '서울에서 출발하여 너무 먼 여정이었지만 음식을 보자마자 행복했습니다 꼭 다시 한번 가보고 싶어요');
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, 3, 'BBB', '이만한 가격대에 이런 해장국을 찾기가 쉽지 않은데 정말 맛있었습니다 ');
    
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
COMMIT;
