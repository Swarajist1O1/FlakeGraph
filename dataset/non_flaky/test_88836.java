class DummyClass_88836 {
    @Test
    public void testPutAndRefreshValue() throws Exception {
        withMap(map -> {
            //fill with 1 space left;
            for (int i = 0; i < 99; i++) {
                int ver = i;
                int grpId = ver + 1;
                int val = grpId * grpId;
                int pageId = 1;
                map.put(grpId, pageId, val, ver);

                map.refresh(grpId, pageId, ver + 1);

                assertEquals(val, map.get(grpId, pageId, ver + 1, -1, -2));

            }

            doAddRemove(map);
        }, 100);
    }

}