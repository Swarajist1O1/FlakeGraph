class DummyClass_92623 {
    @Test
    public void testDeserializationMissingRequired() throws Exception {
        ObjectReader r = READER.without(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkReqBoxDatabindException(r, orangeBoxMissingJson);
        checkReqBoxDatabindException(r, appleBoxMissingJson);

        r = READER.with(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkReqBoxDatabindException(r, orangeBoxMissingJson);
        checkReqBoxDatabindException(r, appleBoxMissingJson);
    }

}