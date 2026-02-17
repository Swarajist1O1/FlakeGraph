class DummyClass_99764 {
    @Test(expected = IllegalArgumentException.class)
    public void testFactorMustBeBiggerEqualThanOne() throws Exception
    {
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "0", FLOW, "FAST"), new TestTimeSource(), 10);
    }

}