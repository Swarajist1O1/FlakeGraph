class DummyClass_179419 {
    @Test
    public void testCamelhump(){
        EntityHelper.initEntityNameMap(UserCamelhump.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserCamelhump.class);
        Assert.assertNotNull(entityTable);
        Assert.assertEquals("user_camelhump", entityTable.getName());

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("user_name", column.getColumn());
            Assert.assertEquals("userName", column.getProperty());

            Assert.assertEquals("user_name = #{userName}", column.getColumnEqualsHolder());
            Assert.assertEquals("user_name = #{record.userName}", column.getColumnEqualsHolder("record"));
            Assert.assertEquals("#{userName}", column.getColumnHolder());
            Assert.assertEquals("#{record.userName}", column.getColumnHolder("record"));
            Assert.assertEquals("#{record.userName}", column.getColumnHolder("record", "suffix"));
            Assert.assertEquals("#{record.userNamesuffix},", column.getColumnHolder("record", "suffix", ","));
            Assert.assertNull(column.getTypeHandler());
        }

        ResultMap resultMap = entityTable.getResultMap(configuration);
        Assert.assertEquals("[USER_NAME]", resultMap.getMappedColumns().toString());

        Assert.assertEquals(1, resultMap.getResultMappings().size());

        ResultMapping resultMapping = resultMap.getResultMappings().get(0);
        Assert.assertEquals("user_name", resultMapping.getColumn());
        Assert.assertEquals("userName", resultMapping.getProperty());
        Assert.assertNull(resultMapping.getJdbcType());
        Assert.assertEquals(StringTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}