class DummyClass_118782 {
    @Test(expected = IllegalReferenceCountException.class)
    public void testRetainResurrect() {
        AbstractReferenceCountedByteBuf referenceCounted = newReferenceCounted();
        assertTrue(referenceCounted.release());
        assertEquals(0, referenceCounted.refCnt());
        referenceCounted.retain();
    }

}