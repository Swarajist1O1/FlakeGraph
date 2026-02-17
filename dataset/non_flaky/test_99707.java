class DummyClass_99707 {
    @Test
    public void negValueGaussian()
    {
        Distribution dist = OptionDistribution.get("gaussian(-1000..-10)").get();
        assertTrue(dist instanceof DistributionBoundApache);

        assertEquals(-1000, dist.minValue());
        assertEquals( -10, dist.maxValue());
        assertEquals(-504, dist.average());

        assertEquals(-1000, dist.inverseCumProb(0d));
        assertEquals(-10, dist.inverseCumProb(1d));
    }

}