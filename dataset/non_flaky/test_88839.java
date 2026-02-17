class DummyClass_88839 {
    @Test
    public void testClearAllWithControlMap() throws Exception {
        int cap = 100;

        doRemovalTests(cap, (grpId, pageId) -> true);
    }

}