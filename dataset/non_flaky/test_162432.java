class DummyClass_162432 {
    @Test @Ignore //TODO investigate intermittent failures
    public void failFastWhenContainerHaltsImmediately() throws Exception {

        long startingTimeMs = System.currentTimeMillis();
        final GenericContainer failsImmediately = new GenericContainer("alpine:3.2")
              .withCommand("/bin/sh", "-c", "return false")
              .withMinimumRunningDuration(Duration.ofMillis(100));

        try {
            assertThrows(
                  "When we start a container that halts immediately, an exception is thrown",
                  RetryCountExceededException.class,
                  () -> {
                      failsImmediately.start();
                      return null;
                  });

            // Check how long it took, to verify that we ARE bailing out early.
            // Want to strike a balance here; too short and this test will fail intermittently
            // on slow systems and/or due to GC variation, too long and we won't properly test
            // what we're intending to test.
            int allowedSecondsToFailure =
                GenericContainer.CONTAINER_RUNNING_TIMEOUT_SEC / 2;
            long completedTimeMs = System.currentTimeMillis();
            assertTrue("container should not take long to start up",
                  completedTimeMs - startingTimeMs < 1000L * allowedSecondsToFailure);
        } finally {
            failsImmediately.stop();
        }
    }

}