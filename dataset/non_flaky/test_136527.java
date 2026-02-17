class DummyClass_136527 {
    @Test
    public void should_generate_select_dsl_class_for_udt_as_clustering() throws Exception {
        setExec(aptUtils -> {

            final GlobalParsingContext globalContext = new GlobalParsingContext(
                    V2_1.INSTANCE,
                    InsertStrategy.ALL_FIELDS,
                    new LowerCaseNaming(),
                    FieldFilter.EXPLICIT_ENTITY_FIELD_FILTER,
                    FieldFilter.EXPLICIT_UDT_FIELD_FILTER,
                    Optional.empty());

            final String className = TestEntityWithUDTAsClustering.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityParser entityParser = new EntityParser(aptUtils);

            final EntityMetaCodeGen.EntityMetaSignature entityMetaSignature = entityParser.parseEntity(typeElement, globalContext);

            final SelectDSLCodeGen2_1 selectDSLCodeGen2_1 = new SelectDSLCodeGen2_1();

            final StringBuilder builder = new StringBuilder();
            try {
                JavaFile.builder(TypeUtils.GENERATED_PACKAGE, selectDSLCodeGen2_1.buildSelectClass(globalContext, entityMetaSignature))
                        .build()
                        .writeTo(builder);
            } catch (IOException e) {
                Assertions.assertThat(false).as("IOException when generating class : %s", e.getMessage()).isTrue();
            }

            assertThat(builder.toString().trim()).isEqualTo(
                    readCodeBlockFromFile("expected_code/dsl/select/should_generate_manager_class_for_index_dsl.txt", false));
        });
        launchTest();
    }

}