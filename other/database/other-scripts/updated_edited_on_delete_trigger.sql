CREATE OR REPLACE FUNCTION update_edited_on_delete()
RETURNS TRIGGER AS $$
BEGIN
    NEW.edited := NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION apply_update_edited_on_delete_trigger_to_all_tables()
RETURNS void AS $$
DECLARE
    table_record record;
BEGIN
    FOR table_record IN (SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE') LOOP
        EXECUTE format('CREATE TRIGGER trigger_update_edited_on_delete BEFORE DELETE ON %I FOR EACH ROW EXECUTE FUNCTION update_edited_on_delete()', table_record.table_name);
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT apply_update_edited_on_delete_trigger_to_all_tables();
