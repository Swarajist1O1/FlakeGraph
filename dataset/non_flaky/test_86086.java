class DummyClass_86086 {
    @Test
    public void updateWithErrors() {
        final String newTitle = "A NEW TITLE " + DateTime.now(DateTimeZone.UTC).toString();
        final String newDescription = "A NEW DESCRIPTION " + DateTime.now(DateTimeZone.UTC).toString();

        final EventDefinitionDto existingDto = eventDefinitionService.get("54e3deadbeefdeadbeef0000").orElse(null);
        final JobDefinitionDto existingJobDefinition = jobDefinitionService.get("54e3deadbeefdeadbeef0001").orElse(null);
        final JobTriggerDto existingTrigger = jobTriggerService.get("54e3deadbeefdeadbeef0002").orElse(null);

        assertThat(existingDto).isNotNull();
        assertThat(existingJobDefinition).isNotNull();
        assertThat(existingTrigger).isNotNull();

        final EventDefinitionDto updatedDto = existingDto.toBuilder()
                .title(newTitle)
                .description(newDescription)
                .build();

        doThrow(new NullPointerException("yolo1")).when(eventDefinitionService).save(any());

        assertThatCode(() -> handler.update(updatedDto, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("yolo1");

        assertThat(eventDefinitionService.get(existingDto.id())).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isEqualTo(existingDto.id());
            assertThat(dto.title()).isEqualTo(existingDto.title());
            assertThat(dto.description()).isEqualTo(existingDto.description());
        });

        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isPresent().get().satisfies(definition -> {
            assertThat(definition.title()).isEqualTo(existingJobDefinition.title());
            assertThat(definition.description()).isEqualTo(existingJobDefinition.description());
        });

        // Reset all before doing new stubs
        reset(eventDefinitionService);
        reset(jobDefinitionService);
        reset(jobTriggerService);

        doThrow(new NullPointerException("yolo2")).when(jobDefinitionService).save(any());

        assertThatCode(() -> handler.update(updatedDto, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("yolo2");

        assertThat(eventDefinitionService.get(existingDto.id())).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isEqualTo(existingDto.id());
            assertThat(dto.title()).isEqualTo(existingDto.title());
            assertThat(dto.description()).isEqualTo(existingDto.description());
        });

        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isPresent().get().satisfies(definition -> {
            assertThat(definition.title()).isEqualTo(existingJobDefinition.title());
            assertThat(definition.description()).isEqualTo(existingJobDefinition.description());
        });

        // Reset all before doing new stubs
        reset(eventDefinitionService);
        reset(jobDefinitionService);
        reset(jobTriggerService);

        doThrow(new NullPointerException("yolo3")).when(jobTriggerService).update(any());

        assertThatCode(() -> handler.update(updatedDto, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("yolo3");

        assertThat(eventDefinitionService.get(existingDto.id())).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isEqualTo(existingDto.id());
            assertThat(dto.title()).isEqualTo(existingDto.title());
            assertThat(dto.description()).isEqualTo(existingDto.description());
        });

        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isPresent().get().satisfies(definition -> {
            assertThat(definition.title()).isEqualTo(existingJobDefinition.title());
            assertThat(definition.description()).isEqualTo(existingJobDefinition.description());
        });
    }

}