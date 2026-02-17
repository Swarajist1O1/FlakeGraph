class DummyClass_84633 {
    @Test
    public void testIntegerRetrievalFromHexadecimalProperty() {
        int hexaValue = 0x3000000;
        String wrongValue = "0xwel";
        int defaultValue = 100;
        // property is set in hexadecimal value
        ZKClientConfig zkClientConfig = new ZKClientConfig();
        zkClientConfig.setProperty(ZKConfig.JUTE_MAXBUFFER,
                Integer.toString(hexaValue));
        int result = zkClientConfig.getInt(ZKConfig.JUTE_MAXBUFFER, defaultValue);
        assertEquals(result, hexaValue);
        zkClientConfig.setProperty(ZKConfig.JUTE_MAXBUFFER,
                wrongValue);
        try {
            result = zkClientConfig.getInt(ZKConfig.JUTE_MAXBUFFER, defaultValue);
            fail("NumberFormatException is expected");
        } catch (NumberFormatException exception) {
            // do nothing
        }
        zkClientConfig.setProperty(ZKConfig.JUTE_MAXBUFFER,
                " " + hexaValue + " ");
        result = zkClientConfig.getInt(ZKConfig.JUTE_MAXBUFFER, defaultValue);
        assertEquals(result, hexaValue);
    }

}