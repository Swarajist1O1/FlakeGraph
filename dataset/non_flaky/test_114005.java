class DummyClass_114005 {
	@Test
    public void executeAction() throws ServletException, UnsupportedEncodingException {
        String output = executeAction("/test/testAction.action");
        Assert.assertEquals("Hello", output);
    }

}