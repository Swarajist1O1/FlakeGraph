class DummyClass_113984 {
    @Test
    public void testEmptyClassesToString() throws Exception {
        ConstantConfig constantConfig = new ConstantConfig();

        constantConfig.setExcludedClasses(new HashSet<Class<?>>());

        Map<String, String> map = constantConfig.getAllAsStringsMap();
        Assert.assertEquals(null, map.get(StrutsConstants.STRUTS_EXCLUDED_CLASSES));
    }

}