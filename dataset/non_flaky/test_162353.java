class DummyClass_162353 {
    @Test
    public void testErrorHander() throws InterruptedException {
        ActionExecution ae = runAction("testErrorHandler", "");
//        dumpState(ae);
        ProgressMonitorReporter pm = ae.getMonitor();
        assertFalse(pm.succeeded());
        assertEquals(2, pm.getMessages().size());
        assertTrue( pm.getMessages().get(0).getMessage().contains("Forcing error from CreateErrorAction") );
        assertEquals( "Error detected", pm.getMessages().get(1).getMessage() );

        ae = runAction("testErrorTimeout", "");
        Thread.sleep(10);  // Allow time out processing to complete, more robust way?
        pm = ae.getMonitor();
        assertFalse(pm.succeeded());
        List<ProgressMessage> messages = pm.getMessages();
        assertEquals( "Timeout detected", messages.get(messages.size() - 1).getMessage());
    }

}