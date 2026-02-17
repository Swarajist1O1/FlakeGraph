class DummyClass_170476 {
    @Test
    public void testSetAttributesForArrayTypeAttribute() throws Exception
    {
        Derived[] deriveds = getArrayTypeAttribute();

        derivedManaged.setAddresses(deriveds);
        mBeanDerivedManaged.getMBeanInfo();

        assertNotNull(mBeanDerivedManaged.getAttribute("addresses"), "Address object shouldn't be null");
    }

}