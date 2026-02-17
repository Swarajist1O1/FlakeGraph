class DummyClass_92617 {
    @Test
    public void testDeserializationEmpty() throws Exception {
        checkOrangeBoxEmpty(BOX_READER_PASS, orangeBoxEmptyJson);
        checkAppleBoxEmpty(BOX_READER_PASS, appleBoxEmptyJson);

        checkOrangeBoxEmpty(BOX_READER_FAIL, orangeBoxEmptyJson);
        checkAppleBoxEmpty(BOX_READER_FAIL, appleBoxEmptyJson);
    }

}