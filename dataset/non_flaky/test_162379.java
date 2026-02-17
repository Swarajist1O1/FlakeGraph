class DummyClass_162379 {
    @Test
    public void testCompareVersionGreaterThan() {
        assertTrue("2.10 > 1.20", new ComparableVersion("2.10").compareTo(new ComparableVersion("1.20")) == 1);
    }

}