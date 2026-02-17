class DummyClass_170505 {
    @Test
    public void testBeanRemovedInstanceNotFoundException() throws Exception
    {
        // given
        setUpBeanRemoved();
        objectName = mbeanContainer.findMBean(managed);

        // when
        mbeanContainer.getMBeanServer().unregisterMBean(objectName);

        // then
        assertFalse(mbeanServer.isRegistered(objectName), "Bean must not have been registered as we unregistered the bean");
        // this flow covers InstanceNotFoundException. Actual code just eating
        // the exception. i.e Actual code just printing the stacktrace, whenever
        // an exception of type InstanceNotFoundException occurs.
        mbeanContainer.beanRemoved(null, managed);
    }

}