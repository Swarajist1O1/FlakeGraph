class DummyClass_20921 {
    @Test
    public void testWrite() throws Exception {
        Thread t = new Thread(server);
        t.start();
        setupPlugin();
        while (!server.ready()) {
            Thread.sleep(1000);
        }
        Assert.assertEquals(0, plugin.write(createMetric()));
        Thread.sleep(100);
        Assert.assertTrue(server.messageReceived());
        plugin.shutdown();
        server.shutdown();
        t.join();
    }

}