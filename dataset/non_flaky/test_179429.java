class DummyClass_179429 {
    @Test
    public void testAll(){
        EntityHelper.initEntityNameMap(UserAll.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserAll.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("user_name", column.getColumn());
            Assert.assertEquals("name", column.getProperty());

            Assert.assertEquals("user_name = #{name, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnEqualsHolder());
            Assert.assertEquals("user_name = #{record.name, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnEqualsHolder("record"));
            Assert.assertEquals("#{name, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnHolder());
            Assert.assertEquals("#{record.name, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnHolder("record"));
            Assert.assertEquals("#{record.name, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnHolder("record", "suffix"));
            Assert.assertEquals("#{record.namesuffix, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler},", column.getColumnHolder("record", "suffix", ","));
            Assert.assertNotNull(column.getTypeHandler());
        }

        ResultMap resultMap = entityTable.getResultMap(configuration);
        Assert.assertEquals("[USER_NAME]", resultMap.getMappedColumns().toString());

        Assert.assertEquals(1, resultMap.getResultMappings().size());

        ResultMapping resultMapping = resultMap.getResultMappings().get(0);
        Assert.assertEquals("user_name", resultMapping.getColumn());
        Assert.assertEquals("name", resultMapping.getProperty());
        Assert.assertNotNull(resultMapping.getJdbcType());
        Assert.assertEquals(BlobTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}