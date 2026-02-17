class DummyClass_92621 {
    @Test
    public void testDeserializationEmpty() throws Exception {
        ObjectReader r = READER.without(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBoxEmpty(r, orangeBoxEmptyJson);
        checkAppleBoxEmpty(r, appleBoxEmptyJson);

        r = READER.with(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        checkOrangeBoxEmpty(r, orangeBoxEmptyJson);
        checkAppleBoxEmpty(r, appleBoxEmptyJson);
    }

}