class DummyClass_20995 {
    @Test
    public void testCombineTrivial() throws Exception {
        Downsample ds = new Downsample(0, 1000, 100, new Avg());
        for (int i = 0; i < 1000; i += 100) {
            ds.add(i, .2);
        }
        Downsample result = Downsample.combineDownsample(Collections.singleton(ds), null);
        int count = 0;
        for (Sample s : result) {
            assertEquals(.2, s.value, 0.0D);
            count++;
        }
        assertEquals(10, count);
    }

}