class DummyClass_162377 {
    @Test
    public void testCompareVersionGreaterThanSameMajor() {
        assertTrue("1.22 > 1.20", new ComparableVersion("1.22").compareTo(new ComparableVersion("1.20")) == 1);
    }

}