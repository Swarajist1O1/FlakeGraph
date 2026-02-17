class DummyClass_170455 {
    @Test
    public void testMBeanForString()
    {
        String obj = "foo";
        Object mbean = container.mbeanFor(obj);
        assertNotNull(mbean);
        container.beanAdded(null, obj);
        ObjectName objectName = container.findMBean(obj);
        assertNotNull(objectName);
    }

}