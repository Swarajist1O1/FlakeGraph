class DummyClass_114097 {
    @Test
    public void fromBean_constructsBeanTableSchema() {
        BeanTableSchema<SimpleBean> beanBeanTableSchema = TableSchema.fromBean(SimpleBean.class);
        assertThat(beanBeanTableSchema).isNotNull();
    }

}