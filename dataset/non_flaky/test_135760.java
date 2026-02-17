class DummyClass_135760 {
    @Test(expected=ServiceConfigurationError.class)
    public void testDynamicPropertiesFailure() throws Exception {
        /*
         * James Bond: Do you expect me to talk?
         * Auric Goldfinger: No, Mr. Bond, I expect you to die!
         */
        fakeServiceLoaderResource = 
                "FAKE_META_INF_SERVICES/com.netflix.hystrix.strategy.properties.HystrixDynamicPropertiesFail";
        HystrixPlugins plugins = setupMockServiceLoader();
        plugins.getDynamicProperties();

    }

}