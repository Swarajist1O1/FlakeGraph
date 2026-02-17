class DummyClass_99705 {
    @Test
    public void setSeed() throws Exception
    {
        Distribution dist = OptionDistribution.get("seq(1..10)").get();
        assertTrue(dist instanceof DistributionSequence);

        for (int seed=1; seed<500; seed+=seed)
        {
            dist.setSeed(seed);
            assertEquals(1, dist.minValue());
            assertEquals(10, dist.maxValue());
            assertEquals(5, dist.average());

            assertEquals(1, dist.inverseCumProb(0d));
            assertEquals(10, dist.inverseCumProb(1d));

            long last = dist.next();
            for (int i = 0; i < 9; i++)
            {
                long next = dist.next();
                if (next>1)
                {
                    assertEquals(next, last + 1); //increase by one each step
                }else{
                    assertEquals(last, 10); //wrap after the end
                }
                last = next;
            }
        }
    }

}