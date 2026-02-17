class DummyClass_20957 {
    @Test(expected = IllegalArgumentException.class)
    public void testDefaultMissing() throws Exception {
        MetricAgeOffFilter filter = new MetricAgeOffFilter();
        HashMap<String, String> options = new HashMap<>();
        filter.init(null, options, null);
    }

}