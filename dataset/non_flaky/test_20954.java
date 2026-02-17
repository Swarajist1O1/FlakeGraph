class DummyClass_20954 {
    @Test
    public void testMixed() throws Exception {
        SortedMap<Key, Value> table = new TreeMap<>();
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME), new byte[0], new byte[0], new byte[0],
                TEST_TIME), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 1), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 1), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 2), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 2), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 3), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 3), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 4), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 4), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 5), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 5), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME), new byte[0], new byte[0], new byte[0],
                TEST_TIME), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 1), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 1), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 2), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 2), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 3), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 3), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 4), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 4), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 5), new byte[0], new byte[0],
                new byte[0], TEST_TIME + 5), EMPTY_VALUE);

        SortedKeyValueIterator<Key, Value> source = new SortedMapIterator(table);
        MetricAgeOffIterator iter = new MetricAgeOffIterator();
        HashMap<String, String> options = new HashMap<>();
        options.put(MetricAgeOffIterator.AGE_OFF_PREFIX + "default", Integer.toString(1 * ONE_DAY));
        iter.init(source, options, null);
        iter.seek(new Range(), columnFamilies, true);
        int seen = 0;
        while (iter.hasTop()) {
            Key k = iter.getTopKey();
            Assert.assertTrue(k.getTimestamp() >= TEST_TIME && k.getTimestamp() <= TEST_TIME + 5);
            seen++;
            iter.next();
        }
        Assert.assertEquals(12, seen);
    }

}