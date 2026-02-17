class DummyClass_99753 {
    @Test
    public void available_ClosedButWithBytes()
    {
        inputPlus = new AsyncStreamingInputPlus(channel);
        int size = 4;
        buf = channel.alloc().heapBuffer(size);
        buf.writerIndex(size);
        inputPlus.append(buf);
        inputPlus.requestClosure();
        Assert.assertEquals(size, inputPlus.unsafeAvailable());
    }

}