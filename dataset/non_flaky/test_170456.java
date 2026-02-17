class DummyClass_170456 {
    @Test
    public void testMBeanForStringArray()
    {
        String[] obj = {"a", "b"};
        Object mbean = container.mbeanFor(obj);
        assertNotNull(mbean);
        container.beanAdded(null, obj);
        ObjectName objectName = container.findMBean(obj);
        assertNotNull(objectName);
    }

}