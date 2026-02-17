class DummyClass_38250 {
    @Test
    public void testGetRangeWithTimestamps() {
        testGetRangeWithTimestamps(false);
        if (reverseRangesSupported()) {
            testGetRangeWithTimestamps(true);
        }
    }

}