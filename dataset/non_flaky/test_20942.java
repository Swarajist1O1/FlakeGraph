class DummyClass_20942 {
    @Test
    public void testMovingAverage() throws Exception {
        SortedMapIterator source = new SortedMapIterator(table);
        TimeSeriesGroupingIterator iter = new TimeSeriesGroupingIterator();
        IteratorSetting settings = new IteratorSetting(100, TimeSeriesGroupingIterator.class);
        settings.addOption(TimeSeriesGroupingIterator.FILTER, "0.20,0.20,0.20,0.20,0.20");
        iter.init(source, settings.getOptions(), SCAN_IE);
        iter.seek(new Range(), EMPTY_COL_FAMS, true);

        for (int i = 4; i < 100; i++) {
            checkNextResult(iter, new double[] { i - 4, i - 3, i - 2, i - 1, i });
        }
        assertFalse(iter.hasTop());
    }

}