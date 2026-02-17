class DummyClass_179422 {
    @Test
    public void testNormal(){
        EntityHelper.initEntityNameMap(UserNormal.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserNormal.class);
        Assert.assertNotNull(entityTable);
        Assert.assertEquals("UserNormal", entityTable.getName());

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("userName", column.getColumn());
            Assert.assertEquals("userName", column.getProperty());

            Assert.assertEquals("userName = #{userName}", column.getColumnEqualsHolder());
            Assert.assertEquals("userName = #{record.userName}", column.getColumnEqualsHolder("record"));
            Assert.assertEquals("#{userName}", column.getColumnHolder());
            Assert.assertEquals("#{record.userName}", column.getColumnHolder("record"));
            Assert.assertEquals("#{record.userName}", column.getColumnHolder("record", "suffix"));
            Assert.assertEquals("#{record.userNamesuffix},", column.getColumnHolder("record", "suffix", ","));
            Assert.assertNull(column.getTypeHandler());
        }

        ResultMap resultMap = entityTable.getResultMap(configuration);
        Assert.assertEquals("[USERNAME]", resultMap.getMappedColumns().toString());

        Assert.assertEquals(1, resultMap.getResultMappings().size());

        ResultMapping resultMapping = resultMap.getResultMappings().get(0);
        Assert.assertEquals("userName", resultMapping.getColumn());
        Assert.assertEquals("userName", resultMapping.getProperty());
        Assert.assertNull(resultMapping.getJdbcType());
        Assert.assertEquals(StringTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}