class DummyClass_38197 {
    @Test
    public void testDeleteTablesForSweepSchema() {
        Set<TableReference> allTableNames = Sets.newHashSet();
        allTableNames.add(TableReference.createFromFullyQualifiedName("sweep.progress"));
        allTableNames.add(TableReference.createFromFullyQualifiedName("sweep.priority"));

        mockery.checking(new Expectations(){{
            oneOf(kvs).getAllTableNames(); will(returnValue(allTableNames));
            oneOf(kvs).dropTables(allTableNames);
            oneOf(kvs).getAllTableNames();
        }});
        Schemas.deleteTablesAndIndexes(SweepSchema.INSTANCE.getLatestSchema(), kvs);
    }

}