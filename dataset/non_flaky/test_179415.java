class DummyClass_179415 {
    @Test
    public void testAll2(){
        EntityHelper.initEntityNameMap(UserAll2.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserAll2.class);
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