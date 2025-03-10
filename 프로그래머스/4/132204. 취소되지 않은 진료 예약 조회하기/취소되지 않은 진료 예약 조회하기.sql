-- 2022년 4월 13일 취소되지 않은 흉부외과(cs) 진료예약 내역을 조회하라.

WITH SELECTED_AP AS (
    SELECT *
    FROM APPOINTMENT
    WHERE DATE_FORMAT(APNT_YMD, '%Y-%m-%d')='2022-04-13' AND APNT_CNCL_YMD IS NULL AND MCDP_CD='CS'
)

SELECT SA.APNT_NO, P.PT_NAME, P.PT_NO, SA.MCDP_CD, D.DR_NAME, SA.APNT_YMD
FROM SELECTED_AP SA
JOIN PATIENT P ON P.PT_NO = SA.PT_NO
JOIN DOCTOR D ON D.DR_ID = SA.MDDR_ID
ORDER BY SA.APNT_YMD;