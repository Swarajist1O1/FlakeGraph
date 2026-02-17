class DummyClass_170508 {
    @Test
    public void testDestroyInstanceNotFoundException() throws Exception
    {
        setUpDestroy();

        objectName = mbeanContainer.findMBean(managed);
        mbeanContainer.getMBeanServer().unregisterMBean(objectName);

        assertFalse(mbeanContainer.getMBeanServer().isRegistered(objectName), "Unregistered bean - managed");
        // this flow covers InstanceNotFoundException. Actual code just eating
        // the exception. i.e Actual code just printing the stacktrace, whenever
        // an exception of type InstanceNotFoundException occurs.
        mbeanContainer.destroy();
    }

}