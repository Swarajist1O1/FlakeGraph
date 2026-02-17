class DummyClass_99800 {
    @Test (expected = IllegalArgumentException.class)
    public void build_SendSizeLessThanZero()
    {
        test(settings -> settings.withSocketSendBufferSizeInBytes(-1));
    }

}