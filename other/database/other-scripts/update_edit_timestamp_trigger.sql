CREATE OR REPLACE FUNCTION update_created_and_edited()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.created IS NULL THEN
        NEW.created := NOW();
    END IF;

    NEW.edited := NOW();

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION apply_update_created_and_edited_trigger_to_all_tables()
RETURNS void AS $$
DECLARE
    table_record record;
    column_exists boolean;
BEGIN
    FOR table_record IN (SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE') LOOP
        SELECT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'public' AND table_name = table_record.table_name AND column_name = 'created')
        INTO column_exists;
        IF column_exists THEN
            EXECUTE format('CREATE TRIGGER trigger_update_created_and_edited BEFORE INSERT OR UPDATE ON %I FOR EACH ROW EXECUTE FUNCTION update_created_and_edited()', table_record.table_name);
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT apply_update_created_and_edited_trigger_to_all_tables();
