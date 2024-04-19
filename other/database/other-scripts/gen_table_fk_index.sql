CREATE OR REPLACE FUNCTION create_all_fk_indexes()
RETURNS void AS
$$
DECLARE
    table_record RECORD;
    fk_column_name text;
BEGIN
    FOR table_record IN (SELECT table_name, column_name
                         FROM information_schema.columns
                         WHERE table_schema = 'public'
                           AND column_name LIKE '%_id'
                           AND column_name <> 'id')
    LOOP
        fk_column_name := table_record.column_name;
        EXECUTE format('CREATE INDEX idx_%s_%s ON %s (%s)',
                       table_record.table_name, fk_column_name,
                       table_record.table_name, fk_column_name);
    END LOOP;
END;
$$
LANGUAGE plpgsql;

SELECT create_all_fk_indexes();
