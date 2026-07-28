-- ===========================================
-- Remove the old demo user
-- ===========================================

DELETE FROM refresh_tokens
WHERE user_id = (
    SELECT id FROM users WHERE username = 'user'
);

DELETE FROM user_roles
WHERE user_id = (
    SELECT id FROM users WHERE username = 'user'
);

DELETE FROM users
WHERE username = 'user';



-- ===========================================
-- Remove the old USER role
-- ===========================================

DELETE ur
FROM user_roles ur
JOIN roles r ON ur.role_id = r.id
WHERE r.name = 'USER';

DELETE FROM roles
WHERE name = 'USER';



-- ===========================================
-- Insert the new roles
-- ===========================================

INSERT INTO roles (name)
VALUES
    ('CUSTOMER'),
    ('PRODUCT_MANAGER'),
    ('INVENTORY_MANAGER'),
    ('ORDER_MANAGER');



-- ===========================================
-- Insert demo users
-- ===========================================

INSERT INTO users
(username,email,password,first_name,last_name)
VALUES
    ('customer',
     'customer@example.com',
     '$2a$12$SU.fh.ZGMd2I3r0Dk.EbveGyV3PKe7a3jn9G22zRj.qyp7Ze8Xc6u',
     'Customer',
     'Demo');


INSERT INTO users
(username,email,password,first_name,last_name)
VALUES
    ('product_manager',
     'product_manager@example.com',
     '$2a$12$ZZERHW.Md8omOBOnbzB1auP4e5kM0boKyijbpv83RyceaAnPMPkC.',
     'Product_Manager',
     'Demo');


INSERT INTO users
(username,email,password,first_name,last_name)
VALUES
    ('inventory_manager',
     'inventory_manager@example.com',
     '$2a$12$8ZxqRIBu3zSlQQcj1FzBl.VfcBjTnXPbt1Z2o2izGqNVBbnzXPRGm',
     'Inventory_Manager',
     'Demo');


INSERT INTO users
(username,email,password,first_name,last_name)
VALUES
    ('order_manager',
     'order_manager@example.com',
     '$2a$12$5Yh7TxIxiSs/9RLbV/7V5uT8BUOXq0rVsOXYw2RTBSMa/AKW3Gl4y',
     'Order_Manager',
     'Demo');



-- ===========================================
-- Assign roles
-- ===========================================

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name='CUSTOMER'
WHERE u.username='customer';


INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name='PRODUCT_MANAGER'
WHERE u.username='product_manager';


INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name='INVENTORY_MANAGER'
WHERE u.username='inventory_manager';


INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name='ORDER_MANAGER'
WHERE u.username='order_manager';

