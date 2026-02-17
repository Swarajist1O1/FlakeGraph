class DummyClass_135768 {
    @Test
    public void testConcurrencyStrategyViaRegisterMethod() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new HystrixConcurrencyStrategyTestImpl());
        HystrixConcurrencyStrategy impl = HystrixPlugins.getInstance().getConcurrencyStrategy();
        assertTrue(impl instanceof HystrixConcurrencyStrategyTestImpl);
    }

}