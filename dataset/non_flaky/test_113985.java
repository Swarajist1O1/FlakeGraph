class DummyClass_113985 {
    @Test
    public void testClassesToString() throws Exception {
        ConstantConfig constantConfig = new ConstantConfig();

        Set<Class<?>> excludedClasses = new LinkedHashSet<>();
        excludedClasses.add(Object.class);
        excludedClasses.add(Runtime.class);
        excludedClasses.add(System.class);

        constantConfig.setExcludedClasses(excludedClasses);

        Map<String, String> map = constantConfig.getAllAsStringsMap();
        Assert.assertEquals("java.lang.Object,java.lang.Runtime,java.lang.System",
                map.get(StrutsConstants.STRUTS_EXCLUDED_CLASSES));
    }

}