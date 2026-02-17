class DummyClass_135751 {
    @Test
    public void testChainingInteger() throws Exception {

        DynamicIntegerProperty node1 = new DynamicIntegerProperty("node1", 1);
        IntegerProperty node2 = new HystrixPropertiesChainedArchaiusProperty.IntegerProperty("node2", node1);

        HystrixPropertiesChainedArchaiusProperty.IntegerProperty node3 = new HystrixPropertiesChainedArchaiusProperty.IntegerProperty("node3", node2);

        assertTrue("" + node3.get(), 1 == node3.get());

        ConfigurationManager.getConfigInstance().setProperty("node1", 11);
        assertTrue(11 == node3.get());

        ConfigurationManager.getConfigInstance().setProperty("node2", 22);
        assertTrue(22 == node3.get());

        ConfigurationManager.getConfigInstance().clearProperty("node1");
        assertTrue(22 == node3.get());

        ConfigurationManager.getConfigInstance().setProperty("node3", 33);
        assertTrue(33 == node3.get());

        ConfigurationManager.getConfigInstance().clearProperty("node2");
        assertTrue(33 == node3.get());

        ConfigurationManager.getConfigInstance().setProperty("node2", 222);
        assertTrue(33 == node3.get());

        ConfigurationManager.getConfigInstance().clearProperty("node3");
        assertTrue(222 == node3.get());

        ConfigurationManager.getConfigInstance().clearProperty("node2");
        assertTrue(1 == node3.get());

        ConfigurationManager.getConfigInstance().setProperty("node2", 2222);
        assertTrue(2222 == node3.get());
    }

}