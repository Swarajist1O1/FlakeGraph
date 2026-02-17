class DummyClass_91575 {
    @Test
    public void testCaseInsensitiveMap() {
        CaseInsensitiveStringMap<String> m1 = new CaseInsensitiveStringMap<>();
        m1.put("a", "a");
        Map<String, String> m2 = new HashMap<>();
        m2.put("a", "a");
        Assert.assertEquals(m2, m1);
        Assert.assertTrue(m1.containsKey("A"));
        Assert.assertFalse(m1.containsValue("A"));
    }

}