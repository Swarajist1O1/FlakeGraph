class DummyClass_135715 {
    @Test
    public void testRequestLogSubClassNoOverrides() {
        HystrixCommand<Integer> subCmd = new SubCommandNoOverride("cache", true);
        assertEquals(1, subCmd.execute().intValue());
        System.out.println("REQ LOG : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        HystrixRequestLog reqLog = HystrixRequestLog.getCurrentRequest();
        assertEquals(1, reqLog.getAllExecutedCommands().size());
        HystrixInvokableInfo<?> info = reqLog.getAllExecutedCommands().iterator().next();
        assertEquals("SubCommandNoOverride", info.getCommandKey().name());
    }

}