class DummyClass_148900 {
    @Test
    public void testGetAsNull() {
        Activity resultActivity = Serialization.getAs(null, Activity.class);
        Assert.assertNull(resultActivity);
    }

}