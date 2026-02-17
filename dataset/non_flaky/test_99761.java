class DummyClass_99761 {
    @Test(expected = IllegalArgumentException.class)
    public void testAcceptsNoLessThanThreeArguments() throws Exception
    {
        new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "1"), new TestTimeSource(), 10);
    }

}