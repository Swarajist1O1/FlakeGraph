class DummyClass_118739 {
    @Test(expected = IllegalArgumentException.class)
    public void decodeHexDumpWithOddLength() {
        ByteBufUtil.decodeHexDump("abc");
    }

}