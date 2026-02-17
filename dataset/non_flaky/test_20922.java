class DummyClass_20922 {
    @Test
    public void testWriteAfterServerRestart() throws Exception {
        Thread t = new Thread(server);
        t.start();
        setupPlugin();
        while (!server.ready()) {
            Thread.sleep(1000);
        }
        Assert.assertEquals(0, plugin.write(createMetric()));
        Thread.sleep(100);
        Assert.assertTrue(server.messageReceived());
        server.shutdown();
        t.join();
        Thread.sleep(2000);

        server.create();
        Thread t2 = new Thread(server);
        t2.start();
        // Need to call this again because the server is not guaranteed to be
        // listening on the same local port as the first time that it was
        // started
        setupPlugin();
        while (!server.ready()) {
            Thread.sleep(1000);
            // Keep sending metrics to plugin to force reconnect
            int result = plugin.write(createMetric());
            System.out.println("Wrote to client, result: " + result);
            Assert.assertEquals(0, result);
        }
        Assert.assertEquals(0, plugin.write(createMetric()));
        Thread.sleep(1000);
        Assert.assertTrue(server.messageReceived());
        plugin.shutdown();
        server.shutdown();
        t2.join();
    }

}