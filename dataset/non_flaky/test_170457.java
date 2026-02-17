class DummyClass_170457 {
    @Test
    public void testMBeanForIntArray()
    {
        int[] obj = {0, 1, 2};
        Object mbean = container.mbeanFor(obj);
        assertNotNull(mbean);
        container.beanAdded(null, obj);
        ObjectName objectName = container.findMBean(obj);
        assertNotNull(objectName);
    }

}