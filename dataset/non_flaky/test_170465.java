class DummyClass_170465 {
    @Test
    public void testStoppingContainerDoesNotUnregistersMBeans() throws Exception
    {
        QueuedThreadPool bean = new QueuedThreadPool();
        container.addBean(bean, true);

        String pkg = bean.getClass().getPackage().getName();
        Set<ObjectName> objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        // QueuedThreadPool and ThreadPoolBudget.
        assertEquals(2, objectNames.size());

        container.stop();

        objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        assertEquals(2, objectNames.size());

        // Remove the MBeans to start clean on the next test.
        objectNames.forEach(objectName ->
        {
            try
            {
                mbeanServer.unregisterMBean(objectName);
            }
            catch (Throwable ignored)
            {
            }
        });
    }

}