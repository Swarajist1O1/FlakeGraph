class DummyClass_114036 {
    @Test
    public void handleRequest_shouldReturnConstantValue() {
        App function = new App();
        Object result = function.handleRequest("echo", null);
        assertEquals("echo", result);
    }

}