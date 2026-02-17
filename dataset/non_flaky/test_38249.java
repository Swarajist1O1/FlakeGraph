class DummyClass_38249 {
    @Test
    public void testGetRangeWithHistory() {
        testGetRangeWithHistory(false);
        if (reverseRangesSupported()) {
            testGetRangeWithHistory(true);
        }
    }

}