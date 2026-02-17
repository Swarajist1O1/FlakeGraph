class DummyClass_114101 {
    @Test
    public void fromClass_invalidClassThrowsException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("InvalidBean");
        TableSchema.fromClass(InvalidBean.class);
    }

}