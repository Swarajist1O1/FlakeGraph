class DummyClass_20920 {
    @Test
    public void testSerialization() throws Exception {
        MetricParser metricParser = new MetricParser();
        Tag t1 = new Tag("tag1=value1");
        Tag t2 = new Tag("tag2=value2");
        Tag avg = new Tag("sample=avg");
        Tag min = new Tag("sample=min");
        Tag max = new Tag("sample=max");
        Tag sum = new Tag("sample=sum");
        Tag count = new Tag("sample=count");
        Tag p50 = new Tag("sample=50p");
        Tag p75 = new Tag("sample=75p");
        Tag p90 = new Tag("sample=90p");
        Tag p99 = new Tag("sample=99p");

        List<Tag> tags = new ArrayList<>();
        tags.add(t1);
        tags.add(t2);
        m.initialize("sys.cpu.user", tags);

        byte[] bytes = m.serialize(m);
        String puts = new String(bytes);
        for (String put : puts.split("\n")) {
            Metric metric = metricParser.parse(put);
            Assert.assertEquals("sys.cpu.user_summarized", metric.getName());
            metric.getTags().forEach(t -> {
                Assert.assertTrue(
                        t.equals(t1) || t.equals(t2) || t.equals(avg) || t.equals(min) || t.equals(max) || t.equals(sum)
                                || t.equals(count) || t.equals(p50) || t.equals(p75) || t.equals(p90) || t.equals(p99));
            });
        }
    }

}