class DummyClass_148902 {
    @Test
    public void testCloneNull() {
        Activity resultActivity = (Activity) Serialization.clone((Object) null);
        Assert.assertNull(resultActivity);
    }

}