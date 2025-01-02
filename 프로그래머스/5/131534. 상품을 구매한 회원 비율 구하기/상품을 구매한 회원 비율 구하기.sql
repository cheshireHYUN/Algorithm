-- 쇼핑몰 가입한 회원정보 USER_INFO (회원ID, 성별, 나이, 가입일)
-- 온라인 상품 판매정보 ONLINE_SALE (온라인판매ID, 회원ID, 상품ID, 판매량, 판매일)
-- 동일한 날짜, 회원ID, 상품ID 조합에 대해서는 하나의 판매데이터만 존재(즉, 같은날 한 회원의 같은상품 주문은 단 하나)

-- 2021년에 가입한 전체 회원중 상품을 구매한 회원수와 상품을 구매한 회원비율을 년,월별로 출력하는 SQL 작성
-- 비율 = 2021가입한 회원중 상품 구매한 회원수 / 2021 가입한 전체 회원수
-- 상품을 구매한 회원 비율은 소수점 두번째 자리에서 반올림
-- 전체결과는 연을 기준으로 오름차순정렬, 그리고 월을 기준으로 오름차순 정렬

-- (1)일단 2021년에 가입한 회원들을 select하고,
-- (2)그다음 해당 회원들의 판매데이터를 select한 뒤,
-- (3)연/월별로 그룹화해서 정렬한다...

WITH SELECTED_USER AS (
        SELECT *
        FROM USER_INFO
        WHERE YEAR(JOINED) = 2021),
    SELECTED_ORDER AS (
        SELECT B.ONLINE_SALE_ID, A.USER_ID, B.PRODUCT_ID, B.SALES_AMOUNT, B.SALES_DATE
        FROM SELECTED_USER A
        INNER JOIN ONLINE_SALE B ON A.USER_ID = B.USER_ID)


-- 158명이 2021년에 가입함
SELECT YEAR(A.SALES_DATE) AS YEAR, MONTH(A.SALES_DATE) AS MONTH, COUNT(DISTINCT A.USER_ID) AS PURCHASED_USERS, ROUND((COUNT(DISTINCT A.USER_ID)/(SELECT COUNT(*) FROM SELECTED_USER)),1) AS PURCHASED_RATIO
FROM SELECTED_ORDER A, SELECTED_USER B
GROUP BY YEAR(SALES_DATE), MONTH(SALES_DATE)
ORDER BY YEAR, MONTH


