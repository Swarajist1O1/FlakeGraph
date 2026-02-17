class DummyClass_88820 {
    @Test
    public void breakInCaseTooFast() {
        PagesWriteSpeedBasedThrottle throttle = new PagesWriteSpeedBasedThrottle(pageMemory2g, null, stateChecker, log);

        long time = throttle.getParkTime(0.67,
            (362584 + 67064) / 2,
            328787,
            1,
            60184,
            23103);

        assertTrue(time > 0);
    }

}