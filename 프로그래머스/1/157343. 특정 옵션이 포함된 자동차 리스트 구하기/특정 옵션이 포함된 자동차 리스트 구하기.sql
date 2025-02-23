-- '네비게이션' 이 포함된 자동차 리스트를 출력, 자동차ID를 기준으로 내림차순
SELECT *
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%네비게이션%'
ORDER BY CAR_ID DESC