class DummyClass_118779 {
    @Test(expected = IllegalReferenceCountException.class)
    public void testRetainOverflow2() {
        AbstractReferenceCountedByteBuf referenceCounted = newReferenceCounted();
        assertEquals(1, referenceCounted.refCnt());
        referenceCounted.retain(Integer.MAX_VALUE);
    }

}