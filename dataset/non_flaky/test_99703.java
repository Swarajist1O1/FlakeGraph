class DummyClass_99703 {
    @Test
    public void negValueSequence() throws Exception
    {
        Distribution dist = OptionDistribution.get("seq(-1000..-10)").get();
        assertTrue(dist instanceof DistributionSequence);

        assertEquals(-1000, dist.minValue());
        assertEquals( -10, dist.maxValue());
        assertEquals(-504, dist.average());

        assertEquals(-1000, dist.inverseCumProb(0d));
        assertEquals(-10, dist.inverseCumProb(1d));

        long min = dist.next();
        assertEquals(-1000, min);

        long last = min;
        long next = dist.next();
        while (last<next)
        {
            assertEquals(next, last+1); //increase by one each step
            last = next;
            next = dist.next();
        }

        assertEquals(-10, last); // wrapping
        assertEquals(-1000, next); // wrapping
    }

}