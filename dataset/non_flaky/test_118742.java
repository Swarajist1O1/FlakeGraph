class DummyClass_118742 {
    @Test
    public void notEqualsBufferSubsections() {
        byte[] b1 = new byte[50];
        byte[] b2 = new byte[256];
        Random rand = new Random();
        rand.nextBytes(b1);
        rand.nextBytes(b2);
        final int iB1 = b1.length / 2;
        final int iB2 = iB1 + b1.length;
        final int length = b1.length - iB1;
        System.arraycopy(b1, iB1, b2, iB2, length);
        // Randomly pick an index in the range that will be compared and make the value at that index differ between
        // the 2 arrays.
        int diffIndex = random(rand, iB1, iB1 + length - 1);
        ++b1[diffIndex];
        assertFalse(ByteBufUtil.equals(Unpooled.wrappedBuffer(b1), iB1, Unpooled.wrappedBuffer(b2), iB2, length));
    }

}