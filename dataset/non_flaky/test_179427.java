class DummyClass_179427 {
    @Test
    public void testJdbcTypeBlob(){
        EntityHelper.initEntityNameMap(UserJdbcTypeBlob.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserJdbcTypeBlob.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("name", column.getColumn());
            Assert.assertEquals("name", column.getProperty());

            Assert.assertEquals("name = #{name, jdbcType=BLOB}", column.getColumnEqualsHolder());
            Assert.assertEquals("name = #{record.name, jdbcType=BLOB}", column.getColumnEqualsHolder("record"));
            Assert.assertEquals("#{name, jdbcType=BLOB}", column.getColumnHolder());
            Assert.assertEquals("#{record.name, jdbcType=BLOB}", column.getColumnHolder("record"));
            Assert.assertEquals("#{record.name, jdbcType=BLOB}", column.getColumnHolder("record", "suffix"));
            Assert.assertEquals("#{record.namesuffix, jdbcType=BLOB},", column.getColumnHolder("record", "suffix", ","));
            Assert.assertNull(column.getTypeHandler());
        }

        ResultMap resultMap = entityTable.getResultMap(configuration);
        Assert.assertEquals("[NAME]", resultMap.getMappedColumns().toString());

        Assert.assertEquals(1, resultMap.getResultMappings().size());

        ResultMapping resultMapping = resultMap.getResultMappings().get(0);
        Assert.assertEquals("name", resultMapping.getColumn());
        Assert.assertEquals("name", resultMapping.getProperty());
        Assert.assertNotNull(resultMapping.getJdbcType());
        Assert.assertEquals(JdbcType.BLOB, resultMapping.getJdbcType());
        Assert.assertEquals(StringTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}