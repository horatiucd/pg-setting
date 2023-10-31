DROP VIEW IF EXISTS pgsetting."product_view" CASCADE;
CREATE OR REPLACE VIEW pgsetting."product_view"
    AS
-- SELECT p.id,
--        p.name,
--        p.price,
--        p.currency_id
-- FROM pgsetting.product p;

-- SELECT p.id,
--        p.name,
--        p.price * r.value price,
--        r.date,
--        r.to_currency_id currency_id
-- FROM pgsetting.product p
-- LEFT JOIN currency_conversion r on p.currency_id = r.from_currency_id and
--                                    r.to_currency_id = 2 and
--                                    r.date = '2023-10-27';

SELECT p.id,
       p.name,
       p.price * r.value price,
       r.date,
       r.to_currency_id currency_id
FROM pgsetting.product p
LEFT JOIN currency_conversion r on p.currency_id = r.from_currency_id and
                                   r.to_currency_id = current_setting('pgsetting.CurrencyId')::int and
                                   r.date = current_setting('pgsetting.CurrencyDate')::date;
