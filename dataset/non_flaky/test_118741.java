class DummyClass_118741 {
    @Test
    public void equalsBufferSubsections() {
        byte[] b1 = new byte[128];
        byte[] b2 = new byte[256];
        Random rand = new Random();
        rand.nextBytes(b1);
        rand.nextBytes(b2);
        final int iB1 = b1.length / 2;
        final int iB2 = iB1 + b1.length;
        final int length = b1.length - iB1;
        System.arraycopy(b1, iB1, b2, iB2, length);
        assertTrue(ByteBufUtil.equals(Unpooled.wrappedBuffer(b1), iB1, Unpooled.wrappedBuffer(b2), iB2, length));
    }

}