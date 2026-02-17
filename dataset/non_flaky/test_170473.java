class DummyClass_170473 {
    @Test
    public void testSetAttributeNullCheck() throws Exception
    {
        objectMBean.setAttribute(null);

        AttributeNotFoundException e = assertThrows(AttributeNotFoundException.class, () -> objectMBean.getAttribute(null));

        assertNotNull(e, "An AttributeNotFoundException must have occurred by now as there is no attribute with the name null");
    }

}