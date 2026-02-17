class DummyClass_31 {
@Test
public void getFieldNamesTest() {
    List<String> names = EnumUtil.getFieldNames(TestEnum.class);
    Assert.assertEquals(CollUtil.newArrayList("type", "name"), names);
}
}