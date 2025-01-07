-- USED_GOODS_BOARD(게시글ID, 작성자ID, 게시글제목, 게시글내용, 가격, 작성일, 거래상태, 조회수)
-- USED_GOODS_FILE(파일ID, 파일확장자, 파일이름, 게시글ID)
-- 조회수가 가장 높은 중고거래 게시물에 대한 파일 경로를 조회
-- 첨부파일 경로는 FILE ID를 기준으로 내림차순

WITH MAX_BOARD AS (
    SELECT *
    FROM USED_GOODS_BOARD
    ORDER BY VIEWS DESC
    LIMIT 1
)

SELECT CONCAT('/home/grep/src/',B.BOARD_ID,'/',B.FILE_ID,B.FILE_NAME,B.FILE_EXT) AS FILE_PATH
FROM MAX_BOARD A
INNER JOIN USED_GOODS_FILE B ON A.BOARD_ID = B.BOARD_ID
ORDER BY B.FILE_ID DESC;