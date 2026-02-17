class DummyClass_88825 {
    @Test
    public void beginOfCp() {
        PagesWriteSpeedBasedThrottle throttle = new PagesWriteSpeedBasedThrottle(pageMemory2g, null, stateChecker, log);

        assertTrue(throttle.getParkTime(0.01, 100,400000,
            1,
            20103,
            23103) == 0);

        //mark speed 22413 for mark all remaining as dirty
        long time = throttle.getParkTime(0.024, 100, 400000,
            1,
            24000,
            23103);
        assertTrue(time > 0);

        assertTrue(throttle.getParkTime(0.01,
            100,
            400000,
            1,
            22412,
            23103) == 0);
    }

}