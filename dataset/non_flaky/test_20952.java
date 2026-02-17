class DummyClass_20952 {
    @Test(expected = IllegalArgumentException.class)
    public void testDefaultMissing() throws Exception {
        SortedMap<Key, Value> table = new TreeMap<>();
        SortedKeyValueIterator<Key, Value> source = new SortedMapIterator(table);
        MetricAgeOffIterator iter = new MetricAgeOffIterator();
        HashMap<String, String> options = new HashMap<>();
        iter.init(source, options, null);
    }

}