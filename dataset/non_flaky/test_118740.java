class DummyClass_118740 {
    @Test(expected = IllegalArgumentException.class)
    public void decodeHexDumpWithInvalidChar() {
        ByteBufUtil.decodeHexDump("fg");
    }

}