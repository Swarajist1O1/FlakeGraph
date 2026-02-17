class DummyClass_170469 {
    @Test
    public void testBasicOperations()
    {
        assertEquals(derivedExtended, objectMBean.getManagedObject(), "Managed objects should be equal");
        assertNull(objectMBean.getObjectName(), "This method call always returns null in the actual code");
        assertNull(objectMBean.getObjectNameBasis(), "This method call always returns null in the actual code");
        assertNull(objectMBean.getObjectContextBasis(), "This method call always returns null in the actual code");
        assertEquals(container, objectMBean.getMBeanContainer(), "Mbean container should be equal");
        assertEquals("Test the mbean extended stuff", objectMBeanInfo.getDescription(), "Mbean description must be equal to : Test the mbean extended stuff");
    }

}