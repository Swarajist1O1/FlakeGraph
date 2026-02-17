class DummyClass_91576 {
    @Test
    public void testCaseInsensitiveSet() {
        CaseInsensitiveStringSet s1 = new CaseInsensitiveStringSet();
        s1.add("a");
        Set<String> s2 = new HashSet<>();
        s2.add("a");
        Assert.assertEquals(s2, s1);
        Assert.assertTrue(s1.contains("A"));
    }

}