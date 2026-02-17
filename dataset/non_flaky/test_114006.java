class DummyClass_114006 {
	@Test
    public void getValueFromStack() throws ServletException, UnsupportedEncodingException {
        request.setParameter("name", "FD");
        executeAction("/test/testAction.action");
        String name = (String) findValueAfterExecute("name");
        Assert.assertEquals("FD", name);
    }

}