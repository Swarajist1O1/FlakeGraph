class DummyClass_114099 {
    @Test
    public void fromClass_constructsBeanTableSchema() {
        TableSchema<SimpleBean> tableSchema = TableSchema.fromClass(SimpleBean.class);
        assertThat(tableSchema).isInstanceOf(BeanTableSchema.class);
    }

}