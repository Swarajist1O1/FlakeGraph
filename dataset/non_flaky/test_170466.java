class DummyClass_170466 {
    @Test
    public void testDestroyingContainerUnregistersMBeans() throws Exception
    {
        QueuedThreadPool bean = new QueuedThreadPool();
        container.addBean(bean, true);

        String pkg = bean.getClass().getPackage().getName();
        Set<ObjectName> objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        // QueuedThreadPool and ThreadPoolBudget.
        assertEquals(2, objectNames.size());

        container.stop();
        container.destroy();

        objectNames = mbeanServer.queryNames(ObjectName.getInstance(pkg + ":*"), null);
        assertEquals(0, objectNames.size());
    }

}