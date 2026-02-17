class DummyClass_98229 {
    @Test
    public void testPerformance() throws Exception {
        testPerformance("2.3");

        System.setProperty(QueryEngine.NATIVE_SORT_SYSTEM_PROPERTY, "true");
        testPerformance("2.3-expSort", getDefaultConfig());
        System.setProperty(QueryEngine.NATIVE_SORT_SYSTEM_PROPERTY, "false");
    }

}