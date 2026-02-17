class DummyClass_20959 {
    @Test
    public void testMixed() throws Exception {
        MetricAgeOffFilter filter = new MetricAgeOffFilter();
        HashMap<String, String> options = new HashMap<>();
        options.put(MetricAgeOffFilter.AGE_OFF_PREFIX + "default", Integer.toString(1 * ONE_DAY));
        filter.init(null, options, null);
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME), new byte[0],
                new byte[0], new byte[0], TEST_TIME), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 1), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 1), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 2), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 2), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 3), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 3), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 4), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 4), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + 5), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 5), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME), new byte[0],
                new byte[0], new byte[0], TEST_TIME), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 1), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 1), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 2), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 2), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 3), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 3), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 4), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 4), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + 5), new byte[0],
                new byte[0], new byte[0], TEST_TIME + 5), null));
    }

}