class DummyClass_118728 {
    @Test(expected = NullPointerException.class)
    public void shouldNotAllowNullInConstructor1() {
        new UnpooledHeapByteBuf(null, new byte[1], 0);
    }

}