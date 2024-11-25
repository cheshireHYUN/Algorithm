-- 개발자 테이블에서 FE 스킬을 가진 개발자 정보(ID, 이메일, 이름, 성), ID기준 오름차순 정렬
-- conv(숫자,원래진부,바꿀진법)으로 이진수로 바꾼 후 비트연산.. 앤드연산으로 1이 나오느냐?
-- 다르게 생각해보면, 개발자가 가진게 10010일때, 10스킬의 경우를 가지는지 확인하려면 A&B=B면 된다!!
-- A&B=A는 안됨ㅇㅇ 10을 기준으로하면 true지만 10010을 기준으로하면 false니까
select distinct a.id, a.email, a.first_name, a.last_name
from developers a
left outer join skillcodes b 
on a.skill_code & b.code = b.code
where b.category = "Front End"
order by a.id