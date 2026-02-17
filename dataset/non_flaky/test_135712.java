class DummyClass_135712 {
    @Test
    public void testRequestCacheSuperClass() {
        HystrixCommand<Integer> superCmd1 = new SuperCommand("cache", true);
        assertEquals(1, superCmd1.execute().intValue());
        HystrixCommand<Integer> superCmd2 = new SuperCommand("cache", true);
        assertEquals(1, superCmd2.execute().intValue());
        HystrixCommand<Integer> superCmd3 = new SuperCommand("no-cache", true);
        assertEquals(1, superCmd3.execute().intValue());
        System.out.println("REQ LOG : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        HystrixRequestLog reqLog = HystrixRequestLog.getCurrentRequest();
        assertEquals(3, reqLog.getAllExecutedCommands().size());
        List<HystrixInvokableInfo<?>> infos = new ArrayList<HystrixInvokableInfo<?>>(reqLog.getAllExecutedCommands());
        HystrixInvokableInfo<?> info1 = infos.get(0);
        assertEquals("SuperCommand", info1.getCommandKey().name());
        assertEquals(1, info1.getExecutionEvents().size());
        HystrixInvokableInfo<?> info2 = infos.get(1);
        assertEquals("SuperCommand", info2.getCommandKey().name());
        assertEquals(2, info2.getExecutionEvents().size());
        assertEquals(HystrixEventType.RESPONSE_FROM_CACHE, info2.getExecutionEvents().get(1));
        HystrixInvokableInfo<?> info3 = infos.get(2);
        assertEquals("SuperCommand", info3.getCommandKey().name());
        assertEquals(1, info3.getExecutionEvents().size());
    }

}