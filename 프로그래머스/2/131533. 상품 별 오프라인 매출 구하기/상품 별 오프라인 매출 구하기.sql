
-- 상품ID, 상품CODE별 오프라인 판매량의 합계를 출력한다.
select p.product_code as PRODUCT_CODE, sum(o.sales_amount)*p.price as SALES
from product p
inner join offline_sale o on p.product_id = o.product_id
group by p.product_code
order by SALES desc, PRODUCT_CODE