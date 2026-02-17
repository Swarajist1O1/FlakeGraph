class DummyClass_88831 {
    @Test(expected = IgniteOutOfMemoryException.class)
    public void testSimplestOverflow() throws Exception {
        withMap(map -> {
                for (int i = 0; i < 10; i++) {
                    int grpId = i + 1;
                    int val = grpId * grpId;
                    assertSizeChanged("Unique put should be successful [" + grpId + "]", map, () -> map.put(grpId, 1, val, 1));

                    assertEquals(val, map.get(grpId, 1, 0, -1, -2));

                    assertSizeNotChanged("Duplicate put for " + grpId, map, () -> map.put(grpId, 1, 1, 1));
                    assertEquals(1, map.get(grpId, 1, 0, -1, -2));
                }

                map.put(11, 1, 11, 1);
            }
            , 10);
    }

}