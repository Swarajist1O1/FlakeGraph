class DummyClass_179414 {
    @Test
    public void testAll(){
        EntityHelper.initEntityNameMap(UserAll.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserAll.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("JDBC", column.getGenerator());
            Assert.assertTrue(column.isIdentity());
        }
    }

}