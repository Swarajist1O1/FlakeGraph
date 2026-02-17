class DummyClass_99749 {
    @Test
    public void read() throws IOException
    {
        inputPlus = new AsyncStreamingInputPlus(channel);
        // put two buffers of 8 bytes each into the queue.
        // then read an int, then a long. the latter tests offset into the inputPlus, as well as spanning across queued buffers.
        // the values of those int/long will both be '42', but spread across both queue buffers.
        ByteBuf buf = channel.alloc().buffer(8);
        buf.writeInt(42);
        buf.writerIndex(8);
        inputPlus.append(buf);
        buf = channel.alloc().buffer(8);
        buf.writeInt(42);
        buf.writerIndex(8);
        inputPlus.append(buf);
        Assert.assertEquals(16, inputPlus.unsafeAvailable());

//        ByteBuffer out = ByteBuffer.allocate(4);
//        int readCount = inputPlus.read(out);
//        Assert.assertEquals(4, readCount);
//        out.flip();
//        Assert.assertEquals(42, out.getInt());
//        Assert.assertEquals(12, inputPlus.unsafeAvailable());

//        out = ByteBuffer.allocate(8);
//        readCount = inputPlus.read(out);
//        Assert.assertEquals(8, readCount);
//        out.flip();
//        Assert.assertEquals(42, out.getLong());
//        Assert.assertEquals(4, inputPlus.unsafeAvailable());
    }

}