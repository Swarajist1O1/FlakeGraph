class DummyClass_114000 {
    @Test
    public void testSessionInitialized() throws Exception {
        ActionProxy proxy = getActionProxy("/test/testAction-2.action");
        Assert.assertNotNull("invocation session should being initialized",
                proxy.getInvocation().getInvocationContext().getSession());
    }

}