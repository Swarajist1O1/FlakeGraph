class DummyClass_91586 {
    @Test
    public void testBooleanArray() {
        ByteBuffer buffer = ByteBuffer.allocate(10000);
        boolean[] x = new boolean[] { true, false, true };
        BytesUtil.writeBooleanArray(x, buffer);
        buffer.flip();
        boolean[] y = BytesUtil.readBooleanArray(buffer);
        assertEquals(y[2], true);
        assertEquals(y[1], false);
    }

}