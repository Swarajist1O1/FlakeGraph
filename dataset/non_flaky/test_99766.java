class DummyClass_99766 {
    @Test
    public void testFlowMustBeEitherFASTorSLOW() throws Exception
    {
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "1", FLOW, "FAST"), new TestTimeSource(), 10);
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "1", FLOW, "SLOW"), new TestTimeSource(), 10);
        try
        {
            new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "1", FLOW, "WRONG"), new TestTimeSource(), 10);
            fail("Expected to fail with wrong flow type.");
        }
        catch (Exception ex)
        {
        }
    }

}