class DummyClass_30979 {
    @Test
    public void newPropertiesShouldHaveDefaultValueAfterReadingOldSerialization() {
      // given

      // set the new property to a value that is different from the default value
      addedProperty.setValue(true);

      // when
      newSchemaObject.wrap(bufferSerializedWithOldSchema);

      // then
      assertThat(addedProperty.getValue())
          .describedAs("value of added property after reading")
          .isFalse();
    }

}