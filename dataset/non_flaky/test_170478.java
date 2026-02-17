class DummyClass_170478 {
    @Test
    public void testSetAttributesException()
    {
        AttributeList attributes = getAttributes("fnameee", "charu");

        attributes = objectMBean.setAttributes(attributes);

        // Original code eating the exception and returning zero size list
        assertEquals(0, attributes.size(), "As there is no attribute with the name fnameee, this should return empty");
    }

}