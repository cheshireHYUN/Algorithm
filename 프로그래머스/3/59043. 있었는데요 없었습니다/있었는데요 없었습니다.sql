-- 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회(결과는 보호시작일이 빠른 순)
SELECT A.ANIMAL_ID, B.NAME
FROM ANIMAL_INS A
INNER JOIN ANIMAL_OUTS B ON A.ANIMAL_ID = B.ANIMAL_ID
WHERE A.DATETIME > B.DATETIME
ORDER BY A.DATETIME;