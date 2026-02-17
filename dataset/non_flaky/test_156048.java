class DummyClass_156048 {
    @Test
    public void testMatchSuccess() {
        Assert.assertEquals(Kind.COMMENT, Kind.match(Kind.COMMENT.value()));
    }

}