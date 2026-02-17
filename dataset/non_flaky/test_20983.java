class DummyClass_20983 {
    @Test
    public void testGenerateHtmlWithIgnoredTags() throws Exception {
        Configuration cfg = TestConfiguration.createMinimalConfigurationForTest();
        cfg.getMetricsReportIgnoredTags().add("instance");
        MetaCache cache = MetaCacheFactory.getCache(cfg);
        cache.add(new Meta("sys.cpu.user", "host", "localhost"));
        cache.add(new Meta("sys.cpu.user", "instance", "0"));
        cache.add(new Meta("sys.cpu.idle", "host", "localhost"));
        cache.add(new Meta("sys.cpu.idle", "instance", "0"));
        TestMetricsResponse r = new TestMetricsResponse(cfg);
        String html = r.generateHtml().toString();
        Assert.assertTrue(html.contains("<td>sys.cpu.idle</td>"));
        Assert.assertTrue(html.contains("<td>host=localhost </td>"));
        Assert.assertTrue(html.contains("<td>sys.cpu.user</td>"));
        Assert.assertTrue(html.contains("<td>host=localhost </td>"));
    }

}