class DummyClass_179420 {
    @Test
    public void testCamelhumpAndUppercase(){
        EntityHelper.initEntityNameMap(UserCamelhumpAndUppercase.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserCamelhumpAndUppercase.class);
        Assert.assertNotNull(entityTable);
        Assert.assertEquals("USER_CAMELHUMP_AND_UPPERCASE", entityTable.getName());

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("USER_NAME", column.getColumn());
            Assert.assertEquals("userName", column.getProperty());

            Assert.assertEquals("USER_NAME = #{userName}", column.getColumnEqualsHolder());
            Assert.assertEquals("USER_NAME = #{record.userName}", column.getColumnEqualsHolder("record"));
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
        Assert.assertEquals("USER_NAME", resultMapping.getColumn());
        Assert.assertEquals("userName", resultMapping.getProperty());
        Assert.assertNull(resultMapping.getJdbcType());
        Assert.assertEquals(StringTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}