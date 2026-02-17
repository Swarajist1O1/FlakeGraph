class DummyClass_20994 {
    @Test
    public void simple() {
        Downsample dsample = new Downsample(10, 30, 1, new Avg());
        for (int i = 10; i < 30; i++) {
            dsample.add(i, i - 10);
        }
        int i = 0;
        for (Sample sample : dsample) {
            assertEquals(10 + i, sample.timestamp);
            assertTrue(sample.timestamp < 30);
            assertEquals(i, (int) sample.value);
            i++;
        }
        assertEquals(20, i);
        dsample = new Downsample(10, 100, 7, new Sum());
        for (int j = 0; j < 5; j++) {
            for (int k = 10; k < 100; k++) {
                dsample.add(k, j + 0.);
            }
        }
        i = 0;
        for (Sample sample : dsample) {
            assertEquals((1 + 2 + 3 + 4) * Math.min(7, (100 - (10 + i * 7))), sample.value, 0.0D);
            assertEquals(10 + i * 7, sample.timestamp);
            i++;
        }
        assertEquals((100 - 10) / 7 + 1, i);
        dsample = new Downsample(10, 30, 10, new Avg());
        for (int j = 10; j < 30; j++) {
            for (int k = 0; k < 10; k++) {
                dsample.add(j, k + 0.);
            }
        }
        for (int j = 0; j < 100; j++) {
            dsample.add(15, 0);
        }
        i = 0;
        for (Sample sample : dsample) {
            if (i == 0) {
                assertEquals(2.25, sample.value, 0.0D);
            } else {
                assertEquals(4.5, sample.value, 0.0D);
            }
            assertEquals(10 * i + 10, sample.timestamp);
            i++;
        }
        assertEquals(2, i);
    }

}