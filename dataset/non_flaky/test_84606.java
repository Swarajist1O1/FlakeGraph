class DummyClass_84606 {
    @Test
    public void testBasicSummary() throws Exception {
        Summary summary = provider.getRootContext()
                .getSummary("cc", MetricsContext.DetailLevel.BASIC);
        summary.add(10);
        summary.add(10);
        int[] count = {0};
        provider.dump((k, v) -> {
            count[0]++;
            int value = ((Number) v).intValue();

            switch (k) {
                case "cc{quantile=\"0.5\"}":
                    assertEquals(10, value);
                    break;
                case "cc_count":
                    assertEquals(2, value);
                    break;
                case "cc_sum":
                    assertEquals(20, value);
                    break;
                default:
                    fail("unespected key " + k);
                    break;
            }
        }
        );
        assertEquals(3, count[0]);
        count[0] = 0;

        // we always must get the same object
        assertSame(summary, provider.getRootContext()
                .getSummary("cc", MetricsContext.DetailLevel.BASIC));

        try {
            provider.getRootContext()
                    .getSummary("cc", MetricsContext.DetailLevel.ADVANCED);
            fail("Can't get the same summary with a different DetailLevel");
        } catch (IllegalArgumentException err) {
            assertThat(err.getMessage(), containsString("Already registered"));
        }

        String res = callServlet();
        assertThat(res, containsString("# TYPE cc summary"));
        assertThat(res, CoreMatchers.containsString("cc_sum 20.0"));
        assertThat(res, CoreMatchers.containsString("cc_count 2.0"));
        assertThat(res, CoreMatchers.containsString("cc{quantile=\"0.5\",} 10.0"));
    }

}