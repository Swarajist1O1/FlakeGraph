class DummyClass_99752 {
    @Test
    public void available_HappyPath()
    {
        inputPlus = new AsyncStreamingInputPlus(channel);
        int size = 4;
        buf = channel.alloc().heapBuffer(size);
        buf.writerIndex(size);
        inputPlus.append(buf);
        Assert.assertEquals(size, inputPlus.unsafeAvailable());
    }

}