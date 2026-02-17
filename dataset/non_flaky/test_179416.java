class DummyClass_179416 {
    @Test
    public void testVersion(){
        EntityHelper.initEntityNameMap(UserVersion.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserVersion.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertTrue(column.getEntityField().isAnnotationPresent(Version.class));
        }
    }

}