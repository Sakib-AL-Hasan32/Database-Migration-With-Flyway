-- ===========================================
-- REMOVE ADJUST_INVENTORY FROM INVENTORY_MANAGER
-- ===========================================

DELETE rp
FROM role_permissions rp
         JOIN roles r ON rp.role_id = r.id
         JOIN permissions p ON rp.permission_id = p.id
WHERE r.name = 'INVENTORY_MANAGER'
  AND p.name = 'ADJUST_INVENTORY';