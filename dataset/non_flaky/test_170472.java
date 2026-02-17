class DummyClass_170472 {
    @Test
    public void testSetAttributeWithCorrectAttrName() throws Exception
    {
        Attribute attribute = new Attribute("fname", "charu");
        objectMBean.setAttribute(attribute);

        String value = (String)objectMBean.getAttribute("fname");

        assertEquals("charu", value, "Attribute(fname) value must be equal to charu");
    }

}