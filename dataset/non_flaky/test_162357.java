class DummyClass_162357 {
    @Test
    public void testSimpleActions() throws InterruptedException, ExecutionException {
        Action action = new DummyAction();
        ActionManager am = new ActionManager();
        am.register(action);
        
        ActionManager.ActionExecution ae1 = am.runAction(action, createParams("message=Test message,count=2"));
        ActionManager.ActionExecution ae2 = am.runAction(action, createParams("message=Test message long,count=50"));

        assertEquals(2, am.listActiveExecutions().size());
        
        ae1.waitForCompletion();
//        dumpState(ae1);
        List<ProgressMessage> messages = ae1.getMonitor().getMessages();
        assertEquals(4, messages.size());
        assertTrue(messages.get(messages.size() - 1).toString().endsWith("finished"));
        assertEquals(1, am.listActiveExecutions().size());
        assertTrue(ae1.getMonitor().succeeded());
        
        ae2.waitForCompletion();
//        dumpState(ae2);
        Thread.sleep(10);  // Allow ActionManager to see the timeout and update the action state list, more robust method?
        messages = ae2.getMonitor().getMessages();
        assertTrue(messages.size() < 50);
        assertTrue(messages.get(messages.size() - 1).toString().endsWith("timeout"));
        assertFalse(ae2.getMonitor().succeeded());
            
        assertEquals(0, am.listActiveExecutions().size());

        assertEquals(ae1, am.getExecution(ae1.getId()));
        assertEquals(ae2, am.getExecution(ae2.getId()));
    }

}