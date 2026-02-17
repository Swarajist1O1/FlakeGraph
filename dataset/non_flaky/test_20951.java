class DummyClass_20951 {
    @Test
    public void testCounterRateWithReset() throws Exception {
        table.clear();
        long ts = System.currentTimeMillis();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                ts += 1000;
                Metric m = new Metric("sys.cpu.user", ts, i * 1.0D, tags);
                byte[] row = MetricAdapter.encodeRowKey(m);
                Key k = new Key(row, tags.get(0).join().getBytes(StandardCharsets.UTF_8),
                        MetricAdapter.encodeColQual(ts, ""), new byte[0], ts);
                Value v = new Value(MetricAdapter.encodeValue(m.getValue().getMeasure()));
                table.put(k, v);
            }
        }

        SortedMapIterator source = new SortedMapIterator(table);
        RateIterator iter = new RateIterator();
        IteratorSetting settings = new IteratorSetting(100, RateIterator.class);

        QueryRequest.RateOption option = new QueryRequest.RateOption();
        option.setCounter(true);
        option.setCounterMax(Long.MAX_VALUE);
        option.setResetValue(1);
        RateIterator.setRateOptions(settings, option);

        iter.init(source, settings.getOptions(), SCAN_IE);
        iter.seek(new Range(), EMPTY_COL_FAMS, true);
        for (int i = 0; i < 99; i++) {
            assertTrue(iter.hasTop());
            assertEquals(((i + 1) % 10 == 0 ? 0.0D : 0.001D), MetricAdapter.decodeValue(iter.getTopValue().get()),
                    0.0D);
            iter.next();
        }
        assertFalse(iter.hasTop());
    }

}