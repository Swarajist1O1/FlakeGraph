class DummyClass_118781 {
    @Test
    public void testReleaseErrorMessage() {
        AbstractReferenceCountedByteBuf referenceCounted = newReferenceCounted();
        assertTrue(referenceCounted.release());
        try {
            referenceCounted.release(1);
            fail("IllegalReferenceCountException didn't occur");
        } catch (IllegalReferenceCountException e) {
            assertEquals("refCnt: 0, decrement: 1", e.getMessage());
        }
    }

}