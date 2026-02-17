class DummyClass_136525 {
  @Test
  public void should_generate_indexed_select_dsl_class_with_udt() throws Exception {
    setExec(aptUtils -> {

      final GlobalParsingContext globalContext = new GlobalParsingContext(
          V2_1.INSTANCE,
          InsertStrategy.ALL_FIELDS,
          new LowerCaseNaming(),
          FieldFilter.EXPLICIT_ENTITY_FIELD_FILTER,
          FieldFilter.EXPLICIT_UDT_FIELD_FILTER,
          Optional.empty());

      final String className = TestEntityWithIndexAndUDT.class.getCanonicalName();
      final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

      final EntityParser entityParser = new EntityParser(aptUtils);

      final EntityMetaCodeGen.EntityMetaSignature entityMetaSignature = entityParser.parseEntity(typeElement, globalContext);

      final IndexSelectDSLCodeGen2_1 indexSelectDSLCodeGen2_1 = new IndexSelectDSLCodeGen2_1();

      final StringBuilder builder = new StringBuilder();
      try {
        JavaFile.builder(TypeUtils.GENERATED_PACKAGE, indexSelectDSLCodeGen2_1.buildSelectClass(globalContext, entityMetaSignature))
            .build()
            .writeTo(builder);
      } catch (IOException e) {
        Assertions.assertThat(false).as("IOException when generating class : %s", e.getMessage()).isTrue();
      }

      assertThat(builder.toString().trim()).isEqualTo(
          readCodeBlockFromFile("expected_code/dsl/index/should_generate_indexed_select_dsl_class_with_udt.txt", false));
    });
    launchTest();
  }

}