ALTER TABLE public.waiter_orders RENAME COLUMN "user" TO waiter;
ALTER TABLE public.cook_orders RENAME COLUMN "user" TO cook;
ALTER TABLE public.cook_orders RENAME COLUMN isready TO ready;
ALTER TABLE public.cook_orders RENAME COLUMN istake TO take;
ALTER TABLE public.waiter_orders RENAME COLUMN isready TO ready;
ALTER TABLE public.waiter_orders RENAME COLUMN istake TO take;