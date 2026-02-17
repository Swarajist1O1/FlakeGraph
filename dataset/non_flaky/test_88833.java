class DummyClass_88833 {
    @Test
    public void testCollisionOnRemove() {
        Map<FullPageId, Long> ctrl = new LinkedHashMap<>();
        int cap = 10;
        FullPageId baseId = new FullPageId(0, 1);

        withMap(map -> {
            for (int i = 0; i < cap; i++) {
                int grpId = i + 1;
                int pageId = findPageIdForCollision(grpId, baseId, cap);
                ctrl.put(new FullPageId(pageId, grpId), (long)grpId);
                map.put(grpId, pageId, (long)grpId, 1);
            }
            for (FullPageId next : ctrl.keySet()) {
                assertTrue(map.remove(next.groupId(), next.pageId()));
            }
        }, cap);
    }

}