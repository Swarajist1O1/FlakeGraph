class DummyClass_156412 {
    @Test
    public void testParseSync() throws InterruptedException {
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        final SimpleDateFormat inner = new SimpleDateFormat(pattern);
        final Format sdf= new Format() {
            private static final long serialVersionUID = 1L;

            @Override
            public StringBuffer format(final Object obj,
                    final StringBuffer toAppendTo,

}