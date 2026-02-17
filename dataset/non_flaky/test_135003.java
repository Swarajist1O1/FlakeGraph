class DummyClass_135003 {
    @Test
    public void testTimeoutCloseReason() throws Exception {
        TimeoutEndpoint.reset();

        Session session = deployment.connectToServer(DoNothingEndpoint.class, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/timeout"));

        Assert.assertEquals(CloseReason.CloseCodes.CLOSED_ABNORMALLY, TimeoutEndpoint.getReason().getCloseCode());
    }

}