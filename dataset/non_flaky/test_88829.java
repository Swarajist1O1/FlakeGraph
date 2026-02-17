class DummyClass_88829 {
    @Test
    public void testShortSize() throws Exception {
        withMap(map -> {
            map.put(1, 1, 0, 0);
            map.put(2, 0, 1, 1);
            map.remove(1, 1);
        }, 2);
    }

}