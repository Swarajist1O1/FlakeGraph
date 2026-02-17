class DummyClass_88826 {
    @Test
    public void enforceThrottleAtTheEndOfCp() {
        PagesWriteSpeedBasedThrottle throttle = new PagesWriteSpeedBasedThrottle(pageMemory2g, null, stateChecker, log);

        long time1 = throttle.getParkTime(0.70, 300000, 400000,
            1, 20200, 23000);
        long time2 = throttle.getParkTime(0.71, 300000, 400000,
            1, 20200, 23000);

        assertTrue(time2 >= time1 * 2); // extra slowdown should be applied.

        long time3 = throttle.getParkTime(0.73, 300000, 400000,
            1, 20200, 23000);
        long time4 = throttle.getParkTime(0.74, 300000, 400000,
            1, 20200, 23000);

        assertTrue(time3 > time2);
        assertTrue(time4 > time3);
    }

}