class DummyClass_159667 {
    @Test
    public void dataTypeParamsTest() throws Exception {
        assumeNotNull(this.getDatabase());
        clearDatabase();

        Liquibase liquibase = createLiquibase("changelogs/mssql/issues/data.type.params.xml");
        liquibase.update((String) null);

        DatabaseSnapshot snapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(CatalogAndSchema.DEFAULT, this.getDatabase(), new SnapshotControl(getDatabase()));

        for (Table table : snapshot.get(Table.class)) {
            if (getDatabase().isLiquibaseObject(table)) {
                continue;
            }
            for (Column column : table.getColumns()) {
                String expectedType = column.getName().split("_")[0];

                String foundTypeDefinition = DataTypeFactory.getInstance().from(column.getType(), new MSSQLDatabase()).toDatabaseDataType(getDatabase()).toString();
                assertFalse("Parameter found in " + table.getName() + "." + column.getName(), foundTypeDefinition.contains("("));
            }
        }
    }

}