-- ===========================================
-- REMOVE CREATE_INVENTORY PERMISSION
-- ===========================================

-- Remove from role_permissions first
DELETE FROM role_permissions
WHERE permission_id = (
    SELECT id
    FROM permissions
    WHERE name = 'CREATE_INVENTORY'
);

-- Remove the permission itself
DELETE FROM permissions
WHERE name = 'CREATE_INVENTORY';