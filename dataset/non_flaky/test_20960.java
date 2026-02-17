class DummyClass_20960 {
    @Test
    public void testAgeoffMixed() throws Exception {
        MetricAgeOffFilter filter = new MetricAgeOffFilter();
        HashMap<String, String> options = new HashMap<>();
        options.put(MetricAgeOffFilter.AGE_OFF_PREFIX + "default", Integer.toString(1 * ONE_DAY));
        options.put(MetricAgeOffFilter.AGE_OFF_PREFIX + "sys.cpu.user", Integer.toString(2 * ONE_DAY));
        filter.init(null, options, null);
        assertFalse(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME - (3 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME - (3 * ONE_DAY)), null));
        assertFalse(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME - (2 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME - (2 * ONE_DAY)), null));
        assertFalse(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME - (1 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME - (1 * ONE_DAY)), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME), new byte[0],
                new byte[0], new byte[0], TEST_TIME), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + ONE_DAY), new byte[0],
                new byte[0], new byte[0], TEST_TIME + ONE_DAY), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.idle", TEST_TIME + (2 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME + (2 * ONE_DAY)), null));
        assertFalse(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME - (3 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME - (3 * ONE_DAY)), null));
        assertFalse(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME - (2 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME - (2 * ONE_DAY)), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME - (1 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME - (1 * ONE_DAY)), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME), new byte[0],
                new byte[0], new byte[0], TEST_TIME), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + ONE_DAY), new byte[0],
                new byte[0], new byte[0], TEST_TIME + ONE_DAY), null));
        assertTrue(filter.accept(new Key(MetricAdapter.encodeRowKey("sys.cpu.user", TEST_TIME + (2 * ONE_DAY)),
                new byte[0], new byte[0], new byte[0], TEST_TIME + (2 * ONE_DAY)), null));
    }

}