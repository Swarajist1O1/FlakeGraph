class DummyClass_91528 {
    @Test
    public void testJdbcTypetoKylinDataType() {
        this.getClass().getClassLoader().toString();
        assertEquals("double", SqlUtil.jdbcTypeToKylinDataType(Types.FLOAT));
        assertEquals("varchar", SqlUtil.jdbcTypeToKylinDataType(Types.NVARCHAR));
        assertEquals("any", SqlUtil.jdbcTypeToKylinDataType(Types.ARRAY));
        assertEquals("integer", SqlUtil.jdbcTypeToKylinDataType((4)));
        assertEquals("smallint", SqlUtil.jdbcTypeToKylinDataType((5)));
        assertEquals("tinyint", SqlUtil.jdbcTypeToKylinDataType((-6)));
        assertEquals("char", SqlUtil.jdbcTypeToKylinDataType((1)));
        assertEquals("decimal", SqlUtil.jdbcTypeToKylinDataType((2)));
        assertEquals("varchar", SqlUtil.jdbcTypeToKylinDataType((-1)));
        assertEquals("byte", SqlUtil.jdbcTypeToKylinDataType((-2)));
        assertEquals("any", SqlUtil.jdbcTypeToKylinDataType((-1720774701)));
        assertEquals("boolean", SqlUtil.jdbcTypeToKylinDataType((-7)));
        assertEquals("timestamp", SqlUtil.jdbcTypeToKylinDataType((93)));
        assertEquals("time", SqlUtil.jdbcTypeToKylinDataType((92)));
        assertEquals("date", SqlUtil.jdbcTypeToKylinDataType((91)));
        assertEquals("bigint", SqlUtil.jdbcTypeToKylinDataType((-5)));
    }

}