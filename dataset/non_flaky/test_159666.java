class DummyClass_159666 {
    @Test
    public void dataTypesTest() throws Exception {
        assumeNotNull(this.getDatabase());
        clearDatabase();

        Liquibase liquibase = createLiquibase("changelogs/mssql/issues/data.types.xml");
        liquibase.update((String) null);

        DatabaseSnapshot snapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(CatalogAndSchema.DEFAULT, this.getDatabase(), new SnapshotControl(getDatabase()));

        for (Table table : snapshot.get(Table.class)) {
            if (getDatabase().isLiquibaseObject(table)) {
                continue;
            }
            for (Column column : table.getColumns()) {
                String expectedType = column.getName().split("_")[0];

                switch(expectedType.toUpperCase()) {
                    // See https://docs.microsoft.com/en-us/sql/t-sql/data-types/ntext-text-and-image-transact-sql
                    // Types text, ntext and image are deprecated and should be translated into
                    // varchar(max), nvarchar(max) and varbinary(max).
                    case "TEXT":
                        expectedType="varchar";
                        break;
                    case "NTEXT":
                        expectedType="nvarchar";
                        break;
                    case "IMAGE":
                        expectedType="varbinary";
                        break;
                    default:
                        // nothing to do
                }

                String foundTypeDefinition = DataTypeFactory.getInstance().from(column.getType(), new MSSQLDatabase()).toDatabaseDataType(getDatabase()).toString();
                // [varbinary] -> varbinary
                foundTypeDefinition = foundTypeDefinition.replaceFirst("^\\[(.*?)\\]", "$1");
                String foundType = foundTypeDefinition.replaceFirst("\\(.*", "").trim();

                assertEquals("Wrong data type for " + table.getName() + "." + column.getName(),
                    expectedType.toLowerCase(),
                    foundType.toLowerCase()
                );

                if ("varbinary".equalsIgnoreCase(expectedType)) {
                    if (column.getName().endsWith("_MAX")) {
                        assertEquals("VARBINARY(MAX)", foundTypeDefinition.toUpperCase());
                    } else {
                        assertEquals("VARBINARY(1)", foundTypeDefinition.toUpperCase());
                    }
                }
            }
        }
    }

}