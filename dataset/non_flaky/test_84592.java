class DummyClass_84592 {
    @Test
    public void testReadStringCheckLength() {
        byte[] buf = new byte[]{
                Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE};
        ByteArrayInputStream is = new ByteArrayInputStream(buf);
        BinaryInputArchive ia = BinaryInputArchive.getArchive(is);
        try {
            ia.readString("");
            fail("Should have thrown an IOException");
        } catch (IOException e) {
            assertTrue(e.getMessage().startsWith(BinaryInputArchive.UNREASONBLE_LENGTH),
                    () -> "Not 'Unreasonable length' exception: " + e);
        }
    }

}