ALTER TABLE public.users ALTER COLUMN password TYPE varchar(256) USING password::varchar(256);