class DummyClass_179411 {
    @Test
    public void testUseGeneratedKeys(){
        EntityHelper.initEntityNameMap(UserJDBC.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserJDBC.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("JDBC", column.getGenerator());
            Assert.assertTrue(column.isIdentity());
        }
    }

}