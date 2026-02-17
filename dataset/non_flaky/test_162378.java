class DummyClass_162378 {
    @Test
    public void testCompareVersionEqual() {
        assertTrue("1.20 == 1.20", new ComparableVersion("1.20").compareTo(new ComparableVersion("1.20")) == 0);
    }

}