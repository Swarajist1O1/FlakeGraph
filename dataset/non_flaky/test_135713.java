class DummyClass_135713 {
    @Test
    public void testRequestCacheSubclassNoOverrides() {
        HystrixCommand<Integer> subCmd1 = new SubCommandNoOverride("cache", true);
        assertEquals(1, subCmd1.execute().intValue());
        HystrixCommand<Integer> subCmd2 = new SubCommandNoOverride("cache", true);
        assertEquals(1, subCmd2.execute().intValue());
        HystrixCommand<Integer> subCmd3 = new SubCommandNoOverride("no-cache", true);
        assertEquals(1, subCmd3.execute().intValue());
        System.out.println("REQ LOG : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        HystrixRequestLog reqLog = HystrixRequestLog.getCurrentRequest();
        assertEquals(3, reqLog.getAllExecutedCommands().size());
        List<HystrixInvokableInfo<?>> infos = new ArrayList<HystrixInvokableInfo<?>>(reqLog.getAllExecutedCommands());
        HystrixInvokableInfo<?> info1 = infos.get(0);
        assertEquals("SubCommandNoOverride", info1.getCommandKey().name());
        assertEquals(1, info1.getExecutionEvents().size());
        HystrixInvokableInfo<?> info2 = infos.get(1);
        assertEquals("SubCommandNoOverride", info2.getCommandKey().name());
        assertEquals(2, info2.getExecutionEvents().size());
        assertEquals(HystrixEventType.RESPONSE_FROM_CACHE, info2.getExecutionEvents().get(1));
        HystrixInvokableInfo<?> info3 = infos.get(2);
        assertEquals("SubCommandNoOverride", info3.getCommandKey().name());
        assertEquals(1, info3.getExecutionEvents().size());
    }

}