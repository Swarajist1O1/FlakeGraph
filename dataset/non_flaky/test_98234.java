class DummyClass_98234 {
    @Test
    public void testPerformance() throws Exception {
        testPerformance("2.4");

        System.setProperty(QueryEngine.NATIVE_SORT_SYSTEM_PROPERTY, "true");
        testPerformance("2.4-expSort", getDefaultConfig());
        System.setProperty(QueryEngine.NATIVE_SORT_SYSTEM_PROPERTY, "false");
    }

}