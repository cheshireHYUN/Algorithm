-- 2022년 10월 5일에 등록된 중고거래 게시물의 게시글id, 작성자id, 게시글제목, 가격, 거래상태를 조회하라
SELECT board_id, writer_id, title, price,
        (case
            when status = 'SALE' then '판매중'
            when status = 'RESERVED' then '예약중'
            when status = 'DONE' then '거래완료'
        end) as status
from used_goods_board
where created_date = '2022-10-5'
order by board_id desc