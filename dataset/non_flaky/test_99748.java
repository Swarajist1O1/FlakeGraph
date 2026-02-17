class DummyClass_99748 {
    @Test
    public void append_normal()
    {
        inputPlus = new AsyncStreamingInputPlus(channel);
        int size = 4;
        buf = channel.alloc().buffer(size);
        buf.writerIndex(size);
        inputPlus.append(buf);
        Assert.assertEquals(buf.readableBytes(), inputPlus.unsafeAvailable());
    }

}