class DummyClass_86110 {
    @Test
    public void createNativeEntity() {
        final EntityV1 entityV1 = createTestEntity();
        final JobDefinitionDto jobDefinitionDto = mock(JobDefinitionDto.class);

        when(jobDefinitionService.save(any(JobDefinitionDto.class))).thenReturn(jobDefinitionDto);
        final UserImpl kmerzUser = new UserImpl(mock(PasswordAlgorithmFactory.class), new Permissions(ImmutableSet.of()), ImmutableMap.of("username", "kmerz"));
        when(userService.load("kmerz")).thenReturn(kmerzUser);

        final NativeEntity<NotificationDto> nativeEntity = facade.createNativeEntity(
            entityV1,
            ImmutableMap.of(),
            ImmutableMap.of(),
            "kmerz");
        assertThat(nativeEntity).isNotNull();

        final NotificationDto notificationDto = nativeEntity.entity();
        assertThat(notificationDto.title()).isEqualTo("title");
        assertThat(notificationDto.description()).isEqualTo("descriptions");
        assertThat(notificationDto.config().type()).isEqualTo("http-notification-v1");
    }

}