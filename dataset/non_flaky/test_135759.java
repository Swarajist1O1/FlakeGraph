class DummyClass_135759 {
    @Test
    public void testDynamicProperties() throws Exception {
        fakeServiceLoaderResource = 
                "FAKE_META_INF_SERVICES/com.netflix.hystrix.strategy.properties.HystrixDynamicProperties";
        HystrixPlugins plugins = setupMockServiceLoader();
        HystrixDynamicProperties properties = plugins.getDynamicProperties();
        plugins.getCommandExecutionHook();
        plugins.getPropertiesStrategy();
        assertTrue(properties instanceof MockHystrixDynamicPropertiesTest);

        assertEvents(
                "[serviceloader: META-INF/services/com.netflix.hystrix.strategy.properties.HystrixDynamicProperties"
                        + ", debug: [Created HystrixDynamicProperties instance by loading from ServiceLoader. Using class: {}, com.netflix.hystrix.strategy.HystrixPluginsTest.MockHystrixDynamicPropertiesTest]"
                        + ", property: hystrix.plugin.HystrixCommandExecutionHook.implementation"
                        + ", serviceloader: META-INF/services/com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook"
                        + ", property: hystrix.plugin.HystrixPropertiesStrategy.implementation"
                        + ", serviceloader: META-INF/services/com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy]");
    }

}