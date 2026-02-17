class DummyClass_135761 {
    @Test
    public void testDynamicSystemProperties() throws Exception {
        //On the off chance this is the first test lets not screw up all the other tests
        HystrixPlugins.getInstance();
        
        System.setProperty("hystrix.plugin.HystrixDynamicProperties.implementation", 
                "com.netflix.hystrix.strategy.properties.HystrixDynamicPropertiesSystemProperties");
        
        HystrixPlugins plugins = setupMockServiceLoader();
        assertTrue(plugins.getDynamicProperties() instanceof HystrixDynamicPropertiesSystemProperties);
        
        HystrixDynamicProperties p = plugins.getDynamicProperties();
        //Some minimum testing of system properties wrapper
        //this probably should be in its own test class.
        assertTrue(p.getBoolean("USE_DEFAULT", true).get());
        assertEquals("string", p.getString("USE_DEFAULT", "string").get());
        assertEquals(1L, p.getLong("USE_DEFAULT", 1L).get().longValue());
        assertEquals(1, p.getInteger("USE_DEFAULT", 1).get().intValue());
        assertNotNull(p.getString("path.separator", null).get());
        
        assertEvents("[debug: [Created HystrixDynamicProperties instance from System property named \"hystrix.plugin.HystrixDynamicProperties.implementation\". Using class: {}, com.netflix.hystrix.strategy.properties.HystrixDynamicPropertiesSystemProperties]]");

        System.clearProperty("hystrix.plugin.HystrixDynamicProperties.implementation");

    }

}