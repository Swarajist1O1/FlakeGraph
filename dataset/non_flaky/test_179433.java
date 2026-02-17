class DummyClass_179433 {
    @Test
    public void testColumn() {
        EntityHelper.initEntityNameMap(User.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(User.class);
        Assert.assertNotNull(entityTable);
        Assert.assertEquals("sys_user", entityTable.getName());
    }

}