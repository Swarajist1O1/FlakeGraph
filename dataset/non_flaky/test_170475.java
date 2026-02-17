class DummyClass_170475 {
    @Test
    public void testSetAttributesWithCorrectValues()
    {
        AttributeList attributes = getAttributes("fname", "vijay");
        objectMBean.setAttributes(attributes);

        attributes = objectMBean.getAttributes(new String[]{"fname"});

        assertEquals(1, attributes.size());
        assertEquals("vijay", ((Attribute)(attributes.get(0))).getValue(), "Fname value must be equal to vijay");
    }

}