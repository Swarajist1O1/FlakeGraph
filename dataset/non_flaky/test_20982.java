class DummyClass_20982 {
    @Test
    public void testGenerateHtml() throws Exception {
        Configuration cfg = TestConfiguration.createMinimalConfigurationForTest();
        MetaCache cache = MetaCacheFactory.getCache(cfg);
        cache.add(new Meta("sys.cpu.user", "host", "localhost"));
        cache.add(new Meta("sys.cpu.user", "instance", "0"));
        cache.add(new Meta("sys.cpu.idle", "host", "localhost"));
        cache.add(new Meta("sys.cpu.idle", "instance", "0"));
        TestMetricsResponse r = new TestMetricsResponse(cfg);
        String html = r.generateHtml().toString();
        Assert.assertTrue(html.contains("<td>sys.cpu.idle</td>"));
        Assert.assertTrue(html.contains("<td>host=localhost instance=0 </td>"));
        Assert.assertTrue(html.contains("<td>sys.cpu.user</td>"));
        Assert.assertTrue(html.contains("<td>host=localhost instance=0 </td>"));
    }

}