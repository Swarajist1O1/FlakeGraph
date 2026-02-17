class DummyClass_113999 {
    @Test
    public void testExecuteActionAgainstCustomStrutsConfigFile() throws Exception {
        String output = executeAction("/test/testAction-2.action");
        Assert.assertEquals("Test-2", output);
    }

}