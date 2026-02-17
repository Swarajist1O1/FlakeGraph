class DummyClass_20946 {
    @Test
    public void testManySparseTimeSeries() throws Exception {
        table.clear();
        long ts = System.currentTimeMillis();
        List<Tag> tags1 = new ArrayList<>();
        tags1.add(new Tag("host", "r01n01"));
        List<Tag> tags2 = new ArrayList<>();
        tags2.add(new Tag("host", "r01n02"));
        List<Tag> tags3 = new ArrayList<>();
        tags3.add(new Tag("host", "r01n03"));
        for (int i = 0; i < 100; i++) {
            ts += 1000;
            Metric m = new Metric("sys.cpu.user", ts, i * 1.0D, tags1);
            byte[] row = MetricAdapter.encodeRowKey(m);
            Key k = new Key(row, tags1.get(0).join().getBytes(StandardCharsets.UTF_8),
                    MetricAdapter.encodeColQual(ts, ""), new byte[0], ts);
            Value v = new Value(MetricAdapter.encodeValue(m.getValue().getMeasure()));
            table.put(k, v);
            // jitter the time on the second time series
            Metric m2 = new Metric("sys.cpu.user", ts + 50, i * 2.0D, tags2);
            byte[] row2 = MetricAdapter.encodeRowKey(m2);
            Key k2 = new Key(row2, tags2.get(0).join().getBytes(StandardCharsets.UTF_8),
                    MetricAdapter.encodeColQual(ts, ""), new byte[0], ts + 50);
            Value v2 = new Value(MetricAdapter.encodeValue(m2.getValue().getMeasure()));
            table.put(k2, v2);
            Metric m3 = new Metric("sys.cpu.user", ts, i * 3.0D, tags3);
            byte[] row3 = MetricAdapter.encodeRowKey(m3);
            Key k3 = new Key(row3, tags3.get(0).join().getBytes(StandardCharsets.UTF_8),
                    MetricAdapter.encodeColQual(ts, ""), new byte[0], ts);
            Value v3 = new Value(MetricAdapter.encodeValue(m3.getValue().getMeasure()));
            table.put(k3, v3);
        }

        SortedMapIterator source = new SortedMapIterator(table);
        TimeSeriesGroupingIterator iter = new TimeSeriesGroupingIterator();
        IteratorSetting settings = new IteratorSetting(100, TimeSeriesGroupingIterator.class);
        settings.addOption(TimeSeriesGroupingIterator.FILTER, "0.20,0.20,0.20,0.20,0.20");
        iter.init(source, settings.getOptions(), SCAN_IE);
        iter.seek(new Range(), EMPTY_COL_FAMS, true);

        LinkedList<Double> first = new LinkedList<>();
        first.add(0D);
        first.add(1D);
        first.add(2D);
        first.add(3D);
        first.add(4D);
        LinkedList<Double> second = new LinkedList<>();
        second.add(0D);
        second.add(2D);
        second.add(4D);
        second.add(6D);
        second.add(8D);
        LinkedList<Double> third = new LinkedList<>();
        third.add(0D);
        third.add(3D);
        third.add(6D);
        third.add(9D);
        third.add(12D);

        // this section changed when the key structure changed so that identical
        // colFam values sorted consecutively within an given time period
        for (int i = 4; i < 100; i++) {
            checkNextResult(iter, first);
            shiftAndAdd(first, 1);
        }
        for (int i = 4; i < 100; i++) {
            System.out.println(i);
            checkNextResult(iter, second);
            shiftAndAdd(second, 2);
        }
        for (int i = 4; i < 100; i++) {
            checkNextResult(iter, third);
            shiftAndAdd(third, 3);
        }
        assertFalse(iter.hasTop());
    }

}