select * from users_db.users;

UPDATE users_db.users 
SET role = 'ADMIN' 
WHERE email = 'juan@correo.com';

SELECT * FROM users_db.students;