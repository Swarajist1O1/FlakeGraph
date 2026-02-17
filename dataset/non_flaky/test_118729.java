class DummyClass_118729 {
    @Test(expected = NullPointerException.class)
    public void shouldNotAllowNullInConstructor2() {
        new UnpooledHeapByteBuf(UnpooledByteBufAllocator.DEFAULT, null, 0);
    }

}