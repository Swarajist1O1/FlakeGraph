class DummyClass_92696 {
    @Test
    public void testName() throws Exception {
        MethodConfig method = new MethodConfig();
        method.setName("hello");
        assertThat(method.getName(), equalTo("hello"));
        Map<String, String> parameters = new HashMap<String, String>();
        MethodConfig.appendParameters(parameters, method);
        assertThat(parameters, not(hasKey("name")));
    }

}