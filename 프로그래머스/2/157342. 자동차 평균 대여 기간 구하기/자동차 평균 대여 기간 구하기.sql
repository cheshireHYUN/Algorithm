-- 평균 대여기간이 7일 이상인 자동차ID와 평균대여기간을 출력하라.
-- 대여기간은 소수점 두번째자리에서 반올림, 평균대여 기간 내림차순 자동차ID 내림차순
WITH DIFF_TABLE AS (
    SELECT CAR_ID, DATEDIFF(END_DATE,START_DATE) AS DIFF
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    ),
    CAR_DIFF AS (
    SELECT CAR_ID, ROUND(SUM(DIFF)/COUNT(CAR_ID),1)+1 AS AVERAGE_DURATION
    FROM DIFF_TABLE
    GROUP BY CAR_ID
    )

SELECT *
FROM CAR_DIFF
WHERE AVERAGE_DURATION>=7
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC