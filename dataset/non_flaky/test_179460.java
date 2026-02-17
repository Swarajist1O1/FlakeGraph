class DummyClass_179460 {
    //    @Test
    public void test1() throws IntrospectionException {
        List<EntityField> fields = null;// = new ArrayList<EntityField>();
        processAllColumns(Country.class, fields, null);
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