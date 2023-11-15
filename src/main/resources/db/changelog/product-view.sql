DROP VIEW IF EXISTS pgsetting."product_view" CASCADE;
CREATE OR REPLACE VIEW pgsetting."product_view"
    AS

-- SELECT p.id,
--        p.name,
--        p.price * e.value price,
--        e.date,
--        e.to_currency_id currency_id
-- FROM pgsetting.product p
-- LEFT JOIN currency_exchange e on p.currency_id = e.from_currency_id and
--                                    e.to_currency_id = 2 and
--                                    e.date = '2023-10-27';

SELECT p.id,
       p.name,
       p.price * e.value price,
       e.date,
       e.to_currency_id currency_id
FROM pgsetting.product p
LEFT JOIN currency_exchange e on p.currency_id = e.from_currency_id and
                                 e.to_currency_id = current_setting('pgsetting.CurrencyId')::int and
                                 e.date = current_setting('pgsetting.CurrencyDate')::date;
