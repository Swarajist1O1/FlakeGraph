class DummyClass_98240 {
    @Test
    public void testPerformance() throws Exception {
        testPerformance("2.6");

        System.setProperty(QueryEngine.NATIVE_SORT_SYSTEM_PROPERTY, "true");
        testPerformance("2.6-expSort", getDefaultConfig());
        System.setProperty(QueryEngine.NATIVE_SORT_SYSTEM_PROPERTY, "false");
    }

}