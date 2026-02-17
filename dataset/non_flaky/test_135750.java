class DummyClass_135750 {
    @Test
    public void testChainingString() throws Exception {

        DynamicStringProperty node1 = new DynamicStringProperty("node1", "v1");
        StringProperty node2 = new HystrixPropertiesChainedArchaiusProperty.StringProperty("node2", node1);

        HystrixPropertiesChainedArchaiusProperty.StringProperty node3 = new HystrixPropertiesChainedArchaiusProperty.StringProperty("node3", node2);

        assertTrue("" + node3.get(), "v1".equals(node3.get()));

        ConfigurationManager.getConfigInstance().setProperty("node1", "v11");
        assertTrue("v11".equals(node3.get()));

        ConfigurationManager.getConfigInstance().setProperty("node2", "v22");
        assertTrue("v22".equals(node3.get()));

        ConfigurationManager.getConfigInstance().clearProperty("node1");
        assertTrue("v22".equals(node3.get()));

        ConfigurationManager.getConfigInstance().setProperty("node3", "v33");
        assertTrue("v33".equals(node3.get()));

        ConfigurationManager.getConfigInstance().clearProperty("node2");
        assertTrue("v33".equals(node3.get()));

        ConfigurationManager.getConfigInstance().setProperty("node2", "v222");
        assertTrue("v33".equals(node3.get()));

        ConfigurationManager.getConfigInstance().clearProperty("node3");
        assertTrue("v222".equals(node3.get()));

        ConfigurationManager.getConfigInstance().clearProperty("node2");
        assertTrue("v1".equals(node3.get()));

        ConfigurationManager.getConfigInstance().setProperty("node2", "v2222");
        assertTrue("v2222".equals(node3.get()));
    }

}