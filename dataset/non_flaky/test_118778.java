class DummyClass_118778 {
    @Test(expected = IllegalReferenceCountException.class)
    public void testRetainOverflow() {
        AbstractReferenceCountedByteBuf referenceCounted = newReferenceCounted();
        referenceCounted.setRefCnt(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, referenceCounted.refCnt());
        referenceCounted.retain();
    }

}