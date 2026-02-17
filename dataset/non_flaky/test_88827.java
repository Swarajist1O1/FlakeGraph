class DummyClass_88827 {
    @Test
    public void tooMuchPagesMarkedDirty() {
        PagesWriteSpeedBasedThrottle throttle = new PagesWriteSpeedBasedThrottle(pageMemory2g, null, stateChecker, log);

       // 363308	350004	348976	10604
        long time = throttle.getParkTime(0.75,
            ((350004 + 348976) / 2),
            350004-10604,
            4,
            279,
            23933);

        System.err.println(time);

        assertTrue(time == 0);
    }

}