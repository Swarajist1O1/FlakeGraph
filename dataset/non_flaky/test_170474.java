class DummyClass_170474 {
    @Test
    public void testSetAttributeAttributeWithWrongAttrName()
    {
        attribute = new Attribute("fnameee", "charu");

        AttributeNotFoundException e = assertThrows(AttributeNotFoundException.class, () -> objectMBean.setAttribute(attribute));

        assertNotNull(e, "An AttributeNotFoundException must have occurred by now as there is no attribute " + "with the name ffname in bean");
    }

}