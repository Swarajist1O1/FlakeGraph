class DummyClass_84605 {
    @Test
    public void testGauge() throws Exception {
        int[] values = {78, -89};
        int[] callCounts = {0, 0};
        Gauge gauge0 = () -> {
            callCounts[0]++;
            return values[0];
        };
        Gauge gauge1 = () -> {
            callCounts[1]++;
            return values[1];
        };
        provider.getRootContext().registerGauge("gg", gauge0);

        int[] count = {0};
        provider.dump((k, v) -> {
            assertEquals("gg", k);
            assertEquals(values[0], ((Number) v).intValue());
            count[0]++;
        }
        );
        assertEquals(1, callCounts[0]);
        assertEquals(0, callCounts[1]);
        assertEquals(1, count[0]);
        count[0] = 0;
        String res2 = callServlet();
        assertThat(res2, CoreMatchers.containsString("# TYPE gg gauge"));
        assertThat(res2, CoreMatchers.containsString("gg 78.0"));

        provider.getRootContext().unregisterGauge("gg");
        provider.dump((k, v) -> {
            count[0]++;
        }
        );
        assertEquals(2, callCounts[0]);
        assertEquals(0, callCounts[1]);
        assertEquals(0, count[0]);
        String res3 = callServlet();
        assertTrue(res3.isEmpty());

        provider.getRootContext().registerGauge("gg", gauge1);

        provider.dump((k, v) -> {
            assertEquals("gg", k);
            assertEquals(values[1], ((Number) v).intValue());
            count[0]++;
        }
        );
        assertEquals(2, callCounts[0]);
        assertEquals(1, callCounts[1]);
        assertEquals(1, count[0]);
        count[0] = 0;

        String res4 = callServlet();
        assertThat(res4, CoreMatchers.containsString("# TYPE gg gauge"));
        assertThat(res4, CoreMatchers.containsString("gg -89.0"));
        assertEquals(2, callCounts[0]);
        // the servlet must sample the value again (from gauge1)
        assertEquals(2, callCounts[1]);

        // override gauge, without unregister
        provider.getRootContext().registerGauge("gg", gauge0);

        provider.dump((k, v) -> {
            count[0]++;
        }
        );
        assertEquals(1, count[0]);
        assertEquals(3, callCounts[0]);
        assertEquals(2, callCounts[1]);
    }

}