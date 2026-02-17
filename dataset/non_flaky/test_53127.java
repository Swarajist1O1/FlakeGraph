class DummyClass_53127 {
    @Test
    public void assert_generic_metrics() throws IOException {
        String data1 = firstBatch.get(statsDKey);
        String data2 = secondBatch.get(statsDKey);

        assertNotNull("Expected to find message for:'" + statsDKey + "' in the first batch.", data1);
        long first = IntegrationTestUtils.getStatsDValueFromMessage(data1);
        assertThat(statsDKey + " first value must have a positive value.", first, greaterThanOrEqualTo(0l));

        assertNotNull("Expected to find message for:'"+statsDKey+"' in the second batch.", data2);
        long second = IntegrationTestUtils.getStatsDValueFromMessage(data2);
        assertThat(statsDKey + " second value must have a positive value.", second, greaterThanOrEqualTo(0l));
    }

}