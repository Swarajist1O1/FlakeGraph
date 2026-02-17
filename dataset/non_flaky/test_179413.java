class DummyClass_179413 {
    @Test
    public void testSql(){
        EntityHelper.initEntityNameMap(UserSql.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserSql.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("select seq.nextval from dual", column.getGenerator());
            Assert.assertEquals(ORDER.BEFORE, column.getOrder());
            Assert.assertTrue(column.isIdentity());
        }
    }

}