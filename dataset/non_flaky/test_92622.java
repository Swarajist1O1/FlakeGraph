class DummyClass_92622 {
    @Test
    public void testDeserializationMissing() throws Exception {
        ObjectReader r = READER.without(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBoxNull(r, orangeBoxMissingJson);
        checkAppleBoxNull(r, appleBoxMissingJson);

        r = READER.with(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkBoxDatabindException(r, orangeBoxMissingJson);
        checkBoxDatabindException(r, appleBoxMissingJson);
    }

}