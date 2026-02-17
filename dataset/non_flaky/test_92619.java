class DummyClass_92619 {
    @Test
    public void testDeserializationPresent() throws Exception {
        ObjectReader r = READER.without(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBox(r);
        checkAppleBox(r);

        r = READER.with(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBox(r);
        checkAppleBox(r);
    }

}