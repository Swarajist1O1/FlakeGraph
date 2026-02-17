class DummyClass_135749 {
    @Test
    public void testBoolean() throws Exception {

        DynamicBooleanProperty pBoolean = new DynamicBooleanProperty("defaultBoolean", true);
        HystrixPropertiesChainedArchaiusProperty.BooleanProperty fBoolean = new HystrixPropertiesChainedArchaiusProperty.BooleanProperty("overrideBoolean", pBoolean);

        System.out.println("pBoolean: " + pBoolean.get());
        System.out.println("fBoolean: " + fBoolean.get());

        assertTrue(fBoolean.get());

        ConfigurationManager.getConfigInstance().setProperty("defaultBoolean", Boolean.FALSE);

        System.out.println("pBoolean: " + pBoolean.get());
        System.out.println("fBoolean: " + fBoolean.get());

        assertFalse(fBoolean.get());

        ConfigurationManager.getConfigInstance().setProperty("overrideBoolean", Boolean.TRUE);
        assertTrue(fBoolean.get());

        ConfigurationManager.getConfigInstance().clearProperty("overrideBoolean");
        assertFalse(fBoolean.get());

        ConfigurationManager.getConfigInstance().clearProperty("defaultBoolean");
        assertTrue(fBoolean.get());
    }

}