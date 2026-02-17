class DummyClass_179458 {
    @Test
    public void test1() throws IntrospectionException {
        List<EntityField> fields = FieldHelper.getFields(Country.class);
        for (EntityField field : fields) {
            System.out.println(field.getName() + "  -  @Id:" + field.isAnnotationPresent(Id.class) + "  -  javaType:" + field.getJavaType());
        }
        System.out.println("======================================");

        fields = FieldHelper.getAll(Country.class);
        for (EntityField field : fields) {
            System.out.println(field.getName() + "  -  @Id:" + field.isAnnotationPresent(Id.class) + "  -  javaType:" + field.getJavaType());
        }
        System.out.println("======================================");
    }

}