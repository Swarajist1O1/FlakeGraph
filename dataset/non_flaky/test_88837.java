class DummyClass_88837 {
    @Test
    public void testClearAtWithControlMap3() throws Exception {
        int cap = 100;

        doRemovalTests(cap, (grpId, pageId) -> {
            int hc = Integer.hashCode(grpId) + 31 * Long.hashCode(pageId);

            return hc % 3 == 0;
        });
    }

}