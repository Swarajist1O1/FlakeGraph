class DummyClass_99721 {
    @Test
    public void testOrderedReplay() throws IOException
    {
        File f = generateQueries(100, true);
        int queryCount = 0;
        try (ChronicleQueue queue = ChronicleQueueBuilder.single(f).build();
             FQLQueryIterator iter = new FQLQueryIterator(queue.createTailer(), 101))
        {
            long last = -1;
            while (iter.hasNext())
            {
                FQLQuery q = iter.next();
                assertTrue(q.queryStartTime >= last);
                last = q.queryStartTime;
                queryCount++;
            }
        }
        assertEquals(100, queryCount);
    }

}