class DummyClass_70848 {
    @Test
    public void testMetricsGroup() {
        SourceTaskMetricsGroup group = new SourceTaskMetricsGroup(taskId, metrics);
        SourceTaskMetricsGroup group1 = new SourceTaskMetricsGroup(taskId1, metrics);
        for (int i = 0; i != 10; ++i) {
            group.recordPoll(100, 1000 + i * 100);
            group.recordWrite(10);
        }
        for (int i = 0; i != 20; ++i) {
            group1.recordPoll(100, 1000 + i * 100);
            group1.recordWrite(10);
        }
        assertEquals(1900.0, metrics.currentMetricValueAsDouble(group.metricGroup(), "poll-batch-max-time-ms"), 0.001d);
        assertEquals(1450.0, metrics.currentMetricValueAsDouble(group.metricGroup(), "poll-batch-avg-time-ms"), 0.001d);
        assertEquals(33.333, metrics.currentMetricValueAsDouble(group.metricGroup(), "source-record-poll-rate"), 0.001d);
        assertEquals(1000, metrics.currentMetricValueAsDouble(group.metricGroup(), "source-record-poll-total"), 0.001d);
        assertEquals(3.3333, metrics.currentMetricValueAsDouble(group.metricGroup(), "source-record-write-rate"), 0.001d);
        assertEquals(100, metrics.currentMetricValueAsDouble(group.metricGroup(), "source-record-write-total"), 0.001d);
        assertEquals(900.0, metrics.currentMetricValueAsDouble(group.metricGroup(), "source-record-active-count"), 0.001d);

        // Close the group
        group.close();

        for (MetricName metricName : group.metricGroup().metrics().metrics().keySet()) {
            // Metrics for this group should no longer exist
            assertFalse(group.metricGroup().groupId().includes(metricName));
        }
        // Sensors for this group should no longer exist
        assertNull(group.metricGroup().metrics().getSensor("sink-record-read"));
        assertNull(group.metricGroup().metrics().getSensor("sink-record-send"));
        assertNull(group.metricGroup().metrics().getSensor("sink-record-active-count"));
        assertNull(group.metricGroup().metrics().getSensor("partition-count"));
        assertNull(group.metricGroup().metrics().getSensor("offset-seq-number"));
        assertNull(group.metricGroup().metrics().getSensor("offset-commit-completion"));
        assertNull(group.metricGroup().metrics().getSensor("offset-commit-completion-skip"));
        assertNull(group.metricGroup().metrics().getSensor("put-batch-time"));

        assertEquals(2900.0, metrics.currentMetricValueAsDouble(group1.metricGroup(), "poll-batch-max-time-ms"), 0.001d);
        assertEquals(1950.0, metrics.currentMetricValueAsDouble(group1.metricGroup(), "poll-batch-avg-time-ms"), 0.001d);
        assertEquals(66.667, metrics.currentMetricValueAsDouble(group1.metricGroup(), "source-record-poll-rate"), 0.001d);
        assertEquals(2000, metrics.currentMetricValueAsDouble(group1.metricGroup(), "source-record-poll-total"), 0.001d);
        assertEquals(6.667, metrics.currentMetricValueAsDouble(group1.metricGroup(), "source-record-write-rate"), 0.001d);
        assertEquals(200, metrics.currentMetricValueAsDouble(group1.metricGroup(), "source-record-write-total"), 0.001d);
        assertEquals(1800.0, metrics.currentMetricValueAsDouble(group1.metricGroup(), "source-record-active-count"), 0.001d);
    }

}