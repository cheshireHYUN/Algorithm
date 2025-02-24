-- 종류가 세단인 자동차중 10월에 대여를 시작한 기록이 있는 자동차 ID리스트를 출력
-- ID리스트는 중복x, 자동차ID를 기준으로 내림차순 정렬

-- 세단인 자동차만 SELECT해두고, 대여기록 테이블에서 HAVING조건절 걸고 10월대여 기록만 DISTINCT로 출력

WITH SEDAN AS (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE CAR_TYPE = '세단'
)

SELECT DISTINCT CAR_ID
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID IN (SELECT CAR_ID 
                 FROM SEDAN)
AND MONTH(START_DATE) = 10
ORDER BY CAR_ID DESC;