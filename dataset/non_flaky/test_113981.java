class DummyClass_113981 {
    @Test
    public void testRegister() throws Exception {
        final ConstantConfig constantConfig = new ConstantConfig();
        constantConfig.setDevMode(true);

        final String expectedUnknownHandler = "expectedUnknownHandler";

        StrutsJavaConfiguration javaConfig = new StrutsJavaConfiguration() {
            @Override
            public List<String> unknownHandlerStack() {
                return Arrays.asList(expectedUnknownHandler);
            }

}