class DummyClass_135747 {
    @Test
    public void testString() throws Exception {

        DynamicStringProperty pString = new DynamicStringProperty("defaultString", "default-default");
        HystrixPropertiesChainedArchaiusProperty.StringProperty fString = new HystrixPropertiesChainedArchaiusProperty.StringProperty("overrideString", pString);

        assertTrue("default-default".equals(fString.get()));

        ConfigurationManager.getConfigInstance().setProperty("defaultString", "default");
        assertTrue("default".equals(fString.get()));

        ConfigurationManager.getConfigInstance().setProperty("overrideString", "override");
        assertTrue("override".equals(fString.get()));

        ConfigurationManager.getConfigInstance().clearProperty("overrideString");
        assertTrue("default".equals(fString.get()));

        ConfigurationManager.getConfigInstance().clearProperty("defaultString");
        assertTrue("default-default".equals(fString.get()));
    }

}