class DummyClass_92620 {
    @Test
    public void testDeserializationNull() throws Exception {
        ObjectReader r = READER.without(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBoxNull(r, orangeBoxNullJson);
        checkAppleBoxNull(r, appleBoxNullJson);

        r = READER.with(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBoxNull(r, orangeBoxNullJson);
        checkAppleBoxNull(r, appleBoxNullJson);
    }

}