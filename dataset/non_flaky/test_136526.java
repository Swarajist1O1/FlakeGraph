class DummyClass_136526 {
    @Test
    public void should_generate_manager_class_for_index_dsl() throws Exception {
        setExec(aptUtils -> {

            final GlobalParsingContext globalContext = new GlobalParsingContext(
                    V3_7.INSTANCE,
                    InsertStrategy.ALL_FIELDS,
                    new LowerCaseNaming(),
                    FieldFilter.EXPLICIT_ENTITY_FIELD_FILTER,
                    FieldFilter.EXPLICIT_UDT_FIELD_FILTER,
                    Optional.empty());

            final String className = TestEntityWithSASI.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityParser entityParser = new EntityParser(aptUtils);

            final EntityMetaSignature entityMetaSignature = entityParser.parseEntity(typeElement, globalContext);

            final ManagerAndDSLClasses managerAndDSLClasses = ManagerCodeGen.buildManager(globalContext, aptUtils, entityMetaSignature);

            final StringBuilder builder = new StringBuilder();
            try {
                JavaFile.builder(TypeUtils.GENERATED_PACKAGE, managerAndDSLClasses.managerClass)
                        .build()
                        .writeTo(builder);
            } catch (IOException e) {
                Assertions.assertThat(false).as("IOException when generating class : %s", e.getMessage()).isTrue();
            }

            assertThat(builder.toString().trim())
                    .isEqualTo(readCodeBlockFromFile("expected_code/manager/should_generate_manager_class_for_index_dsl.txt"));
        });
        launchTest();
    }

}