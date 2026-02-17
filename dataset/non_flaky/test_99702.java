class DummyClass_99702 {
    @Test
    public void simpleSequence() throws Exception
    {
        Distribution dist = OptionDistribution.get("seq(1..10)").get();
        assertTrue(dist instanceof DistributionSequence);

        assertEquals(1, dist.minValue());
        assertEquals(10, dist.maxValue());
        assertEquals(5, dist.average());

        assertEquals(1, dist.inverseCumProb(0d));
        assertEquals(10, dist.inverseCumProb(1d));

        long min = dist.next();
        assertEquals(1,min);

        long last = min;
        for (int i=0; i<9; i++)
        {
            long next = dist.next();
            assertEquals(next, last+1); //increase by one each step
            last = next;
        }

        assertEquals(1, dist.next()); // wrapping
    }

}