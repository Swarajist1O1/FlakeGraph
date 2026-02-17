class DummyClass_135711 {
    @Test
    public void testFallback() {
        HystrixCommand<Integer> superCmd = new SuperCommand("cache", false);
        assertEquals(2, superCmd.execute().intValue());

        HystrixCommand<Integer> subNoOverridesCmd = new SubCommandNoOverride("cache", false);
        assertEquals(2, subNoOverridesCmd.execute().intValue());

        HystrixCommand<Integer> subOverriddenFallbackCmd = new SubCommandOverrideFallback("cache", false);
        assertEquals(3, subOverriddenFallbackCmd.execute().intValue());
    }

}