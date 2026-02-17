class DummyClass_88838 {
    @Test
    public void testClearAtWithControlMap7() throws Exception {
        int cap = 100;

        doRemovalTests(cap, (grpId, pageId) -> {
            int hc = Integer.hashCode(grpId) + 31 * Long.hashCode(pageId);

            return hc % 7 == 0;
        });
    }

}