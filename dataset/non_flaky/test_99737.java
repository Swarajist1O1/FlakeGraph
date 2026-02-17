class DummyClass_99737 {
    @Test
    public void testFQLQueryBatchToStatement()
    {
        List<List<ByteBuffer>> values = new ArrayList<>();
        List<String> queries = new ArrayList<>();
        for (int bqCount = 0; bqCount < 10; bqCount++)
        {
            queries.add("select * from asdf where x = ? and y = " + bqCount);
            List<ByteBuffer> queryValues = new ArrayList<>();
            for (int i = 0; i < 10; i++)
                queryValues.add(ByteBufferUtil.bytes(i + ":" + bqCount));
            values.add(queryValues);
        }

        FQLQuery.Batch batch = new FQLQuery.Batch("xyz",
                                                   QueryOptions.DEFAULT.getProtocolVersion().asInt(),
                                                   QueryOptions.DEFAULT,
                                                   1234,
                                                   12345,
                                                   54321,
                                                   com.datastax.driver.core.BatchStatement.Type.UNLOGGED,
                                                   queries,
                                                   values);
        Statement stmt = batch.toStatement();
        assertEquals(stmt.getDefaultTimestamp(), 12345);
        assertTrue(stmt instanceof com.datastax.driver.core.BatchStatement);
        com.datastax.driver.core.BatchStatement batchStmt = (com.datastax.driver.core.BatchStatement)stmt;
        List<Statement> statements = Lists.newArrayList(batchStmt.getStatements());
        List<Statement> fromFQLQueries = batch.queries.stream().map(FQLQuery.Single::toStatement).collect(Collectors.toList());
        assertEquals(statements.size(), fromFQLQueries.size());
        assertEquals(12345, batchStmt.getDefaultTimestamp());
        for (int i = 0; i < statements.size(); i++)
            compareStatements(statements.get(i), fromFQLQueries.get(i));
    }

}