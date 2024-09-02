-- 자동차 종류가 SUV인 자동차의 평균 일일대여요금을 출력하라
SELECT round(avg(DAILY_FEE),0) as AVERAGE_FEE
from car_rental_company_car
where car_type = 'SUV'
