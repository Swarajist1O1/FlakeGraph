class DummyClass_99751 {
    @Test
    public void available_closed()
    {
        inputPlus = new AsyncStreamingInputPlus(channel);
        inputPlus.requestClosure();
        inputPlus.unsafeAvailable();
    }

}