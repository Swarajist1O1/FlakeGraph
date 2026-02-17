class DummyClass_170459 {
    @Test
    public void testDerivedAttributes() throws Exception
    {
        Derived derived = new Derived();
        Managed managed = derived.getManagedInstance();
        ObjectMBean derivedMBean = (ObjectMBean)container.mbeanFor(derived);
        ObjectMBean managedMBean = (ObjectMBean)container.mbeanFor(managed);

        container.beanAdded(null, derived);
        container.beanAdded(null, managed);

        MBeanInfo derivedInfo = derivedMBean.getMBeanInfo();
        assertNotNull(derivedInfo);
        MBeanInfo managedInfo = managedMBean.getMBeanInfo();
        assertNotNull(managedInfo);

        assertEquals("com.acme.Derived", derivedInfo.getClassName(), "name does not match");
        assertEquals("Test the mbean stuff", derivedInfo.getDescription(), "description does not match");
        assertEquals(5, derivedInfo.getAttributes().length, "attribute count does not match");
        assertEquals("Full Name", derivedMBean.getAttribute("fname"), "attribute values does not match");

        derivedMBean.setAttribute(new Attribute("fname", "Fuller Name"));
        assertEquals("Fuller Name", derivedMBean.getAttribute("fname"), "set attribute value does not match");
        assertEquals("goop", derivedMBean.getAttribute("goop"), "proxy attribute values do not match");
    }

}