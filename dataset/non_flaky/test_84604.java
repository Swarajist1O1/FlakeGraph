class DummyClass_84604 {
    @Test
    public void testCounters() throws Exception {
        Counter counter = provider.getRootContext().getCounter("cc");
        counter.add(10);
        int[] count = {0};
        provider.dump((k, v) -> {
            assertEquals("cc", k);
            assertEquals(10, ((Number) v).intValue());
            count[0]++;
        }
        );
        assertEquals(1, count[0]);
        count[0] = 0;

        // this is not allowed but it must not throw errors
        counter.add(-1);

        provider.dump((k, v) -> {
            assertEquals("cc", k);
            assertEquals(10, ((Number) v).intValue());
            count[0]++;
        }
        );
        assertEquals(1, count[0]);

        // we always must get the same object
        assertSame(counter, provider.getRootContext().getCounter("cc"));

        String res = callServlet();
        assertThat(res, CoreMatchers.containsString("# TYPE cc counter"));
        assertThat(res, CoreMatchers.containsString("cc 10.0"));
    }

}