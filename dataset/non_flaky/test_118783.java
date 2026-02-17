class DummyClass_118783 {
    @Test(expected = IllegalReferenceCountException.class)
    public void testRetainResurrect2() {
        AbstractReferenceCountedByteBuf referenceCounted = newReferenceCounted();
        assertTrue(referenceCounted.release());
        assertEquals(0, referenceCounted.refCnt());
        referenceCounted.retain(2);
    }

}