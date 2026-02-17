class DummyClass_177205 {
    @Test
    public void contextLoads() {
        assertThat(applicationContext.getBean(ArmeriaAutoConfiguration.class)).isNotNull();
        assertThatThrownBy(() -> {
            applicationContext.getBean(ArmeriaReactiveWebServerFactory.class);
        }).isInstanceOf(BeansException.class);
    }

}