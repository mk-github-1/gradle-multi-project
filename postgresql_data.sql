--------------------------------------------------

-- Delete
DELETE FROM public.m_login_user_role CASCADE;
DELETE FROM public.m_login_user CASCADE;
DELETE FROM public.m_role CASCADE;

--------------------------------------------------

-- role --
INSERT INTO public.m_role(role_id, role_name, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('ROLE_SYSTEM_ADMINISTRATOR', 'システム管理者', 1, false, current_timestamp, current_timestamp, 0);
INSERT INTO public.m_role(role_id, role_name, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('ROLE_ADMINISTRATOR', '管理者', 2, false, current_timestamp, current_timestamp, 0);
INSERT INTO public.m_role(role_id, role_name, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('ROLE_GENERAL', '一般', 3, false, current_timestamp, current_timestamp, 0);

--------------------------------------------------

-- login_user --
-- 自習用アプリ用パスワードのハッシュ値 0000 -> $2a$10$XoTNNlYlYHn2vmfMnmJhU.1M78uA6oOTKj4J9iu.nBeVSwUu1YjyS --
INSERT INTO public.m_login_user(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('user1@example.com', '$2a$10$XoTNNlYlYHn2vmfMnmJhU.1M78uA6oOTKj4J9iu.nBeVSwUu1YjyS', false, false, false, true, 1, false, current_timestamp, current_timestamp, 0);
INSERT INTO public.m_login_user(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('user2@example.com', '$2a$10$XoTNNlYlYHn2vmfMnmJhU.1M78uA6oOTKj4J9iu.nBeVSwUu1YjyS', false, false, false, true, 1, false, current_timestamp, current_timestamp, 0);
INSERT INTO public.m_login_user(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('user3@example.com', '$2a$10$XoTNNlYlYHn2vmfMnmJhU.1M78uA6oOTKj4J9iu.nBeVSwUu1YjyS', false, false, false, true, 1, false, current_timestamp, current_timestamp, 0);

--------------------------------------------------

-- login_user_role （Springではロールに”ROLE_”プレフィックスを設定する必要があります）--
INSERT INTO public.m_login_user_role(username, role_id, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('user1@example.com', 'ROLE_SYSTEM_ADMINISTRATOR', 1, false, current_timestamp, current_timestamp, 0);
INSERT INTO public.m_login_user_role(username, role_id, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('user2@example.com', 'ROLE_ADMINISTRATOR', 2, false, current_timestamp, current_timestamp, 0);
INSERT INTO public.m_login_user_role(username, role_id, sort_order, is_deleted, created_at, updated_at, timestamp) VALUES('user3@example.com', 'ROLE_GENERAL', 3, false, current_timestamp, current_timestamp, 0);

--------------------------------------------------
