class DummyClass_134996 {
    @Test
    public void testClientSideIdleTimeout() throws Exception {
        //make a sub class
        CountDownLatch latch = new CountDownLatch(1);
        CloseCountdownEndpoint c = new CloseCountdownEndpoint(latch);

        Session session = deployment.connectToServer(c, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/chat/Bob"));
        session.setMaxIdleTimeout(100);
        Assert.assertTrue(latch.await(2000, TimeUnit.MILLISECONDS));
        Assert.assertFalse(session.isOpen());

    }

}