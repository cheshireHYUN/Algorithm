-- 2022년 10월 16일에 대여중인 자동차는 대여중, 아니면 대여가능을 표시

-- 그때 대여중인 자동차id만 우선 쿼리하고, 두번째쿼리에서 그룹바이한뒤 그 ID들은 대여중, 나머지는 대여가능
WITH SUB_TABLE AS (SELECT CAR_ID
                    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                    WHERE START_DATE <= '2022-10-16' AND END_DATE >= '2022-10-16'),
     CAR_TABLE AS (        
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        GROUP BY CAR_ID
     )

SELECT C.CAR_ID, (
                    CASE
                        WHEN S.CAR_ID IS NULL THEN '대여 가능'
                        ELSE '대여중'
                    END
                ) AS AVAILABILITY
FROM CAR_TABLE C
LEFT OUTER JOIN SUB_TABLE S ON C.CAR_ID = S.CAR_ID
ORDER BY C.CAR_ID DESC;
