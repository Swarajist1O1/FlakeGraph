class DummyClass_148839 {
    @Test
    public void HasContentIsFalseWhenActivityTextHasNoContent() {
        Activity activity = createActivity();

        boolean result = activity.hasContent();

        Assert.assertEquals(result, false);
    }

}