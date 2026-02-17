class DummyClass_179426 {
    @Test
    public void testJdbcTypeVarchar(){
        EntityHelper.initEntityNameMap(UserJdbcTypeVarchar.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserJdbcTypeVarchar.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("name", column.getColumn());
            Assert.assertEquals("name", column.getProperty());

            Assert.assertEquals("name = #{name, jdbcType=VARCHAR}", column.getColumnEqualsHolder());
            Assert.assertEquals("name = #{record.name, jdbcType=VARCHAR}", column.getColumnEqualsHolder("record"));
            Assert.assertEquals("#{name, jdbcType=VARCHAR}", column.getColumnHolder());
            Assert.assertEquals("#{record.name, jdbcType=VARCHAR}", column.getColumnHolder("record"));
            Assert.assertEquals("#{record.name, jdbcType=VARCHAR}", column.getColumnHolder("record", "suffix"));
            Assert.assertEquals("#{record.namesuffix, jdbcType=VARCHAR},", column.getColumnHolder("record", "suffix", ","));
            Assert.assertNull(column.getTypeHandler());
        }

        ResultMap resultMap = entityTable.getResultMap(configuration);
        Assert.assertEquals("[NAME]", resultMap.getMappedColumns().toString());

        Assert.assertEquals(1, resultMap.getResultMappings().size());

        ResultMapping resultMapping = resultMap.getResultMappings().get(0);
        Assert.assertEquals("name", resultMapping.getColumn());
        Assert.assertEquals("name", resultMapping.getProperty());
        Assert.assertNotNull(resultMapping.getJdbcType());
        Assert.assertEquals(JdbcType.VARCHAR, resultMapping.getJdbcType());
        Assert.assertEquals(StringTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}