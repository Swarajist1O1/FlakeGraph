class DummyClass_170507 {
    @Test
    public void testDestroy()
    {
        setUpDestroy();

        objectName = mbeanContainer.findMBean(managed);
        mbeanContainer.destroy();

        assertFalse(mbeanContainer.getMBeanServer().isRegistered(objectName), "Unregistered bean - managed");
    }

}