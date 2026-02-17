class DummyClass_99723 {
    @Test
    public void testMergingIterator() throws IOException
    {
        File f = generateQueries(100, false);
        File f2 = generateQueries(100, false);
        int queryCount = 0;
        try (ChronicleQueue queue = ChronicleQueueBuilder.single(f).build();
             ChronicleQueue queue2 = ChronicleQueueBuilder.single(f2).build();
             FQLQueryIterator iter = new FQLQueryIterator(queue.createTailer(), 101);
             FQLQueryIterator iter2 = new FQLQueryIterator(queue2.createTailer(), 101);
             MergeIterator<FQLQuery, List<FQLQuery>> merger = MergeIterator.get(Lists.newArrayList(iter, iter2), FQLQuery::compareTo, new Replay.Reducer()))
        {
            long last = -1;

            while (merger.hasNext())
            {
                List<FQLQuery> qs = merger.next();
                assertEquals(2, qs.size());
                assertEquals(0, qs.get(0).compareTo(qs.get(1)));
                assertTrue(qs.get(0).queryStartTime >= last);
                last = qs.get(0).queryStartTime;
                queryCount++;
            }
        }
        assertEquals(100, queryCount);
    }

}