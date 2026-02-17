class DummyClass_135766 {
    @Test
    public void testEventNotifierViaProperty() {
        try {
            String fullClass = HystrixEventNotifierTestImpl.class.getName();
            System.setProperty("hystrix.plugin.HystrixEventNotifier.implementation", fullClass);
            HystrixEventNotifier impl = HystrixPlugins.getInstance().getEventNotifier();
            assertTrue(impl instanceof HystrixEventNotifierTestImpl);
        } finally {
            System.clearProperty("hystrix.plugin.HystrixEventNotifier.implementation");
        }
	}*/

}