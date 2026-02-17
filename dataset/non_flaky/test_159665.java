class DummyClass_159665 {
    @Test
    public void defaultValuesTests() throws Exception {
        clearDatabase();

        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase("changelogs/mssql/issues/default.values.xml");
        liquibase.update((String) null);

        DatabaseSnapshot snapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(CatalogAndSchema.DEFAULT, this.getDatabase(), new SnapshotControl(getDatabase()));

        for (Table table : snapshot.get(Table.class)) {
            for (Column column : table.getColumns()) {
                if (column.getName().toLowerCase().endsWith("_default")) {
                    Object defaultValue = column.getDefaultValue();
                    assertNotNull("Null default value for " + table.getName() + "." + column.getName(), defaultValue);
                    if (column.getName().toLowerCase().contains("date") || column.getName().toLowerCase().contains("time")) {
                        if (defaultValue instanceof String) {
                            assertTrue(defaultValue.equals("2017-12-09 23:52:39.1234567 +01:00"));
                        } else if (defaultValue instanceof DatabaseFunction) {
                            ((DatabaseFunction) defaultValue).getValue().contains("type datetimeoffset");
                        } else if (defaultValue instanceof Time) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(((Date) defaultValue));
                            assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
                            assertEquals(52, calendar.get(Calendar.MINUTE));
                            assertEquals(39, calendar.get(Calendar.SECOND));
                        } else {
                            assertTrue("Unexpected default type "+defaultValue.getClass().getName()+" for " + table.getName() + "." + column.getName(), defaultValue instanceof Date);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(((Date) defaultValue));
                            assertEquals(9, calendar.get(Calendar.DAY_OF_MONTH));
                            assertEquals(11, calendar.get(Calendar.MONTH));
                            assertEquals(2017, calendar.get(Calendar.YEAR));
                        }
                    } else if (column.getName().toLowerCase().contains("char_")) {
                        assertTrue("Unexpected default type "+defaultValue.getClass().getName()+" for " + table.getName() + "." + column.getName(), defaultValue instanceof String);
                    } else if (column.getName().toLowerCase().contains("binary_")) {
                        assertTrue("Unexpected default type "+defaultValue.getClass().getName()+" for " + table.getName() + "." + column.getName(), defaultValue instanceof DatabaseFunction);
                    } else {
                        assertTrue("Unexpected default type "+defaultValue.getClass().getName()+" for " + table.getName() + "." + column.getName(), defaultValue instanceof Number);
                        assertEquals(1, ((Number) defaultValue).intValue());
                    }
                }
            }
        }
    }

}