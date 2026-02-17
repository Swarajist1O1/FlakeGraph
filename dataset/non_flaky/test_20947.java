class DummyClass_20947 {
    @Test
    public void testConstantTimeRate() throws Exception {
        SortedMapIterator source = new SortedMapIterator(table);
        RateIterator iter = new RateIterator();
        IteratorSetting settings = new IteratorSetting(100, RateIterator.class);
        iter.init(source, settings.getOptions(), SCAN_IE);
        iter.seek(new Range(), EMPTY_COL_FAMS, true);
        for (int i = 0; i < 99; i++) {
            assertTrue(iter.hasTop());
            assertEquals(0.001D, MetricAdapter.decodeValue(iter.getTopValue().get()), 0.0D);
            iter.next();
        }
        assertFalse(iter.hasTop());
    }

}