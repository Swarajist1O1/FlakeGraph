class DummyClass_99799 {
    @Test (expected = IllegalArgumentException.class)
    public void build_SmallSendSize()
    {
        test(settings -> settings.withSocketSendBufferSizeInBytes(999));
    }

}