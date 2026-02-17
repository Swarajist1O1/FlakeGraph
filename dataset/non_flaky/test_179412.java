class DummyClass_179412 {
    @Test
    public void testDialect(){
        EntityHelper.initEntityNameMap(UserDialect.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserDialect.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("SELECT LAST_INSERT_ID()", column.getGenerator());
            Assert.assertEquals(ORDER.AFTER, column.getOrder());
            Assert.assertTrue(column.isIdentity());
        }
    }

}