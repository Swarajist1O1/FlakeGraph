class DummyClass_86125 {
    @Test
    public void runWithMigrationStatus() {
        final int migratedConditions = 9; // Only 8 because we pass one migrated condition in
        final int migratedCallbacks = 3;  // Only 2 because we pass one migrated callback in

        assertThat(migrator.run(Collections.singleton("00000000-0000-0000-0000-000000000002"), Collections.singleton("54e3deadbeefdeadbeef0001"))).satisfies(result -> {
            assertThat(result.completedAlertConditions()).containsOnly(
                    "00000000-0000-0000-0000-000000000001",
                    "00000000-0000-0000-0000-000000000002",
                    "00000000-0000-0000-0000-000000000003",
                    "00000000-0000-0000-0000-000000000004",
                    "00000000-0000-0000-0000-000000000005",
                    "00000000-0000-0000-0000-000000000006",
                    "00000000-0000-0000-0000-000000000007",
                    "00000000-0000-0000-0000-000000000008",
                    "00000000-0000-0000-0000-000000000009",
                    "00000000-0000-0000-0000-000000000010"
            );
            assertThat(result.completedAlarmCallbacks()).containsOnly(
                    "54e3deadbeefdeadbeef0001",
                    "54e3deadbeefdeadbeef0002",
                    "54e3deadbeefdeadbeef0003",
                    "54e3deadbeefdeadbeef0004"
            );
        });

        // Make sure we use the EventDefinitionHandler to create the event definitions
        verify(eventDefinitionHandler, times(migratedConditions)).create(any(EventDefinitionDto.class), any(Optional.class));

        // Make sure we use the NotificationResourceHandler to create the notifications
        verify(notificationResourceHandler, times(migratedCallbacks)).create(any(NotificationDto.class), any(Optional.class));

        assertThat(eventDefinitionService.streamAll().count()).isEqualTo(migratedConditions);
        assertThat(notificationService.streamAll().count()).isEqualTo(migratedCallbacks);
    }

}