-- 자동차 종류가 '트럭'인 자동차의 대여기록에 대해
-- 대여기록별 대여금액을 구해서 대여기록ID와 대여금액 리스트를 출력
-- 대여금액 기준 내림차순, 대여기록id기준 내림차순


SELECT A.HISTORY_ID,
        (CASE
            WHEN B.DISCOUNT_RATE IS NOT NULL THEN TRUNCATE((100-B.DISCOUNT_RATE)*0.01*A.FEE,0)
            ELSE TRUNCATE(A.FEE,0)
        END) AS FEE
FROM (
    SELECT B.HISTORY_ID, A.CAR_TYPE, 1+(DATEDIFF(B.END_DATE, B.START_DATE)) as DATE_DIFF,
        (CASE
            WHEN 1+(DATEDIFF(B.END_DATE, B.START_DATE)) >= 90 THEN '90일 이상'
            WHEN 1+(DATEDIFF(B.END_DATE, B.START_DATE)) >= 30 THEN '30일 이상'
            WHEN 1+(DATEDIFF(B.END_DATE, B.START_DATE)) >= 7 THEN '7일 이상'
            ELSE NULL
        END) AS DURATION_TYPE,
        a.daily_fee,
        (A.DAILY_FEE * (1+(DATEDIFF(B.END_DATE, B.START_DATE)))) AS FEE
    FROM CAR_RENTAL_COMPANY_CAR A
    INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY B ON A.CAR_ID = B.CAR_ID 
    WHERE A.CAR_TYPE = '트럭'
) A
LEFT OUTER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN B ON (A.DURATION_TYPE = B.DURATION_TYPE AND A.CAR_TYPE = B.CAR_TYPE)
ORDER BY FEE DESC, HISTORY_ID DESC
