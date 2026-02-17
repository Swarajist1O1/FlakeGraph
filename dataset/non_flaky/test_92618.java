class DummyClass_92618 {
    @Test
    public void testDeserializationMissing() throws Exception {
        checkOrangeBoxNull(BOX_READER_PASS, orangeBoxMissingJson);
        checkAppleBoxNull(BOX_READER_PASS, appleBoxMissingJson);

        checkBoxException(BOX_READER_FAIL, orangeBoxMissingJson);
        checkBoxException(BOX_READER_FAIL, appleBoxMissingJson);
    }

}