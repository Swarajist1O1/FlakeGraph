class DummyClass_20948 {
    @Test
    public void testRateWithTimeJitter() throws Exception {
        table.clear();
        Random r = new Random(111131131L);
        long ts = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            ts += 1000 + r.nextInt(100);
            Metric m = new Metric("sys.cpu.user", ts, i * 1.0D, tags);
            byte[] row = MetricAdapter.encodeRowKey(m);
            Key k = new Key(row, tags.get(0).join().getBytes(StandardCharsets.UTF_8),
                    MetricAdapter.encodeColQual(ts, ""), new byte[0], ts);
            Value v = new Value(MetricAdapter.encodeValue(m.getValue().getMeasure()));
            table.put(k, v);
        }

        SortedMapIterator source = new SortedMapIterator(table);
        source.seek(new Range(), EMPTY_COL_FAMS, true);
        long prevTs = -1L;
        Double prevValue = null;
        List<Double> expected = new ArrayList<>();
        while (source.hasTop()) {
            Key k = source.getTopKey();
            Value v = source.getTopValue();
            if (prevTs != -1L) {
                Double thisValue = MetricAdapter.decodeValue(v.get());
                expected.add((thisValue + (prevValue * -1)) / (k.getTimestamp() - prevTs));
            }
            prevTs = k.getTimestamp();
            prevValue = MetricAdapter.decodeValue(v.get());
            source.next();
        }

        assertEquals(99, expected.size());
        source = new SortedMapIterator(table);
        RateIterator iter = new RateIterator();
        IteratorSetting settings = new IteratorSetting(100, RateIterator.class);
        iter.init(source, settings.getOptions(), SCAN_IE);
        iter.seek(new Range(), EMPTY_COL_FAMS, true);
        for (int i = 0; i < 99; i++) {
            assertTrue(iter.hasTop());
            assertEquals(expected.get(i), MetricAdapter.decodeValue(iter.getTopValue().get()), 0.0D);
            iter.next();
        }
        assertFalse(iter.hasTop());
    }

}