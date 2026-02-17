class DummyClass_92616 {
    @Test
    public void testDeserializationNull() throws Exception {
        checkOrangeBoxNull(BOX_READER_PASS, orangeBoxNullJson);
        checkAppleBoxNull(BOX_READER_PASS, appleBoxNullJson);

        checkOrangeBoxNull(BOX_READER_FAIL, orangeBoxNullJson);
        checkAppleBoxNull(BOX_READER_FAIL, appleBoxNullJson);
    }

}