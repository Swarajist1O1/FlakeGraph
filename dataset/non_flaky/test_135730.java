class DummyClass_135730 {
    @Test
    public void testBadRequestsDoNotAffectErrorPercentage() {
        String key = "cmd-metrics-B";
        try {

            HystrixCommand<Boolean> cmd1 = new SuccessCommand(key ,1);
            HystrixCommandMetrics metrics = cmd1.metrics;
            cmd1.execute();
            Thread.sleep(100);
            assertEquals(0, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd2 = new FailureCommand(key, 1);
            cmd2.execute();
            Thread.sleep(100);
            assertEquals(50, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd3 = new BadRequestCommand(key, 1);
            HystrixCommand<Boolean> cmd4 = new BadRequestCommand(key, 1);
            try {
                cmd3.execute();
            } catch (HystrixBadRequestException ex) {
                System.out.println("Caught expected HystrixBadRequestException from cmd3");
            }
            try {
                cmd4.execute();
            } catch (HystrixBadRequestException ex) {
                System.out.println("Caught expected HystrixBadRequestException from cmd4");
            }
            Thread.sleep(100);
            assertEquals(50, metrics.getHealthCounts().getErrorPercentage());

            HystrixCommand<Boolean> cmd5 = new FailureCommand(key, 1);
            HystrixCommand<Boolean> cmd6 = new FailureCommand(key, 1);
            cmd5.execute();
            cmd6.execute();
            Thread.sleep(100);
            assertEquals(75, metrics.getHealthCounts().getErrorPercentage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error occurred : " + e.getMessage());
        }
    }

}