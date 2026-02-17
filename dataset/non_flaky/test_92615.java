class DummyClass_92615 {
    @Test
    public void testDeserializationPresent() throws Exception {
        checkOrangeBox(BOX_READER_PASS);
        checkAppleBox(BOX_READER_PASS);

        checkOrangeBox(BOX_READER_FAIL);
        checkAppleBox(BOX_READER_FAIL);
    }

}