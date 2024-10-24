-- Database: ecommerce
-- DROP DATABASE IF EXISTS ecommerce;
CREATE DATABASE ecommerce
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.auth_user
-- DROP TABLE IF EXISTS public.auth_user;
CREATE TABLE IF NOT EXISTS public.auth_user
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    roles character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT auth_user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.auth_user
    OWNER to postgres;

-- Table: public.order_item
-- DROP TABLE IF EXISTS public.order_item;
CREATE TABLE IF NOT EXISTS public.order_item
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    price numeric(38,2),
    quantity integer NOT NULL,
    products_id bigint,
    CONSTRAINT order_item_pkey PRIMARY KEY (id),
    CONSTRAINT fkhw82geh9i7yk9bnn4395owgx3 FOREIGN KEY (products_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_item
    OWNER to postgres;

-- Table: public.orders
-- DROP TABLE IF EXISTS public.orders;
CREATE TABLE IF NOT EXISTS public.orders
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    order_date timestamp(6) without time zone,
    status character varying(255) COLLATE pg_catalog."default",
    total_amount numeric(38,2),
    auth_user_id bigint,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT fk8jr9lj62fptll8kmffm4bro1k FOREIGN KEY (auth_user_id)
        REFERENCES public.auth_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders
    OWNER to postgres;

-- Table: public.orders_items
-- DROP TABLE IF EXISTS public.orders_items;
CREATE TABLE IF NOT EXISTS public.orders_items
(
    orders_id bigint NOT NULL,
    items_id bigint NOT NULL,
    CONSTRAINT uk7qrg5pfgjon82yhgwfqrdijm5 UNIQUE (items_id),
    CONSTRAINT fkju13hoj4l1nc4nbqbayjx766m FOREIGN KEY (orders_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkl3w3fx5rbjq0tbb2i0xidwabh FOREIGN KEY (items_id)
        REFERENCES public.order_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders_items
    OWNER to postgres;

-- Table: public.payment
-- DROP TABLE IF EXISTS public.payment;
CREATE TABLE IF NOT EXISTS public.payment
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    amount character varying(255) COLLATE pg_catalog."default",
    currency character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT payment_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.payment
    OWNER to postgres;

-- Table: public.products
-- DROP TABLE IF EXISTS public.products;
CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    description character varying(255) COLLATE pg_catalog."default",
    image_url character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    price numeric(38,2),
    CONSTRAINT products_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;

-- Table: public.shopping_cart
-- DROP TABLE IF EXISTS public.shopping_cart;
CREATE TABLE IF NOT EXISTS public.shopping_cart
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    auth_user_id bigint,
    CONSTRAINT shopping_cart_pkey PRIMARY KEY (id),
    CONSTRAINT ukaer8lid6nct420kaqwxngo3ic UNIQUE (auth_user_id),
    CONSTRAINT fk4q9cdkixutu1rqu0ktgjvjnhy FOREIGN KEY (auth_user_id)
        REFERENCES public.auth_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.shopping_cart
    OWNER to postgres;

-- Table: public.shopping_cart_products
-- DROP TABLE IF EXISTS public.shopping_cart_products;
CREATE TABLE IF NOT EXISTS public.shopping_cart_products
(
    shopping_cart_id bigint NOT NULL,
    products integer,
    products_key bigint NOT NULL,
    CONSTRAINT shopping_cart_products_pkey PRIMARY KEY (shopping_cart_id, products_key),
    CONSTRAINT fkb0wl9vd38umuh226jsjqx3grq FOREIGN KEY (shopping_cart_id)
        REFERENCES public.shopping_cart (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.shopping_cart_products
    OWNER to postgres;