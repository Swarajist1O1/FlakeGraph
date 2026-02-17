class DummyClass_135748 {
    @Test
    public void testInteger() throws Exception {

        DynamicIntegerProperty pInt = new DynamicIntegerProperty("defaultInt", -1);
        HystrixPropertiesChainedArchaiusProperty.IntegerProperty fInt = new HystrixPropertiesChainedArchaiusProperty.IntegerProperty("overrideInt", pInt);

        assertTrue(-1 == fInt.get());

        ConfigurationManager.getConfigInstance().setProperty("defaultInt", 10);
        assertTrue(10 == fInt.get());

        ConfigurationManager.getConfigInstance().setProperty("overrideInt", 11);
        assertTrue(11 == fInt.get());

        ConfigurationManager.getConfigInstance().clearProperty("overrideInt");
        assertTrue(10 == fInt.get());

        ConfigurationManager.getConfigInstance().clearProperty("defaultInt");
        assertTrue(-1 == fInt.get());
    }

}