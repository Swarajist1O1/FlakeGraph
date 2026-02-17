class DummyClass_135763 {
    @Test
    public void testCommandExecutionHookViaRegisterMethod() {
        HystrixPlugins.getInstance().registerCommandExecutionHook(new HystrixCommandExecutionHookTestImpl());
        HystrixCommandExecutionHook impl = HystrixPlugins.getInstance().getCommandExecutionHook();
        assertTrue(impl instanceof HystrixCommandExecutionHookTestImpl);
	}*/

}