class DummyClass_99724 {
    @Test
    public void testFQLQueryReader() throws IOException
    {
        FQLQueryReader reader = new FQLQueryReader();

        try (ChronicleQueue queue = ChronicleQueueBuilder.single(generateQueries(1000, true)).build())
        {
            ExcerptTailer tailer = queue.createTailer();
            int queryCount = 0;
            while (tailer.readDocument(reader))
            {
                assertNotNull(reader.getQuery());
                if (reader.getQuery() instanceof FQLQuery.Single)
                {
                    assertTrue(reader.getQuery().keyspace() == null || reader.getQuery().keyspace().equals("querykeyspace"));
                }
                else
                {
                    assertEquals("someks", reader.getQuery().keyspace());
                }
                queryCount++;
            }
            assertEquals(1000, queryCount);
        }
    }

}