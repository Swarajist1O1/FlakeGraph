class DummyClass_170477 {
    @Test
    public void testSetAttributesForCollectionTypeAttribute() throws Exception
    {
        ArrayList<Derived> aliasNames = new ArrayList<>(Arrays.asList(getArrayTypeAttribute()));

        derivedManaged.setAliasNames(aliasNames);
        mBeanDerivedManaged.getMBeanInfo();

        assertNotNull(mBeanDerivedManaged.getAttribute("aliasNames"), "Address object shouldn't be null");
        assertNull(mBeanDerivedManaged.getAttribute("derived"), "Derived object shouldn't registered with container so its value will be null");
    }

}