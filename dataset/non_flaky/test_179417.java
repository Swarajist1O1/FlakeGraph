class DummyClass_179417 {
    @Test(expected = VersionException.class)
    public void testVersionError(){
        EntityHelper.initEntityNameMap(UserVersionError.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserVersionError.class);
        Assert.assertNotNull(entityTable);
        SqlHelper.wherePKColumns(UserVersionError.class, true);
    }

}