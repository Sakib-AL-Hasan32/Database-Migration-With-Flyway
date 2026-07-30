UPDATE categories
SET created_by = (
    SELECT id
    FROM users
    WHERE username = 'admin'
),
    updated_by = (
        SELECT id
        FROM users
        WHERE username = 'admin'
    )
WHERE created_by IS NULL;