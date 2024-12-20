-- 아이스크림 가게 상반기 주문정보(FIRST_HALF) : 출하번호(FK), 아이스크림 맛(PK), 상반기 총 주문량
-- 7월의 아이스크림 주문정보(JULY) : 출하번호(PK), 아이스크림 맛(FK), 7월 총 주문량
-- 7월엔 아이스크림 주문량이 많아 같은 아이스크림에 대해 서로 다른 공장에서 가게로 출하함. 따라서 같은맛이라도
-- 다른 출하번호를 가질 수 있다.

-- 7월 아이스크림의 총 주문량과 상반기 아이스크림 총 주문량을 더한 값이 큰 순서대로 상위 3개 맛을 조회하라.


SELECT A.FLAVOR
FROM FIRST_HALF A
INNER JOIN (
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS SUM_TOTAL_ORDER
    FROM JULY
    GROUP BY FLAVOR
    ) B ON A.FLAVOR = B.FLAVOR
ORDER BY (A.TOTAL_ORDER + B.SUM_TOTAL_ORDER) DESC
LIMIT 3;


