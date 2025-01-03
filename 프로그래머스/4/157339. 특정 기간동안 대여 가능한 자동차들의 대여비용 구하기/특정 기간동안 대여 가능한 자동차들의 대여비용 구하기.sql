-- 자동차 정보 : CAR_RENTAL_COMPANY_CAR(자동차ID, 자동차 종류, 일일대여요금(원), 자동차옵션리스트)
-- 자동차 대여기록 정보 : CAR_RENTAL_COMPANY_RENTAL_HISTORY(자동차대여기록ID, 자동차ID, 대여시작일, 대여종료일)
-- 자동차/대여기간 종류별 할인정책 : CAR_RENTAL_COMPANY_DISCOUNT_PLAN(정책ID, 자동차 종류, 대여기간 종류, 할인율)

-- 자동차 종류가 '세단' OR 'SUV'인 자동차중 2022/11/1 ~ 2022/11/30까지 대여 가능하고
-- 30일간의 대여금액이 50만원 이상 200만원 미만인 자동차에 대해
-- 자동차ID, 자동차종류, 대여금액(FEE) 리스트를 출력하는 SQL 작성
-- 결과는 대여금액 내림차순, 자동차종류 기준 오름차순, 자동차ID 내림차순

-- 1. 자동차정보와 대여기록정보를 LEFT OUTER JOIN해서 '세단','SUV'인 차중 기준기간동안 대여되지 않은 차를 찾는다.
--    대여되지 않은 차를 찾기위해 대여된차를 먼저 구해서 사용한다,
-- 2. 해당 차를 30일간 대여할경우의 FEE를 구한뒤 50만원 이상 200만원 미만인 결과만 출력한다
--    이때, 할인률은 DURATION_TYPE이 30일이상인것들중 해당 차의 CAR_TYPE에 맞는 DISCOUNT_RATE를 찾아 계산한다.

WITH CANT_CAR AS (
    SELECT A.CAR_ID
    FROM CAR_RENTAL_COMPANY_CAR A
    INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY B ON A.CAR_ID = B.CAR_ID
    WHERE (A.CAR_TYPE = '세단' OR A.CAR_TYPE = 'SUV')
    AND ((B.START_DATE BETWEEN '2022-11-01' AND '2022-11-30')
        OR (B.END_DATE BETWEEN '2022-11-01' AND '2022-11-30')
        OR (B.START_DATE < '2022-11-01' AND B.END_DATE > '2022-11-30'))
    ORDER BY A.CAR_ID),
    
    CAN_CAR AS (
        SELECT B.CAR_ID, B.CAR_TYPE, B.DAILY_FEE
        FROM CANT_CAR A
        RIGHT OUTER JOIN CAR_RENTAL_COMPANY_CAR B ON A.CAR_ID = B.CAR_ID
        WHERE A.CAR_ID IS NULL
        AND (B.CAR_TYPE = '세단' OR B.CAR_TYPE = 'SUV')
    )
    

SELECT *
FROM (
    SELECT A.CAR_ID, A.CAR_TYPE, TRUNCATE(((A.DAILY_FEE*30)*((100-B.DISCOUNT_RATE)*0.01)),0) AS FEE
    FROM CAN_CAR A
    INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN B ON A.CAR_TYPE = B.CAR_TYPE
    WHERE DURATION_TYPE = '30일 이상'
    ) CALCULATED_CAR
WHERE FEE >= 500000 AND FEE<=2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC
