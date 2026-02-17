class DummyClass_170500 {
    @Test
    public void testGetMBeanServer()
    {
        assertEquals(mbeanServer, mbeanContainer.getMBeanServer(), "MBean server Instance must be equal");
    }

}