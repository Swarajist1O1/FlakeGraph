class DummyClass_118780 {
    @Test(expected = IllegalReferenceCountException.class)
    public void testReleaseOverflow() {
        AbstractReferenceCountedByteBuf referenceCounted = newReferenceCounted();
        referenceCounted.setRefCnt(0);
        assertEquals(0, referenceCounted.refCnt());
        referenceCounted.release(Integer.MAX_VALUE);
    }

}