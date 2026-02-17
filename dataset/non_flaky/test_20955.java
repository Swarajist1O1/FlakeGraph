class DummyClass_20955 {
    @Test
    public void testAgeoffMixed() throws Exception {
        SortedMap<Key, Value> table = new TreeMap<>();
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME - (3 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME - (3 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME - (2 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME - (2 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME - (1 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME - (1 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME), new byte[0], new byte[0], new byte[0],
                TEST_TIME), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + ONE_DAY), new byte[0], new byte[0],
                new byte[0], TEST_TIME + ONE_DAY), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + (2 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME + (2 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME - (3 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME - (3 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME - (2 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME - (2 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME - (1 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME - (1 * ONE_DAY)), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME), new byte[0], new byte[0], new byte[0],
                TEST_TIME), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + ONE_DAY), new byte[0], new byte[0],
                new byte[0], TEST_TIME + ONE_DAY), EMPTY_VALUE);
        table.put(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + (2 * ONE_DAY)), new byte[0],
                new byte[0], new byte[0], TEST_TIME + (2 * ONE_DAY)), EMPTY_VALUE);

        SortedKeyValueIterator<Key, Value> source = new SortedMapIterator(table);
        MetricAgeOffIterator iter = new MetricAgeOffIterator();
        HashMap<String, String> options = new HashMap<>();
        options.put(MetricAgeOffIterator.AGE_OFF_PREFIX + "default", Integer.toString(1 * ONE_DAY));
        options.put(MetricAgeOffIterator.AGE_OFF_PREFIX + "sys.cpu.user", Integer.toString(2 * ONE_DAY));
        iter.init(source, options, null);
        iter.seek(new Range(), columnFamilies, true);
        int seen = 0;
        while (iter.hasTop()) {
            Key k = iter.getTopKey();
            Assert.assertTrue(
                    k.getTimestamp() >= (TEST_TIME - (2 * ONE_DAY)) && k.getTimestamp() <= TEST_TIME + (2 * ONE_DAY));
            seen++;
            iter.next();
        }
        Assert.assertEquals(7, seen);

    }

}