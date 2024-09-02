-- 제일 비싼 식품의 식품ID, 식품이름, 식품코드, 식품분류, 식품가격을 조회
SELECT *
FROM FOOD_PRODUCT
ORDER BY PRICE DESC
LIMIT 1;