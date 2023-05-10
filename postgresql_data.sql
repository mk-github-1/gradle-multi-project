--------------------------------------------------

-- Delete
DELETE FROM public.user_role;
DELETE FROM public.login_user;
DELETE FROM public.role;

DELETE FROM public.person;

--------------------------------------------------

-- role --
INSERT INTO public.role(name, created_at, updated_at) VALUES('SYSTEM_ADMINISTRATOR', current_timestamp, current_timestamp);
INSERT INTO public.role(name, created_at, updated_at) VALUES('ADMINISTRATOR', current_timestamp, current_timestamp);
INSERT INTO public.role(name, created_at, updated_at) VALUES('USER', current_timestamp, current_timestamp);

--------------------------------------------------

-- login_user --
-- BCryptハッシュの計算 https://toolbase.cc/text/bcrypt : password 0000 --
INSERT INTO public.login_user(email, name, password, created_at, updated_at) VALUES('system_administrator@example.com', 'system_administrator', '$2a$08$X/YzqqMDYG0FylpR9uVep.3Ds2a3grpE8UPw/vfswWJT5kcLcSDBq', current_timestamp, current_timestamp);
INSERT INTO public.login_user(email, name, password, created_at, updated_at) VALUES('administrator@example.com', 'administrator', '$2a$08$X/YzqqMDYG0FylpR9uVep.3Ds2a3grpE8UPw/vfswWJT5kcLcSDBq', current_timestamp, current_timestamp);
INSERT INTO public.login_user(email, name, password, created_at, updated_at) VALUES('user@example.com', 'user', '$2a$08$X/YzqqMDYG0FylpR9uVep.3Ds2a3grpE8UPw/vfswWJT5kcLcSDBq', current_timestamp, current_timestamp);

--------------------------------------------------

-- user_role （Springではロールに”ROLE_”プレフィックスを設定する必要があります）--
INSERT INTO public.user_role(user_id, role_id, created_at, updated_at) VALUES('system_administrator@example.com', 'ROLE_SYSTEMADMINISTRATOR', current_timestamp, current_timestamp);
INSERT INTO public.user_role(user_id, role_id, created_at, updated_at) VALUES('administrator@example.com', 'ROLE_ADMINISTRATOR', current_timestamp, current_timestamp);
INSERT INTO public.user_role(user_id, role_id, created_at, updated_at) VALUES('user@example.com', 'ROLE_USER', current_timestamp, current_timestamp);

--------------------------------------------------
