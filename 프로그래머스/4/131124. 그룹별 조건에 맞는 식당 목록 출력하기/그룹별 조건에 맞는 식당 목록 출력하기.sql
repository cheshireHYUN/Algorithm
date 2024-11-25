-- 리뷰를 가장 많이 작성한 회원들의 리뷰를 조회하는 SQL문을 작성해라.
-- 회원이름, 리뷰텍스트, 리뷰작성일이 출력되도록 하고, 리뷰작성일기준/리뷰텍스트기준으로 오름차순 정렬

with cnt_tbl as (
    select m.member_id, m.member_name, count(r.review_id) as cnt
    from member_profile m
    inner join rest_review r on m.member_id = r.member_id
    group by m.member_id 
)

select c.member_name, r.review_text, date_format(r.review_date, '%Y-%m-%d')
from cnt_tbl c
inner join rest_review r on c.member_id = r.member_id
where c.cnt = (
    select max(cnt) 
    from cnt_tbl)
order by review_date, review_text