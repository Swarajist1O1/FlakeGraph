class DummyClass_99736 {
    @Test
    public void testFQLQuerySingleToStatement()
    {
        List<ByteBuffer> values = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            values.add(ByteBufferUtil.bytes(i));
        FQLQuery.Single single = new FQLQuery.Single("xyz",
                                                     QueryOptions.DEFAULT.getProtocolVersion().asInt(),
                                                     QueryOptions.forInternalCalls(values),
                                                     1234,
                                                     12345,
                                                     54321,
                                                     "select * from aaa",
                                                     values);
        Statement stmt = single.toStatement();
        assertEquals(stmt.getDefaultTimestamp(), 12345);
        assertTrue(stmt instanceof SimpleStatement);
        SimpleStatement simpleStmt = (SimpleStatement)stmt;
        assertEquals("select * from aaa",simpleStmt.getQueryString(CodecRegistry.DEFAULT_INSTANCE));
        assertArrayEquals(values.toArray(), simpleStmt.getValues(com.datastax.driver.core.ProtocolVersion.fromInt(QueryOptions.DEFAULT.getProtocolVersion().asInt()), CodecRegistry.DEFAULT_INSTANCE));
    }

}