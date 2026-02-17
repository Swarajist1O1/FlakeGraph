class DummyClass_88828 {
    @Test
    public void warningInCaseTooMuchThrottling() {
        AtomicInteger warnings = new AtomicInteger(0);
        IgniteLogger log = mock(IgniteLogger.class);

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();

            System.out.println("log.info() called with arguments: " + Arrays.toString(args));

            warnings.incrementAndGet();

            return null;
        }).when(log).info(anyString());

        AtomicInteger written = new AtomicInteger();
        CheckpointWriteProgressSupplier cpProgress = mock(CheckpointWriteProgressSupplier.class);
        when(cpProgress.writtenPagesCounter()).thenReturn(written);

        PagesWriteSpeedBasedThrottle throttle = new PagesWriteSpeedBasedThrottle(pageMemory2g, cpProgress, stateChecker, log) {
            @Override protected void doPark(long throttleParkTimeNs) {
                //do nothing
            }
        };
        throttle.onBeginCheckpoint();
        written.set(200); //emulating some pages written

        for (int i = 0; i < 100000; i++) {
            //emulating high load on marking
            throttle.onMarkDirty(false);

            if (throttle.throttleWeight() > PagesWriteSpeedBasedThrottle.WARN_THRESHOLD)
                break;
        }

        for (int i = 0; i < 1000; i++) {
            //emulating additional page writes to be sure log message is generated

            throttle.onMarkDirty(false);

            if(warnings.get()>0)
                break;
        }

        System.out.println(throttle.throttleWeight());

        assertTrue(warnings.get() > 0);
    }

}