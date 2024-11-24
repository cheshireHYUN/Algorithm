-- 입양을 못 간 동물 중 가장 오래 보호소에 있던 동물 3마리의 이름과 보호시작일을 조회(보호 시작일 순)
SELECT a.name, a.datetime
from animal_ins a
left outer join animal_outs b on a.animal_id = b.animal_id
where  b.animal_id is null
order by a.datetime
limit 3;