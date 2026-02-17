class DummyClass_179428 {
    @Test
    public void testTypehandler(){
        EntityHelper.initEntityNameMap(UserTypehandler.class, config);
        EntityTable entityTable = EntityHelper.getEntityTable(UserTypehandler.class);
        Assert.assertNotNull(entityTable);

        Set<EntityColumn> columns = entityTable.getEntityClassColumns();
        Assert.assertEquals(1, columns.size());

        for (EntityColumn column : columns) {
            Assert.assertEquals("name", column.getColumn());
            Assert.assertEquals("name", column.getProperty());

            Assert.assertEquals("name = #{name, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnEqualsHolder());
            Assert.assertEquals("name = #{record.name, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnEqualsHolder("record"));
            Assert.assertEquals("#{name, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnHolder());
            Assert.assertEquals("#{record.name, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnHolder("record"));
            Assert.assertEquals("#{record.name, typeHandler=org.apache.ibatis.type.BlobTypeHandler}", column.getColumnHolder("record", "suffix"));
            Assert.assertEquals("#{record.namesuffix, typeHandler=org.apache.ibatis.type.BlobTypeHandler},", column.getColumnHolder("record", "suffix", ","));
            Assert.assertNotNull(column.getTypeHandler());
        }

        ResultMap resultMap = entityTable.getResultMap(configuration);
        Assert.assertEquals("[NAME]", resultMap.getMappedColumns().toString());

        Assert.assertEquals(1, resultMap.getResultMappings().size());

        ResultMapping resultMapping = resultMap.getResultMappings().get(0);
        Assert.assertEquals("name", resultMapping.getColumn());
        Assert.assertEquals("name", resultMapping.getProperty());
        Assert.assertNull(resultMapping.getJdbcType());
        Assert.assertEquals(BlobTypeHandler.class, resultMapping.getTypeHandler().getClass());
    }

}