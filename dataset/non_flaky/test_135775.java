class DummyClass_135775 {
    @Test
    public void testPropertiesStrategyViaProperty() {
        try {
            String fullClass = HystrixPropertiesStrategyTestImpl.class.getName();
            System.setProperty("hystrix.plugin.HystrixPropertiesStrategy.implementation", fullClass);
            HystrixPropertiesStrategy impl = HystrixPlugins.getInstance().getPropertiesStrategy();
            assertTrue(impl instanceof HystrixPropertiesStrategyTestImpl);
        } finally {
            System.clearProperty("hystrix.plugin.HystrixPropertiesStrategy.implementation");
        }
	}*/

}