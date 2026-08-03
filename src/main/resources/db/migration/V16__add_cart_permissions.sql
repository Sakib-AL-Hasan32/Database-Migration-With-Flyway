-- ===========================================
-- ADD CART PERMISSIONS
-- ===========================================

-- Insert permissions
INSERT INTO permissions (name)
VALUES
    ('CREATE_CART_ITEM'),
    ('UPDATE_CART_ITEM'),
    ('VIEW_CART_ITEM'),
    ('DELETE_CART_ITEM');


-- Assign permissions to CUSTOMER role
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r
JOIN permissions p
WHERE r.name = 'CUSTOMER'
AND p.name IN (
            'CREATE_CART_ITEM',
            'UPDATE_CART_ITEM',
            'VIEW_CART_ITEM',
            'DELETE_CART_ITEM'
);