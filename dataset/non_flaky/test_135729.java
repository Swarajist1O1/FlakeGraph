class DummyClass_135729 {
    @Test
    public void testGetErrorPercentage() {
        String key = "cmd-metrics-A";
        try {
            HystrixCommand<Boolean> cmd1 = new SuccessCommand(key, 1);
            HystrixCommandMetrics metrics = cmd1.metrics;
            cmd1.execute();
            Thread.sleep(100);
            assertEquals(0, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd2 = new FailureCommand(key, 1);
            cmd2.execute();
            Thread.sleep(100);
            assertEquals(50, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd3 = new SuccessCommand(key, 1);
            HystrixCommand<Boolean> cmd4 = new SuccessCommand(key, 1);
            cmd3.execute();
            cmd4.execute();
            Thread.sleep(100);
            assertEquals(25, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd5 = new TimeoutCommand(key);
            HystrixCommand<Boolean> cmd6 = new TimeoutCommand(key);
            cmd5.execute();
            cmd6.execute();
            Thread.sleep(100);
            assertEquals(50, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd7 = new SuccessCommand(key, 1);
            HystrixCommand<Boolean> cmd8 = new SuccessCommand(key, 1);
            HystrixCommand<Boolean> cmd9 = new SuccessCommand(key, 1);
            cmd7.execute();
            cmd8.execute();
            cmd9.execute();

            // latent
            HystrixCommand<Boolean> cmd10 = new SuccessCommand(key, 60);
            cmd10.execute();

            // 6 success + 1 latent success + 1 failure + 2 timeout = 10 total
            // latent success not considered error
            // error percentage = 1 failure + 2 timeout / 10
            Thread.sleep(100);
            assertEquals(30, metrics.getHealthCounts().getErrorPercentage());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Error occurred: " + e.getMessage());
        }

    }

}