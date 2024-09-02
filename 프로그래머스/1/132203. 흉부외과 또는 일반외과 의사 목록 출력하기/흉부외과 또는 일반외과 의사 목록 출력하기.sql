-- MCDP_CD가 CS거나 GS인 의사이름, 의사ID, 진료과, 고용일자를 조회
-- 고용일자를 내림차순 정렬, 같다면 이름기준 오름차순정렬
SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD,'%Y-%m-%d') AS HIRE_YMD
FROM DOCTOR
WHERE MCDP_CD = 'CS' OR MCDP_CD = 'GS'
ORDER BY HIRE_YMD DESC, DR_NAME ASC