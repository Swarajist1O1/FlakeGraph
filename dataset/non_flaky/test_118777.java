class DummyClass_118777 {
    @Test
    public void testIsTextMultiThreaded() throws Throwable {
        final ByteBuf buffer = Unpooled.copiedBuffer("Hello, World!", CharsetUtil.ISO_8859_1);

        try {
            final AtomicInteger counter = new AtomicInteger(60000);
            final AtomicReference<Throwable> errorRef = new AtomicReference<Throwable>();
            List<Thread> threads = new ArrayList<Thread>();
            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (errorRef.get() == null && counter.decrementAndGet() > 0) {
                                assertTrue(ByteBufUtil.isText(buffer, CharsetUtil.ISO_8859_1));
                            }
                        } catch (Throwable cause) {
                            errorRef.compareAndSet(null, cause);
                        }
                    }

}