class DummyClass_88832 {
    @Test
    public void testPutRemoveOnSamePlaces() throws Exception {
        withMap(map -> {
                doAddRemove(map);

                //fill with 1 space left;
                for (int i = 0; i < 99; i++) {
                    int grpId = i + 1;
                    int val = grpId * grpId;
                    assertSizeChanged("Unique put should be successful " + grpId, map,
                        () -> map.put(grpId, 1, val, 1));
                }

                doAddRemove(map);
            }
            , 100);
    }

}