class DummyClass_170502 {
    @Test
    public void testBeanAdded()
    {
        setBeanAdded();

        objectName = mbeanContainer.findMBean(managed);

        assertTrue(mbeanServer.isRegistered(objectName), "Bean must have been registered");
    }

}