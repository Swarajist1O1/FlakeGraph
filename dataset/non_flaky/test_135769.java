class DummyClass_135769 {
    @Test
    public void testConcurrencyStrategyViaProperty() {
        try {
            String fullClass = HystrixConcurrencyStrategyTestImpl.class.getName();
            System.setProperty("hystrix.plugin.HystrixConcurrencyStrategy.implementation", fullClass);
            HystrixConcurrencyStrategy impl = HystrixPlugins.getInstance().getConcurrencyStrategy();
            assertTrue(impl instanceof HystrixConcurrencyStrategyTestImpl);
        } finally {
            System.clearProperty("hystrix.plugin.HystrixConcurrencyStrategy.implementation");
        }
	}*/

}