-- 수원으 연도별 평균 미세먼지 오염도와 평균 초미세먼지 오염도 (둘다 소수 셋째자리에서 반올림), 연도 오름차순

SELECT YEAR(YM) AS YEAR, ROUND(AVG(PM_VAL1), 2) AS PM10, ROUND(AVG(PM_VAL2),2) AS 'PM2.5'
FROM AIR_POLLUTION
WHERE LOCATION2 = '수원'
GROUP BY YEAR(YM)
ORDER BY YEAR ASC;