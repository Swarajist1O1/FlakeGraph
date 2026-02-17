class DummyClass_99704 {
    @Test
    public void bigSequence() throws Exception
    {
        Distribution dist = OptionDistribution.get(String.format("seq(1..%d)", Long.MAX_VALUE)).get();
        assertTrue(dist instanceof DistributionSequence);

        assertEquals(1, dist.minValue());
        assertEquals(Long.MAX_VALUE, dist.maxValue());

        assertEquals(1, dist.inverseCumProb(0d));
        assertEquals(Long.MAX_VALUE, dist.inverseCumProb(1d));

    }

}