-- ===========================================
-- ADD CANCEL_ORDER PERMISSION
-- ===========================================

INSERT INTO permissions (name)
SELECT 'CANCEL_ORDER'
WHERE NOT EXISTS (
    SELECT 1
    FROM permissions
    WHERE name = 'CANCEL_ORDER'
);

-- ===========================================
-- ASSIGN CANCEL_ORDER TO CUSTOMER ROLE
-- ===========================================

INSERT INTO role_permissions (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM roles r
         JOIN permissions p
              ON p.name = 'CANCEL_ORDER'
WHERE r.name = 'CUSTOMER'
  AND NOT EXISTS (
    SELECT 1
    FROM role_permissions rp
    WHERE rp.role_id = r.id
      AND rp.permission_id = p.id
);