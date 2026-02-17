class DummyClass_86048 {
    @Test
    public void hasMessagesIndexedUpTo() {
        final DateTime timestamp = DateTime.now(DateTimeZone.UTC);

        when(dbProcessingStatusService.earliestPostIndexingTimestamp()).thenReturn(Optional.of(timestamp));

        assertThat(dependencyCheck.hasMessagesIndexedUpTo(timestamp)).isTrue();
        assertThat(dependencyCheck.hasMessagesIndexedUpTo(timestamp.minusHours(1))).isTrue();
        assertThat(dependencyCheck.hasMessagesIndexedUpTo(timestamp.plusHours(1))).isFalse();

        // The method should always return false if there is no value for the max indexed timestamp available
        when(dbProcessingStatusService.earliestPostIndexingTimestamp()).thenReturn(Optional.empty());

        assertThat(dependencyCheck.hasMessagesIndexedUpTo(timestamp)).isFalse();
        assertThat(dependencyCheck.hasMessagesIndexedUpTo(timestamp.minusHours(1))).isFalse();
        assertThat(dependencyCheck.hasMessagesIndexedUpTo(timestamp.plusHours(1))).isFalse();
    }

}