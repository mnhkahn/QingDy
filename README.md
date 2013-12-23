# Synchronize Github

## Force Synchronize
	git reset --hard HEAD
	git clean -f
	git pull

## Commit 	
	git add -A
	git status
	git commit -a""
	git push origin master

## Import & Export MySql
	create user 'qingdy'@'localhost' identified by '';
	mysqldump -u qingdy -p QingDy > C:/Users/Bryce/Documents/GitHub/QingDy/data/qingdy.sql
	mysql -u qingdy -p QingDy < C:/Users/Bryce/Documents/GitHub/QingDy/data/qingdy.sql

## UnZip File
	unzip upload.zip -d [DIRECTRY]
	
## Passport
	[ip] 121.199.55.106
	[SSH] root Qingdiwang66
	[Aliyun] kangdakd@163.com qingdiwang66
	[mysql] bryce selinai5
	[ftp] ftp Qingdiwang66