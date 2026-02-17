class DummyClass_162354 {
    @Test
    public void testCompoundflows() throws InterruptedException {
        ActionExecution ae = runAction("sequenceTest", "");
        ProgressMonitorReporter pm = ae.getMonitor();
        assertTrue(pm.succeeded());
        List<ProgressMessage> messages = pm.getMessages();
        assertEquals(3, messages.size());
        assertEquals("sequence 1", messages.get(0).getMessage());
        assertEquals("sequence 2", messages.get(1).getMessage());
        assertEquals("sequence 3", messages.get(2).getMessage());

        ae = runAction("parTest", "");
//        dumpState(ae);
        pm = ae.getMonitor();
        assertTrue(pm.succeeded());
        messages = pm.getMessages();
        assertEquals(3, messages.size());
        for (int i = 0; i < 3; i++) {
            assertTrue( messages.get(i).getMessage().matches("par [123]") );
        }
    }

}