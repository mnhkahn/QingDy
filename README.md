## Synchronize Github

# Force Synchronize
git reset --hard HEAD
git clean -f
git pull
# Commit
git add -A
git status
git commit -a""
git push origin master

# Import & Export MySql
create user 'qingdy'@'localhost' identified by '';
mysqldump -u qingdy -p QingDy > C:/Users/Bryce/Documents/GitHub/QingDy/data/1.sql
mysql -u qingdy -p QingDy < 1.sql

# UnZip File
unzip -d [DIRECTRY]