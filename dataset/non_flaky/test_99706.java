class DummyClass_99706 {
    @Test
    public void simpleGaussian()
    {
        Distribution dist = OptionDistribution.get("gaussian(1..10)").get();
        assertTrue(dist instanceof DistributionBoundApache);

        assertEquals(1, dist.minValue());
        assertEquals(10, dist.maxValue());
        assertEquals(5, dist.average());

        assertEquals(1, dist.inverseCumProb(0d));
        assertEquals(10, dist.inverseCumProb(1d));

        int testCount = 100000;
        int[] results = new int[11];
        for (int i = 0; i < testCount; i++)
        {
            int val = toIntExact(dist.next());
            results[val]++;
        }

        // Increasing for the first half
        for (int i = toIntExact(dist.minValue()); i < dist.average(); i++)
        {
            assertTrue(results[i] < results[i + 1]);
        }

        // Decreasing for the second half
        for (int i = toIntExact(dist.average()) + 1; i < dist.maxValue(); i++)
        {
            assertTrue(results[i] > results[i + 1]);
        }
    }

}