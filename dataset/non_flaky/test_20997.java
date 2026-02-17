class DummyClass_20997 {
    @Test
    public void testDownsampleStartCalculation() throws Exception {
        long queryStart = System.currentTimeMillis() - 86400000;
        long period = 60000;
        long keyTimestamp = queryStart + (86400000 / 2 + 3256);

        Set<Long> expectedStartTimes = new HashSet<>();
        for (long i = queryStart; i < queryStart + 86400000; i += period) {
            expectedStartTimes.add(i);
        }
        assertEquals(1440, expectedStartTimes.size());

        long sampleStart = keyTimestamp - ((keyTimestamp - queryStart) % period);
        assertTrue(expectedStartTimes.contains(sampleStart));

    }

}