class DummyClass_170471 {
    @Test
    public void testGetAttributeAttributeNotFoundException()
    {
        AttributeNotFoundException e = assertThrows(AttributeNotFoundException.class, () -> objectMBean.getAttribute("ffname"));

        assertNotNull(e, "An AttributeNotFoundException must have occurred by now as there is no attribute with the name ffname in bean");
    }

}