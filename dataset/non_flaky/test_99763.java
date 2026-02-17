class DummyClass_99763 {
    @Test(expected = IllegalArgumentException.class)
    public void testHighRatioMustBeSmallerEqualThanOne() throws Exception
    {
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "2", FACTOR, "2", FLOW, "FAST"), new TestTimeSource(), 10);
    }

}