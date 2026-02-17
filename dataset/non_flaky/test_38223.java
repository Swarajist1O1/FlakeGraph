class DummyClass_38223 {
    @Test
    public void testPropertiesToXML()
    {
        // simple string kv pair
        Properties p = new Properties();
        p.setProperty("MY_CONFIG_KEY", "MY_CONFIG_VALUE");
        String propertiesAsXML = TextUtils.storePropertiesToXMLString(p);
        assertNotNull(propertiesAsXML);
        p = TextUtils.loadPropertiesFromXMLString(propertiesAsXML);
        assertNotNull(p.getProperty("MY_CONFIG_KEY"));
        assertEquals("MY_CONFIG_VALUE", p.getProperty("MY_CONFIG_KEY"));

        // embedded config
        Properties pComplex = new Properties();
        pComplex.setProperty("MY_SUB_CONFIG", TextUtils.storePropertiesToXMLString(p));
        propertiesAsXML = TextUtils.storePropertiesToXMLString(pComplex);
        assertNotNull(propertiesAsXML);
        pComplex = TextUtils.loadPropertiesFromXMLString(propertiesAsXML);
        p = TextUtils.loadPropertiesFromXMLString(pComplex.getProperty("MY_SUB_CONFIG"));
        assertNotNull(p.getProperty("MY_CONFIG_KEY"));
        assertEquals("MY_CONFIG_VALUE", p.getProperty("MY_CONFIG_KEY"));
    }

}