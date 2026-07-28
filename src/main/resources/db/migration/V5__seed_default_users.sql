-- ============================
-- DEFAULT USERS
-- ============================

INSERT INTO users (
    username,
    email,
    password,
    first_name,
    last_name
)
VALUES
    (
        'admin',
        'admin@example.com',
        '$2a$12$T.d/D1aCIjBfYTv2giQMQumjcMhYnCVIVl4ORWxveqqF7CVjSfB1y',
        'System',
        'Administrator'
    ),
    (
        'user',
        'user@example.com',
        '$2a$12$PKbLPkkqV9p0U/gScOr1k.QI/BpJRtdPQ1.GsVnrAM21JAYfRH4V.',
        'Demo',
        'User'
    );


-- ============================
-- ASSIGN ROLES
-- ============================

INSERT INTO user_roles (user_id, role_id)
VALUES
    (
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM roles WHERE name = 'ADMIN')
    ),
    (
        (SELECT id FROM users WHERE username = 'user'),
        (SELECT id FROM roles WHERE name = 'USER')
    );