--------------------------------
----------------- MEMBER
--------------------------------
-- 1. 회원가입
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MGENDER, MBIRTH)
    VALUES ('AAA', '1', '홍', 'H@H.COM', '남', '1995-12-12');
    
-- 2. ID중복체크
SELECT * FROM MEMBER WHERE MID = 'AAA';

-- 3. EMAIL중복체크
SELECT * FROM MEMBER WHERE MEMAIL = 'H@H.COM';

-- 4. 로그인 체크
SELECT * FROM MEMBER WHERE MID = 'AAA' AND MPW = '1';

-- 5. ID로 회원정보 가져오기
SELECT * FROM MEMBER WHERE MID = 'AAA';
SELECT * FROM MEMBER;

-- 6. 회원정보 수정하기
UPDATE MEMBER SET MPW = '111',
                  MEMAIL = 'A@A.COM',
                  MTEL = '010-1111-1111',
                  MGENDER = '남',
                  MBIRTH = '1995-12-20'
    WHERE MID = 'AAA';
    
-- 7-1. 관리자모드에서 회원리스트 출력 - 페이징
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY MRDATE DESC) A)
    WHERE RN BETWEEN 2 AND 3;
    
-- 7-2. 페이징시 필요한 등록된 회원수
SELECT COUNT(*) FROM MEMBER;

-- 8. 회원 탈퇴
DELETE FROM MEMBER WHERE MID = 'DDD';


-- * 내가 쓴 글 보기 (자유게시판) - 페이징 // 보류
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM FREEBOARD WHERE MID = 'BBB' ORDER BY FRDATE DESC)A)
    WHERE RN BETWEEN 1 AND 2;


-- * 페이징시 필요한 등록한 글 갯수 (자유게시판) // 보류
SELECT COUNT(*) FROM FREEBOARD WHERE MID = 'BBB';

-- *. 내가 등록한 맛집 보기 - 페이징 // 보류
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM RESTAURANT WHERE MID = 'BBB' ORDER BY RRDATE DESC)A)
    WHERE RN BETWEEN 1 AND 2;

-- *. 페이징시 필요한 등록한 맛집 갯수 // 보류
SELECT COUNT(*) FROM RESTAURANT;

--------------------------------
----------------- RESTAURANT
--------------------------------

-- 1-1. 맛집 리스트 보기 - 페이징 
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM RESTAURANT ORDER BY RRDATE DESC)A)
    WHERE RN BETWEEN 1 AND 10;

-- 1-2. 페이징시 필요한 등록한 맛집 갯수
SELECT COUNT(*) FROM RESTAURANT;

-- 2. 맛집 등록
INSERT INTO RESTAURANT (RNO, MID, CNO, RPLACE, RNAME, RCONTENT, MAINIMG, SUBIMG1, SUBIMG2, RTEL, RMENU, RPRICE)
    VALUES (RES_SEQ.NEXTVAL, 'AAA', 1, '인천', '인천식당', '깔끔해요', 'IMAGE1', NULL, NULL, '02-111-1111', '제육볶음', '12000');

-- 3. 등록한 맛집 수정
UPDATE RESTAURANT SET CNO = 2,
                      RPLACE = '서울',
                      RNAME = '서울식당',
                      RCONTENT = '깨끗해요',
                      MAINIMG = 'IMAGE2',
                      SUBIMG1 = 'IMG1',
                      SUBIMG2 = 'IMG2',
                      RTEL = '02-123-1234',
                      RMENU = '순두부',
                      RPRICE = '10000'
    WHERE RNO = 1;

-- 4. 등록한 맛집 삭제
DELETE FROM RESTAURANT WHERE RNO = '4';

-- 5-1. 평점 등록
INSERT INTO RAVG (ANO, RNO, AVG)
    VALUES (RAVG_SEQ.NEXTVAL, '2', 5);

-- 5-2. 평점 등록 횟수 업데이트
UPDATE RESTAURANT SET AVGHIT = AVGHIT + 1
    WHERE RNO = '1';

-- 6. 평점 출력
SELECT * FROM RAVG;
SELECT * FROM RESTAURANT;

SELECT AVG(AVG) FROM RAVG WHERE RNO = '1';

-- 7. 맛집 클릭시 상세보기 
SELECT * FROM RESTAURANT WHERE RNO = '1';

-- * 평점 4.5 이상 맛집 출력 // 보류

-- 8. 맛집 이름으로 검색 
SELECT * FROM RESTAURANT WHERE RNAME LIKE '%' || '신' || '%';

--------------------------------
----------------- OneReview
--------------------------------

-- 1. 한줄평 등록
INSERT INTO ONEREVIEW (ONO, RNO, MID, OCONTENT)
    VALUES (ONE_SEQ.NEXTVAL, '1', 'CCC', '좋아요');

-- 2. 한줄평 삭제
DELETE FROM ONEREVIEW WHERE ONO = '4';

-- 3. 맛집 한줄평 출력
SELECT * FROM ONEREVIEW WHERE RNO = '1' ORDER BY ORECOMMAND DESC; 

-- 4. 좋아요 클릭
UPDATE ONEREVIEW SET ORECOMMAND = ORECOMMAND + 1
    WHERE ONO = '1';

-- 5. 싫어요 클릭
UPDATE ONEREVIEW SET ORECOMMAND = ORECOMMAND - 1
    WHERE ONO = '1';

SELECT * FROM ONEREVIEW;
--------------------------------
----------------- ADMIN
--------------------------------

-- 1. ADMIN 로그인 체크
SELECT * FROM ADMIN WHERE AID = 'ADMIN' AND APW = '1';

-- 2. 관리자 로그인 후 세션에 넣을 용도 (DTO 가져오기)
SELECT * FROM  ADMIN WHERE AID = 'ADMIN';

-- 3. 관리자 모드에서 관리자 계정 추가
INSERT INTO ADMIN (AID, APW, ANAME)
    VALUES ('ADMIN2', '1', '관리자2');

--------------------------------
----------------- NOTICEBOARD
--------------------------------

-- 1. 공지게시판 글 등록
INSERT INTO NOTICEBOARD (NNO, AID, NTITLE, NCONTENT)
    VALUES (NOTICE_SEQ.NEXTVAL, 'ADMIN', '공지1', '본문');

-- 2-1. 공지게시판 페이징
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM NOTICEBOARD ORDER BY NRDATE DESC)A)
    WHERE RN BETWEEN 1 AND 2;

-- 2-2. 페이징시 필요한 등록한 공지글 수
SELECT COUNT(*) FROM NOTICEBOARD;

-- 3. 조회수 1 올리기 
UPDATE NOTICEBOARD SET NHIT = NHIT + 1
    WHERE NNO = '1';

-- 4. 공지글 수정
UPDATE NOTICEBOARD SET NTITLE = '바뀐 제목',
                       NCONTENT = '바뀐 본문'
    WHERE NNO = '1';

-- 5. 공지글 삭제
DELETE FROM NOTICEBOARD WHERE NNO = '1';

-- 6. 글번호로 공지글 상세보기
SELECT * FROM NOTICEBOARD WHERE NNO = '1';

-- 7. 공지게시판 검색
SELECT * FROM NOTICEBOARD WHERE NTITLE LIKE '%' || '공지' || '%';

--------------------------------
----------------- FREEBOARD
--------------------------------
-- 1. 자유게시판 글 등록(원글)
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FIMAGE2, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'CCC', '포근한 날씨네요', '따뜻해요', NULL, NULL, FREE_SEQ.CURRVAL, 0, 0);

-- 2-1. 자유게시판 페이징
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM FREEBOARD ORDER BY FGROUP DESC, FSTEP)A)
    WHERE RN BETWEEN 1 AND 10;

-- 2-2. 페이징시 필요한 자유게시판 글목록
SELECT COUNT (*) FROM FREEBOARD;

-- 3. 조회수 1 올리기
UPDATE FREEBOARD SET FHIT = FHIT + 1
    WHERE FNO = '1';

-- 4. 자유게시판 글 상세보기
SELECT * FROM FREEBOARD WHERE FNO = '1';

-- 5. 자유게시판 글 수정
UPDATE FREEBOARD SET FTITLE = '수정된 제목',
                     FCONTENT = '수정된 본문',
                     FIMAGE1 = '사진1',
                     FIMAGE2 = '사진2'
    WHERE FNO = '1';

-- 6. 자유게시판 글 삭제
DELETE FROM FREEBOARD WHERE FNO = '6';
    
-- 7-1. 답변글 달기 선행답변글 쓰기 선행 단계(원글의 fgroup과 같고 원글의 fstep보다 크면 fstep을 1 증가)
UPDATE FREEBOARD SET FSTEP = FSTEP + 1
    WHERE FGROUP = 2 AND FSTEP > 0;

-- 7-2. 답변글 등록
INSERT INTO FREEBOARD (FNO, MID, FTITLE, FCONTENT, FIMAGE1, FIMAGE2, FGROUP, FSTEP, FINDENT)
    VALUES (FREE_SEQ.NEXTVAL, 'CCC', '서구청 닭갈비 맛있어요', '한번가보세요', NULL, NULL, 2, 1, 1);

-- 8. 자유게시판 글 검색
SELECT * FROM FREEBOARD WHERE FTITLE LIKE '%' || '맛' || '%';