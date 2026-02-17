class DummyClass_88824 {
    @Test
    public void speedWithDelayCalculation() throws InterruptedException {
        IntervalBasedMeasurement measurement = new IntervalBasedMeasurement(100, 1);

        int runs = 10;
        int nanosPark = 100;
        int multiplier = 100000;
        for (int i = 0; i < runs; i++) {
            measurement.setCounter(i * multiplier, System.nanoTime());

            LockSupport.parkNanos(nanosPark);
        }

        long speed = measurement.getSpeedOpsPerSec(System.nanoTime());

        assertTrue(speed > 0);
        long maxSpeed = (TimeUnit.SECONDS.toNanos(1) * multiplier * runs) / ((long)(runs * nanosPark));
        assertTrue(speed < maxSpeed);

        Thread.sleep(200);

        assertEquals(0, measurement.getSpeedOpsPerSec(System.nanoTime()));
    }

}