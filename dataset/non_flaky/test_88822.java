class DummyClass_88822 {
    @Test
    public void averageCalculation() throws InterruptedException {
        IntervalBasedMeasurement measurement = new IntervalBasedMeasurement(100, 1);

        for (int i = 0; i < 1000; i++)
            measurement.addMeasurementForAverageCalculation(100);

        assertEquals(100, measurement.getAverage());

        Thread.sleep(220);

        assertEquals(0, measurement.getAverage());

        assertEquals(0, measurement.getSpeedOpsPerSec(System.nanoTime()));
    }

}