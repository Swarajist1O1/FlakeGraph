class DummyClass_162380 {
    @Test
    public void testCompareVersionIgnoresExcessLength() {
        assertTrue("1.20 == 1.20.3", new ComparableVersion("1.20").compareTo(new ComparableVersion("1.20.3")) == 0);
    }

}