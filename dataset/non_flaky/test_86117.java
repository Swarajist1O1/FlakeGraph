class DummyClass_86117 {
    @Test
    public void createNativeEntity() {
        final EntityV1 entityV1 = createTestEntity();
        final NotificationDto notificationDto = NotificationDto.builder()
                .config(HTTPEventNotificationConfig.builder().url("https://hulud.net").build())
                .title("Notify me Senpai")
                .description("A notification for senpai")
                .id("dead-beef")
                .build();
        final EntityDescriptor entityDescriptor = EntityDescriptor.create("123123", ModelTypes.NOTIFICATION_V1);
        final ImmutableMap<EntityDescriptor, Object> nativeEntities = ImmutableMap.of(
                entityDescriptor, notificationDto);

        final JobDefinitionDto jobDefinitionDto = mock(JobDefinitionDto.class);
        final JobTriggerDto jobTriggerDto = mock(JobTriggerDto.class);
        when(jobDefinitionDto.id()).thenReturn("job-123123");
        when(jobSchedulerClock.nowUTC()).thenReturn(DateTime.now(DateTimeZone.UTC));
        when(jobDefinitionService.save(any(JobDefinitionDto.class))).thenReturn(jobDefinitionDto);
        when(jobTriggerService.create(any(JobTriggerDto.class))).thenReturn(jobTriggerDto);
        final UserImpl kmerzUser = new UserImpl(mock(PasswordAlgorithmFactory.class), new Permissions(ImmutableSet.of()), ImmutableMap.of("username", "kmerz"));
        when(userService.load("kmerz")).thenReturn(kmerzUser);


        final NativeEntity<EventDefinitionDto> nativeEntity = facade.createNativeEntity(
                entityV1,
                ImmutableMap.of(),
                nativeEntities,
                "kmerz");
        assertThat(nativeEntity).isNotNull();

        final EventDefinitionDto eventDefinitionDto = nativeEntity.entity();
        assertThat(eventDefinitionDto.title()).isEqualTo("title");
        assertThat(eventDefinitionDto.description()).isEqualTo("description");
        assertThat(eventDefinitionDto.config().type()).isEqualTo("aggregation-v1");
        // verify that ownership was registered for this entity
        verify(entityOwnershipService, times(1)).registerNewEventDefinition(nativeEntity.entity().id(), kmerzUser);
    }

}