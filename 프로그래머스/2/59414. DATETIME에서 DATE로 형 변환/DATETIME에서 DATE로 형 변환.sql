-- 각 동물의 아이디와 이름, 들어온 날짜를 조회 (결과는 아이디순)
SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') AS "날짜"
FROM ANIMAL_INS
ORDER BY ANIMAL_ID