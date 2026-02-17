class DummyClass_84632 {
    @Test
    public void testIntegerRetrievalFromProperty() {
        ZKClientConfig conf = new ZKClientConfig();
        String prop = "UnSetProperty" + System.currentTimeMillis();
        int defaultValue = 100;
        // property is not set we should get the default value
        int result = conf.getInt(prop, defaultValue);
        assertEquals(defaultValue, result);

        // property is set but can not be parsed to int, we should get the
        // NumberFormatException
        conf.setProperty(ZKConfig.JUTE_MAXBUFFER, "InvlaidIntValue123");
        try {
            result = conf.getInt(ZKConfig.JUTE_MAXBUFFER, defaultValue);
            fail("NumberFormatException is expected");
        } catch (NumberFormatException exception) {
            // do nothing
        }
        assertEquals(defaultValue, result);

        // property is set to an valid int, we should get the set value
        int value = ZKClientConfig.CLIENT_MAX_PACKET_LENGTH_DEFAULT;
        conf.setProperty(ZKConfig.JUTE_MAXBUFFER, Integer.toString(value));
        result = conf.getInt(ZKConfig.JUTE_MAXBUFFER, defaultValue);
        assertEquals(value, result);

        // property is set but with white spaces
        value = 12345;
        conf.setProperty(ZKConfig.JUTE_MAXBUFFER, " " + value + " ");
        result = conf.getInt(ZKConfig.JUTE_MAXBUFFER, defaultValue);
        assertEquals(value, result);
    }

}