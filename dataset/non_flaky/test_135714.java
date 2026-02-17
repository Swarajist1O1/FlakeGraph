class DummyClass_135714 {
    @Test
    public void testRequestLogSuperClass() {
        HystrixCommand<Integer> superCmd = new SuperCommand("cache", true);
        assertEquals(1, superCmd.execute().intValue());
        System.out.println("REQ LOG : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        HystrixRequestLog reqLog = HystrixRequestLog.getCurrentRequest();
        assertEquals(1, reqLog.getAllExecutedCommands().size());
        HystrixInvokableInfo<?> info = reqLog.getAllExecutedCommands().iterator().next();
        assertEquals("SuperCommand", info.getCommandKey().name());
    }

}