SELECT lender as user, postDate as time, 1 as type FROM qingdy.transaction 
UNION
SELECT poster as user, postDate as time, 2 as type FROM qingdy.answer order by time desc;
