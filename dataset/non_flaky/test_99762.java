class DummyClass_99762 {
    @Test(expected = IllegalArgumentException.class)
    public void testHighRatioMustBeBiggerThanZero() throws Exception
    {
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0", FACTOR, "2", FLOW, "FAST"), new TestTimeSource(), 10);
    }

}