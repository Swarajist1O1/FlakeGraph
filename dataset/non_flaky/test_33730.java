class DummyClass_33730 {
    @Test
    public void isInjectComponent() {
        wac.getBean(JSONPResponseBodyAdvice.class);
        wac.getBean(FastJsonViewResponseBodyAdvice.class);
    }

}