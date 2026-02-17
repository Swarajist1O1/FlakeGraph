class DummyClass_162352 {
    @Test
    public void testSimpleActions() {
        ActionExecution ae = runAction("messageThrice", "message=test");
        ProgressMonitorReporter pm = ae.getMonitor();
        assertTrue(pm.succeeded());
        assertEquals(5, pm.getMessages().size());
        assertTrue( pm.getMessages().get(0).getMessage().startsWith("messageThrice") );
        assertEquals( "test", pm.getMessages().get(1).getMessage() );
        
        pm = runAction("helloThrice", "").getMonitor();
        assertTrue(pm.succeeded());
        assertEquals(5, pm.getMessages().size());
        assertTrue( pm.getMessages().get(0).getMessage().startsWith("helloThrice") );
        assertEquals( "Hello", pm.getMessages().get(1).getMessage() );
        
    }

}