class DummyClass_179461 {
    @Test
    public void test2() {
        List<EntityField> fields = _getProperties(Country.class);
        for (EntityField field : fields) {
            System.out.println(field.getName() + "  -  @Id:" + field.isAnnotationPresent(Id.class) + "  -  javaType:" + field.getJavaType());
        }
        System.out.println("======================================");
    }

}