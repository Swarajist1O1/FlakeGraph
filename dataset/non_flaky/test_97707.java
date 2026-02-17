class DummyClass_97707 {
    @Test
    public void testJavadoc() {
        final Settings settings = TestUtils.settings();
        settings.javadocXmlFiles = Arrays.asList(new File("src/test/javadoc/test-javadoc.xml"));
        final TypeProcessor typeProcessor = new DefaultTypeProcessor();
        {
            final Model model = new Jackson2Parser(settings, typeProcessor).parseModel(ClassWithJavadoc.class);
            final BeanModel bean = model.getBeans().get(0);
            Assert.assertEquals("Documentation for ClassWithJavadoc. First line.", bean.getComments().get(0));
            Assert.assertEquals("Second line.", bean.getComments().get(1));
            final PropertyModel property1 = bean.getProperties().get(0);
            Assert.assertEquals("Documentation for documentedField.", property1.getComments().get(0));
            final PropertyModel property2 = bean.getProperties().get(1);
            Assert.assertEquals("Documentation for documentedEnumField.", property2.getComments().get(0));
            final EnumModel enumModel = model.getEnums().get(0);
            Assert.assertEquals("Documentation for DummyEnum.", enumModel.getComments().get(0));
        }
        {
            final Model model = new Jackson2Parser(settings, typeProcessor).parseModel(ClassWithoutJavadoc.class);
            final BeanModel bean = model.getBeans().get(0);
            Assert.assertNull(bean.getComments());
            final PropertyModel property = bean.getProperties().get(0);
            Assert.assertNull(property.getComments());
        }
        {
            final String generated = new TypeScriptGenerator(settings).generateTypeScript(
                    Input.from(ClassWithJavadoc.class, InterfaceWithJavadoc.class, ClassWithEmbeddedExample.class));

            Assert.assertTrue(generated.contains("Documentation for ClassWithJavadoc. First line."));
            Assert.assertTrue(generated.contains("Second line."));
            Assert.assertTrue(generated.contains("Documentation for documentedField."));
            Assert.assertTrue(generated.contains("Documentation for documentedEnumField."));
            Assert.assertTrue(generated.contains("Documentation for DummyEnum."));
            Assert.assertTrue(generated.contains("Documentation for getter property."));
            Assert.assertTrue(generated.contains("Documentation for renamed field."));
            Assert.assertTrue(generated.contains("Documentation for InterfaceWithJavadoc."));
            Assert.assertTrue(generated.contains("Documentation for interface getter property."));
            Assert.assertTrue(generated.contains("@return value of getterPropery"));
            Assert.assertTrue(generated.contains("@deprecated replaced by something else"));
            Assert.assertTrue(generated.contains(" *     // indentation and line breaks are kept\n * \n *     {@literal @}"));
            Assert.assertTrue(generated.contains(" *     public List<String> generics() {\n"));
            Assert.assertTrue(generated.contains("ff0000"));
            Assert.assertTrue(generated.contains("00ff00"));
            Assert.assertTrue(generated.contains("0000ff"));
        }

}