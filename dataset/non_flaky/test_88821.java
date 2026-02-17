class DummyClass_88821 {
    @Test
    public void noBreakIfNotFastWrite() {
        PagesWriteSpeedBasedThrottle throttle = new PagesWriteSpeedBasedThrottle(pageMemory2g, null, stateChecker, log);

        long time = throttle.getParkTime(0.47,
            ((362584 + 67064) / 2),
            328787,
            1,
            20103,
            23103);

        assertTrue(time == 0);
    }

}