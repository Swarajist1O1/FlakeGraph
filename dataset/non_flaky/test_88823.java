class DummyClass_88823 {
    @Test
    public void speedCalculation() throws InterruptedException {
        IntervalBasedMeasurement measurement = new IntervalBasedMeasurement(100, 1);

        for (int i = 0; i < 1000; i++)
            measurement.setCounter(i, System.nanoTime());

        long speed = measurement.getSpeedOpsPerSec(System.nanoTime());
        System.out.println("speed measured " + speed);
        assertTrue(speed > 1000);

        Thread.sleep(230);

        assertEquals(0, measurement.getSpeedOpsPerSec(System.nanoTime()));
    }

}