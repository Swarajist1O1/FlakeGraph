class DummyClass_99735 {
    @Test
    public void testCompare()
    {
        FQLQuery q1 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 123, 111, 222, "aaaa", Collections.emptyList());
        FQLQuery q2 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 123, 111, 222,"aaaa", Collections.emptyList());

        assertEquals(0, q1.compareTo(q2));
        assertEquals(0, q2.compareTo(q1));

        FQLQuery q3 = new FQLQuery.Batch("abc", 0, QueryOptions.DEFAULT, 123, 111, 222, com.datastax.driver.core.BatchStatement.Type.UNLOGGED, Collections.emptyList(), Collections.emptyList());
        // single queries before batch queries
        assertTrue(q1.compareTo(q3) < 0);
        assertTrue(q3.compareTo(q1) > 0);

        // check that smaller query time
        FQLQuery q4 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 124, 111, 222, "aaaa", Collections.emptyList());
        assertTrue(q1.compareTo(q4) < 0);
        assertTrue(q4.compareTo(q1) > 0);

        FQLQuery q5 = new FQLQuery.Batch("abc", 0, QueryOptions.DEFAULT, 124, 111, 222, com.datastax.driver.core.BatchStatement.Type.UNLOGGED, Collections.emptyList(), Collections.emptyList());
        assertTrue(q1.compareTo(q5) < 0);
        assertTrue(q5.compareTo(q1) > 0);

        FQLQuery q6 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 123, 111, 222, "aaaa", Collections.singletonList(ByteBufferUtil.bytes(10)));
        FQLQuery q7 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 123, 111, 222, "aaaa", Collections.emptyList());
        assertTrue(q6.compareTo(q7) > 0);
        assertTrue(q7.compareTo(q6) < 0);

        FQLQuery q8 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 123, 111, 222, "aaaa", Collections.singletonList(ByteBufferUtil.bytes("a")));
        FQLQuery q9 = new FQLQuery.Single("abc", 0, QueryOptions.DEFAULT, 123, 111, 222, "aaaa", Collections.singletonList(ByteBufferUtil.bytes("b")));
        assertTrue(q8.compareTo(q9) < 0);
        assertTrue(q9.compareTo(q8) > 0);
    }

}