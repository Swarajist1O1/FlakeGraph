class DummyClass_177206 {
    @Test
    public void contextLoads() {
        assertThat(applicationContext.getBean(ArmeriaReactiveWebServerFactory.class)).isNotNull();
        assertThatThrownBy(() -> {
            applicationContext.getBean(ArmeriaAutoConfiguration.class);
        }).isInstanceOf(BeansException.class);
    }

}