#select * from QingDyDB.qd_member where username = 'bryce';
#insert into QingDyDB.qd_member (username, password, groupid, signature, introduce, regdate) values ('test1', MD5('qwerty'), 1, 'test', 'test', '2012-08-22 11:37:38');
#update QingDyDB.qd_member set password=MD5('qwerty'), groupid=1, gender=0, icon='/avatar/avatar.png', phone='13452172009', qq='360924857', msn='mnhkahn@yahoo.com.cn', site='http://www.cyeam.com', email='lichao0407@gmail.com', signature='Yeah', introduce='Hello', location=110100, birthday='2012-08-22 11:37:38' WHERE username='test';
#select uid from QingDyDB.qd_member where username='test';
#select * from QingDyDB.qd_member left outer join QingDyDB.qd_answer on QingDyDB.qd_member.uid=QingDyDB.qd_answer.uid left outer join QingDyDB.qd_mall on QingDyDB.qd_member.uid=QingDyDB.qd_mall.uid where username='bryce';
#select name from QingDyDB.qd_mall left outer join QingDyDB.areas on QingDyDB.qd_mall.clientlocation=QingDyDB.areas.areaid;
#select * from QingDyDB.areas where parentid=0 group by areaid;
#select * from QingDyDB.areas where parentid=(select areaid from QingDyDB.areas where areaid=110000) group by vieworder;
#select * from QingDyDB.areas where parentid=(select areaid from QingDyDB.areas where areaid=110100) group by vieworder
#select * from QingDyDB.clients;
#select * from QingDyDB.lendtype;
#select * from QingDyDB.newsclasses;
#select * from QingDyDB.pawn;
#select * from QingDyDB.questionclasses;
#select * from QingDyDB.ratetype;
#select * from QingDyDB.repaymethod;
#select * from QingDyDB.speciality;
#select * from QingDyDB.usersofloan;
#SELECT * FROM QingDyDB.qd_mall where (cname like '%%' || introduce like '%%') && verify=1 order by r_num desc limit 0, 10;
#INSERT INTO `QingDyDB`.`qd_message`(`mid`,`fromuid`,`touid`,`title`,`message`,`postdate`,`isreaded`)VALUES(0,65,1,"hello",'你好','2012-08-27 16:07:39',0);
#delete from QingDyDB.qd_message where mid=0;
#TRUNCATE TABLE QingDyDB.qd_message;
#select * from QingDyDB.qd_transaction inner join QingDyDB.qd_member on QingDyDB.qd_transaction.lenderid=QingDyDB.qd_member.uid where username='bryce' limit 0,2;
#select * from QingDyDB.qd_blog where bid=2;
#SELECT * FROM QingDyDB.qd_mall left outer join QingDyDB.qd_member on QingDyDB.qd_mall.uid=QingDyDB.qd_member.uid left outer join QingDyDB.areas on QingDyDB.qd_mall.clientlocation=QingDyDB.areas.areaid left outer join QingDyDB.usersofloan on QingDyDB.qd_mall.usesofloanid=QingDyDB.usersofloan.uolid left outer join QingDyDB.speciality on QingDyDB.qd_mall.specialityid=QingDyDB.speciality.speid left outer join QingDyDB.clients on QingDyDB.qd_mall.lendtypeid=QingDyDB.clients.ctid left outer join QingDyDB.lendtype on QingDyDB.qd_mall.lendtypeid=QingDyDB.lendtype.ltid left outer join QingDyDB.lendedyears on QingDyDB.qd_mall.lendedyearsid=QingDyDB.lendedyears.lid  where mid like '%1%' order by mid desc limit 0,15
#SELECT * FROM QingDyDB.qd_mall left outer join QingDyDB.qd_member on QingDyDB.qd_mall.uid=QingDyDB.qd_member.uid left outer join QingDyDB.areas on QingDyDB.qd_mall.clientlocation=QingDyDB.areas.areaid left outer join QingDyDB.usersofloan on QingDyDB.qd_mall.usesofloanid=QingDyDB.usersofloan.uolid left outer join QingDyDB.speciality on QingDyDB.qd_mall.specialityid=QingDyDB.speciality.speid left outer join QingDyDB.clients on QingDyDB.qd_mall.lendtypeid=QingDyDB.clients.ctid left outer join QingDyDB.lendtype on QingDyDB.qd_mall.lendtypeid=QingDyDB.lendtype.ltid left outer join QingDyDB.lendedyears on QingDyDB.qd_mall.lendedyearsid=QingDyDB.lendedyears.lid where true and mid = 1
#SELECT * FROM QingDyDB.qd_product join QingDyDB.qd_mall on QingDyDB.qd_product.mid=QingDyDB.qd_mall.mid inner join QingDyDB.qd_member on QingDyDB.qd_product.uid=QingDyDB.qd_member.uid left outer join QingDyDB.areas on QingDyDB.qd_product.clientlocation=QingDyDB.areas.areaid left outer join QingDyDB.usersofloan on QingDyDB.qd_product.usesofloanid=QingDyDB.usersofloan.uolid left outer join QingDyDB.clients on QingDyDB.qd_product.clientid=QingDyDB.clients.ctid left outer join QingDyDB.ratetype on QingDyDB.qd_product.ratetypeid=QingDyDB.ratetype.rid left outer join QingDyDB.repaymethod on QingDyDB.qd_product.repaymentmethodid=QingDyDB.repaymethod.rid left outer join QingDyDB.speciality on QingDyDB.qd_product.ptype=QingDyDB.speciality.speid;
#select * from QingDyDB.qd_loan left outer join QingDyDB.qd_member on QingDyDB.qd_loan.uid=QingDyDB.qd_member.uid left outer join QingDyDB.usersofloan on QingDyDB.qd_loan.usesofloanid=QingDyDB.usersofloan.uolid left outer join QingDyDB.lendtype on QingDyDB.qd_loan.lendtypeid=QingDyDB.lendtype.ltid left outer join QingDyDB.pawn on QingDyDB.qd_loan.pawnid=QingDyDB.pawn.pid left outer join QingDyDB.areas on QingDyDB.qd_loan.location=QingDyDB.areas.areaid
#select * from QingDyDB.qd_blog left outer join QingDyDB.qd_member on QingDyDB.qd_blog.uid=QingDyDB.qd_member.uid left outer join QingDyDB.questionclasses on QingDyDB.questionclasses.classid=QingDyDB.qd_blog.classes
select * from QingDyDB.qd_transaction left outer join QingDyDB.qd_member on QingDyDB.qd_transaction.loanerid=QingDyDB.qd_member.uid
#select * from QingDyDB.qd_transaction left outer join QingDyDB.qd_member on QingDyDB.qd_transaction.lenderid=QingDyDB.qd_member.uid