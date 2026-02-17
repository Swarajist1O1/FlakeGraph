class DummyClass_99747 {
    @Test
    public void append_closed()
    {
        inputPlus = new AsyncStreamingInputPlus(channel);
        inputPlus.requestClosure();
        inputPlus.close();
        buf = channel.alloc().buffer(4);
        assertFalse(inputPlus.append(buf));
    }

}