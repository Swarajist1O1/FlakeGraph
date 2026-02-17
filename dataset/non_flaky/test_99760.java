class DummyClass_99760 {
    @Test
    public void rebufferTimeout() throws IOException
    {
        long timeoutMillis = 1000;
        inputPlus = new AsyncStreamingInputPlus(channel, timeoutMillis, TimeUnit.MILLISECONDS);

        long startNanos = System.nanoTime();
        try
        {
            inputPlus.readInt();
            Assert.fail("should not have been able to read from the queue");
        }
        catch (InputTimeoutException e)
        {
            // this is the success case, and is expected. any other exception is a failure.
        }

        long durationNanos = System.nanoTime() - startNanos;
        Assert.assertTrue(TimeUnit.MILLISECONDS.toNanos(timeoutMillis) <= durationNanos);
    }

}