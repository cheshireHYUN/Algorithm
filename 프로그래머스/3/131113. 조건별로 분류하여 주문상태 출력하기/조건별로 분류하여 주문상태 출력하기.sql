-- 2022년 5월 1일 기준으로 출고여부 조회
-- 출고여부는 위날짜까지 출과완료로 이후날짜는 출고대기로 미정이면 출고미정
-- 주문ID를 기준으로 오름차순 정렬
SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, '%Y-%m-%d') AS OUT_DATE,
        (CASE
           WHEN OUT_DATE <= '2022-05-01' THEN '출고완료'
            WHEN OUT_DATE > '2022-05-01' THEN '출고대기'
            ELSE '출고미정'
        END) AS 출고여부
FROM FOOD_ORDER
ORDER BY ORDER_ID;