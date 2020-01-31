# spring-bookstore

## Create database

```
CREATE DATABASE IF NOT EXISTS `book_shop_jpa`;
CREATE USER IF NOT EXISTS 'h4rryp0tt3r'@'localhost' IDENTIFIED BY 'Horcrux4life!';
GRANT ALL PRIVILEGES ON book_shop_jpa.* TO 'h4rryp0tt3r'@'localhost';
```

### Creating updated schema.sql / data.sql files

```
mysqldump -u root -p --no-data book_shop_jpa > src/main/resources/schema.sql
mysqldump -u root -p --no-create-info book_shop_jpa > src/main/resources/data.sql

sed -i '1iSET FOREIGN_KEY_CHECKS=0;' src/main/resources/schema.sql
echo "SET FOREIGN_KEY_CHECKS=1;" >> src/main/resources/schema.sql
sed -i '1iSET FOREIGN_KEY_CHECKS=0;' src/main/resources/data.sql
echo "SET FOREIGN_KEY_CHECKS=1;" >> src/main/resources/data.sql
```
