class DummyClass_91569 {
    @Test
    public void basicTest() {
        String s1 = new String("hi");
        String s2 = new String("hi");

        List<String> c1 = Lists.newArrayList(s1);
        List<String> c2 = Lists.newArrayList(s2);
        List<String> c3 = Lists.newArrayList(s2);

        Assert.assertFalse(IdentityUtils.collectionReferenceEquals(c1, c2));
        Assert.assertTrue(IdentityUtils.collectionReferenceEquals(c3, c2));
    }

}