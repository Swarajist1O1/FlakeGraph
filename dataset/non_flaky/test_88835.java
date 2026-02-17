class DummyClass_88835 {
    @Test
    public void testPutAndCantGetOutdatedValue() throws Exception {
        withMap(map -> {
            //fill with 1 space left;
            for (int i = 0; i < 99; i++) {
                int ver = i;
                int grpId = ver + 1;
                int val = grpId * grpId;
                map.put(grpId, 1, val, ver);

                assertEquals(val, map.get(grpId, 1, ver, -1, -2));

                assertEquals(-2, map.get(grpId, 1, ver + 1, -1, -2));
            }
        }, 100);
    }

}