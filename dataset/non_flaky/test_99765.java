class DummyClass_99765 {
    @Test(expected = IllegalArgumentException.class)
    public void testWindowSizeMustBeBiggerEqualThanTen() throws Exception
    {
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "5", FLOW, "FAST"), new TestTimeSource(), 1);
    }

}